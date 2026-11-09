package com.example.queueflow.model

data class BankService(
    val id: String,
    val branchId: String,
    val serviceName: String,
    val prefix: String,
    val estimatedServiceMinutes: Int
)