package com.example.svbookmarket.activities.model

import android.icu.text.CaseMap
import java.net.URL


data class Book( var imageURL: URL, var title: String, var author: String, var price: Long, var rating: Double, var kind: String)
