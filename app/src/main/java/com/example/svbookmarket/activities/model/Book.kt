package com.example.svbookmarket.activities.model

import android.net.Uri


data class Book(
    var imageURL: Uri = Uri.EMPTY,
    var title: String = "",
    var author: String = "",
    var price: Long = 1L,
    var rating: Double = 1.0,
    var kind: String = "",
    var ratesCount: Long = 1L,
    var description: String = ""
)
