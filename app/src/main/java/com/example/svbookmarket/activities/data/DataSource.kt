package com.example.svbookmarket.activities.data

import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.CheckoutCard

class DataSource {
    fun loadCheckoutCard(): MutableList<CheckoutCard> {
        return mutableListOf(
            CheckoutCard(R.string.cover,R.string.name,R.string.author,R.string.price,R.string.number),
            CheckoutCard(R.string.cover,R.string.name,R.string.author,R.string.price,R.string.number),
            CheckoutCard(R.string.cover,R.string.name,R.string.author,R.string.price,R.string.number),
            CheckoutCard(R.string.cover,R.string.name,R.string.author,R.string.price,R.string.number),
            CheckoutCard(R.string.cover,R.string.name,R.string.author,R.string.price,R.string.number),
            CheckoutCard(R.string.cover,R.string.name,R.string.author,R.string.price,R.string.number),
            CheckoutCard(R.string.cover,R.string.name,R.string.author,R.string.price,R.string.number),
            CheckoutCard(R.string.cover,R.string.name,R.string.author,R.string.price,R.string.number),
        )
    }
}