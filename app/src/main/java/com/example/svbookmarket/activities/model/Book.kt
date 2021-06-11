package com.example.svbookmarket.activities.model

import android.accounts.AuthenticatorDescription
import android.icu.text.CaseMap
import android.net.Uri
import java.net.URL


data class Book( var imageURL: Uri, var title: String, var author: String, var price: Long, var rating: Double, var kind: String, var ratesCount: Long, var description: String)
