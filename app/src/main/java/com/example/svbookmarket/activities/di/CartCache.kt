package com.example.svbookmarket.activities.di

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.svbookmarket.activities.model.Cart
import javax.inject.Inject

class CartCache @Inject constructor(){
    private var cartList = mutableListOf<Cart>()
    fun get(): MutableList<Cart>? {
        Log.i("Cartcache", "${cartList?.size}")

        return cartList
    }

    fun set(items: MutableList<Cart>) {
        cartList = items
        Log.i("Cartcache", "${cartList?.size}")

    }

    fun remove(item: Cart) {
        cartList?.remove(item)
    }

}