package com.example.svbookmarket.activities.data

import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.CartModel
import com.example.svbookmarket.activities.model.CheckoutCard

class DataSource {
    fun loadCheckoutCard(): MutableList<CheckoutCard> {
        return mutableListOf(
            CheckoutCard("dsadsa","casc","adr","100","123"),
            CheckoutCard("dsadsa","casc","adr","100","123"),
            CheckoutCard("dsadsa","casc","adr","100","123"),
            CheckoutCard("dsadsa","casc","adr","100","123"),
            CheckoutCard("dsadsa","casc","adr","100","123"),
            CheckoutCard("dsadsa","casc","adr","100","123"),

        )
    }
    fun loadCart():MutableList<CartModel>{
        return mutableListOf(
            CartModel("cc","cc","cc","cc","1000" ),
            CartModel("cc","cc","cc","cc","200" ),
            CartModel("cc","cc1","cc","cc","113" ),
            CartModel("cc","cc","cc","cc","1997" ),
            CartModel("cc","cc","cc","cc","2001" ),
            CartModel("cc","cc1","cc","cc","99" ),

        )
    }
}