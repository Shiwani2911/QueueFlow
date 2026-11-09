package com.example.queueflow.model

enum class TokenStatus {
    WAITING_NOT_CHECKED_IN,
    CHECKED_IN,
    CALLED,
    SERVING,
    COMPLETED,
    CANCELLED,
    EXPIRED,
    NO_SHOW
}