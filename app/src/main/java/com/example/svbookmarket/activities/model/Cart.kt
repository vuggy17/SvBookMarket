package com.example.svbookmarket.activities.model

data class Cart(
    var id: String,
    var imgUrl: String,
    var name: String,
    var author: String,
    var numbers: Int,
    var price: Long,
    var isChose: Boolean,
)