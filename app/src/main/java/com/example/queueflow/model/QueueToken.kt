
package com.example.queueflow.model

data class QueueToken(
    val id: String,
    val customerId: String,
    val branchId: String,
    val serviceId: String,
    val tokenNumber: String,
    val status: TokenStatus,
    val createdAt: Long,
    val checkInDeadline: Long,
    val checkedInAt: Long? = null,
    val calledAt: Long? = null
)
