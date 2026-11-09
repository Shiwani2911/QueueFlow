
package com.example.queueflow.Queue

import com.example.queueflow.model.BankService
import com.example.queueflow.model.QueueToken
import com.example.queueflow.model.TokenStatus
import java.util.UUID

class QueueManager {

    private val tokens = mutableListOf<QueueToken>()

    private val counters = mutableMapOf<String, Int>()

    private val checkInWindowMillis = 30 * 60 * 1000L

    // Generate a new token
    fun generateToken(
        customerId: String,
        service: BankService
    ): QueueToken {

        val currentTime = System.currentTimeMillis()

        val counterKey = "${service.branchId}-${service.id}"

        val nextNumber = (counters[counterKey] ?: 0) + 1

        counters[counterKey] = nextNumber

        val token = QueueToken(
            id = UUID.randomUUID().toString(),
            customerId = customerId,
            branchId = service.branchId,
            serviceId = service.id,
            tokenNumber = "${service.prefix}${nextNumber.toString().padStart(3, '0')}",
            status = TokenStatus.WAITING_NOT_CHECKED_IN,
            createdAt = currentTime,
            checkInDeadline = currentTime + checkInWindowMillis
        )

        tokens.add(token)

        return token
    }

    fun cancelToken(tokenId: String): Boolean {
        val index = tokens.indexOfFirst { it.id == tokenId }

        if (index == -1) return false

        val token = tokens[index]

        if (
            token.status != TokenStatus.WAITING_NOT_CHECKED_IN &&
            token.status != TokenStatus.CHECKED_IN
        ) {
            return false
        }

        tokens[index] = token.copy(
            status = TokenStatus.CANCELLED
        )

        return true
    }

    // Check in a customer
    fun checkIn(tokenId: String): Boolean {

        val currentTime = System.currentTimeMillis()

        expireOverdueTokens(currentTime)

        val index = tokens.indexOfFirst {
            it.id == tokenId
        }

        if (index == -1) return false

        val token = tokens[index]

        if (token.status != TokenStatus.WAITING_NOT_CHECKED_IN) {
            return false
        }

        tokens[index] = token.copy(
            status = TokenStatus.CHECKED_IN,
            checkedInAt = currentTime
        )

        return true
    }

    // Expire tokens that missed their check-in deadline
    fun expireOverdueTokens(
        currentTime: Long = System.currentTimeMillis()
    ) {

        for (index in tokens.indices) {

            val token = tokens[index]

            if (
                token.status == TokenStatus.WAITING_NOT_CHECKED_IN &&
                currentTime >= token.checkInDeadline
            ) {

                tokens[index] = token.copy(
                    status = TokenStatus.EXPIRED
                )
            }
        }
    }

    // Get all tokens
    fun getAllTokens(): List<QueueToken> {

        expireOverdueTokens()

        return tokens.toList()
    }
}
