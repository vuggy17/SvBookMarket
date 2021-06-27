package com.example.svbookmarket.activities.common

import com.example.svbookmarket.activities.model.AppAccount
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.model.Order
import com.example.svbookmarket.activities.model.User
import com.google.firebase.firestore.QueryDocumentSnapshot
import kotlin.math.roundToInt

object AppUtil {
    var currentUser: User = User()
    var currentAccount: AppAccount = AppAccount("","", currentUser)
    var currentOrder: Order = Order()

    fun toBook(doc: QueryDocumentSnapshot): Book {
        var bookItem = Book()
        bookItem.Author = doc["Author"].toString()
        bookItem.Counts = doc["Counts"].toString().toDouble().roundToInt()
        bookItem.Description = doc["Description"].toString()
        bookItem.Image = doc["Image"].toString()
        bookItem.Kind = doc["Kind"].toString()
        bookItem.Name = doc["Name"].toString()
        bookItem.Price = doc["Price"].toString().toDouble().roundToInt()
        bookItem.rate = doc["rate"].toString().toDouble().roundToInt()
        return  bookItem
    }
}