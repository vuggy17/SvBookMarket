package com.example.svbookmarket.activities.model

import android.net.Uri

data class CartModel(var id: String = "",
                     var imgUrl: Uri,
                     var name:String,
                     var author:String,
                     var quantity:Int,
                     var price:Long,
                     var isChose:Boolean = false)