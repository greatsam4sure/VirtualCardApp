package com.example.virtualcardapp

data class CardList(
    val count: Long,
    val items: List<CardItem>,
    val resource: String,
    val status: Long,
    val execution: Double
)
