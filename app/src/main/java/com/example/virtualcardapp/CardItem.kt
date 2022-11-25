package com.example.virtualcardapp

data class CardItem(
    val scheme: String,
    val accountID: String,
    val countryCode: String,
    val created: String,
    val expYear: Long,
    val expDate: String,
    val live: Boolean,
    val lastNumbers: String,
    val expMonth: Long,
    val updated: String,
    val programID: String,
    val firstNumbers: String,
    val id: String,
    val type: String
)
