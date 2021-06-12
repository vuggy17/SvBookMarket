package com.example.svbookmarket.activities.data

import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.example.svbookmarket.activities.model.Book
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.getField

public class FullBookList private constructor(var lstFullBook:MutableList<Book> = mutableListOf()) {

    init {
        lstFullBook = getDataFromDb()
    }



    private object Holder { val INSTANCE = FullBookList() }

    companion object {

        fun getInstance(): FullBookList{
            return Holder.INSTANCE
        }

        fun getDataFromDb() : MutableList<Book>
        {
            val lstFullBook: MutableList<Book> = mutableListOf()
            val db:FirebaseFirestore = FirebaseFirestore.getInstance()
            val ref = db.collection("books")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result)
                    {
                        val doc = document.data;
                        val authors: String = document.get("Author").toString();
                        val count: Long = document.get("Counts").toString().toLong();
                        val description : String = document.get("Description").toString();
                        val image: Uri= Uri.parse(document.get("Image").toString());
                        val kind: String = document.get("Kind").toString();
                        val name: String = document.get("Name").toString();
                        val rate: Double = document.get("rate").toString().toDouble();
                        val price: Long = document.get("Price").toString().toLong();
                        val book: Book = Book(document.id,image,name,authors,price, rate, kind, count, description)
                        lstFullBook.add(book)
                    }
            }

                return lstFullBook
        }
    }
}