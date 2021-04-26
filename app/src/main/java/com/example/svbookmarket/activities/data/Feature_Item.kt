package com.example.svbookmarket.activities.data

import com.google.android.material.resources.MaterialResources

data class Feature_Item (val imgBookCover: Int,
                         val textAuthor: String,
                         val textBookName: String,
                         val textPrice: String = "",
                         val NumReviews: String = "0 Reviews",
                         val NumStart: Double = 0.0)