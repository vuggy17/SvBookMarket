package com.example.svbookmarket.activities.data

import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.AddressModel
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
    fun loadAddressCard():MutableList<AddressModel>{
        return mutableListOf(
            AddressModel("Khuong Duy","0869256174","Ktx khu A, DHQG","Phuong Linh Trung", "Quan Thu Duc","Tp.Ho Chi Minh"),
            AddressModel("Khuong Duy","0869256174","Ktx khu A, DHQG","Phuong Linh Trung", "Quan Thu Duc","Tp.Ho Chi Minh"),
            AddressModel("Khuong Duy","0869256174","Ktx khu A, DHQG","Phuong Linh Trung", "Quan Thu Duc","Tp.Ho Chi Minh"),
            AddressModel("Khuong Duy","0869256174","Ktx khu A, DHQG","Phuong Linh Trung", "Quan Thu Duc","Tp.Ho Chi Minh"),
            AddressModel("Khuong Duy","0869256174","Ktx khu A, DHQG","Phuong Linh Trung", "Quan Thu Duc","Tp.Ho Chi Minh"),
            AddressModel("Khuong Duy","0869256174","Ktx khu A, DHQG","Phuong Linh Trung", "Quan Thu Duc","Tp.Ho Chi Minh"),
            AddressModel("Khuong Duy","0869256174","Ktx khu A, DHQG","Phuong Linh Trung", "Quan Thu Duc","Tp.Ho Chi Minh"),
        )
    }
    fun loadFeatureCard(): MutableList<Feature_Item> {
        return mutableListOf(
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang")
        )
    }
    fun loadBestsellingCard(): MutableList<Feature_Item> {
        return mutableListOf(
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 90000"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 12000"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 15000"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 20000"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 90000"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 12000"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 15000"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 20000")
        )
    }
    fun loadSuggestCard(): MutableList<Feature_Item> {
        return mutableListOf(
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 90000", "99 Reviews"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 12000",  "99 Reviews"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 15000",  "99 Reviews"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 20000", "99 Reviews"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 90000", "99 Reviews"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 12000", "99 Reviews"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 15000","99 Reviews"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 20000","99 Reviews")
        )
    }
}