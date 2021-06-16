package com.example.svbookmarket.activities.data

import android.util.Log
import com.example.svbookmarket.activities.model.Book
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

public class FullBookList private constructor(var lstFullBook: MutableList<Book> = mutableListOf()) {

    init {
        getDataBySnapshot()
    }

    private fun getDataBySnapshot() {
        var ref = FirebaseFirestore.getInstance().collection("books")
        ref.addSnapshotListener { snapshot, e ->

            e?.let {
                Log.d("K-error", e.message!!)
                return@addSnapshotListener
            }
//            for (dc in snapshot!!.documentChanges)
//            {
//                when (dc.type) {
//                    DocumentChange.Type.ADDED -> {
//                        val data : MutableMap<String?, Any?> = dc.document.data;
//                        lstFullBook.add(Book(dc.document.id, Uri.parse(data["Image"].toString()), data["Name"].toString(),
//                            data["Author"].toString(), data["Price"].toString().toLong(), data["rate"].toString().toDouble(),
//                            data["Kind"].toString(), data["Counts"].toString().toLong(), data["Description"].toString()))
//                    }
//                    DocumentChange.Type.MODIFIED -> {
//                        val data: MutableMap<String?, Any?> = dc.document.data;
//                        Log.d("000000000000000000000", "dcm")
//                        for(i in 0 until lstFullBook.size)
//                        {
//                            if (lstFullBook[i].id == dc.document.id) {
//                                lstFullBook[i].Name =  data["Name"].toString()
//                                lstFullBook[i].Author =  data["Author"].toString()
//                                lstFullBook[i].Kind =  data["Kind"].toString()
//                                lstFullBook[i].Description = data["Description"].toString()
//                                lstFullBook[i].Image= Uri.parse(data["Image"].toString())
//                                lstFullBook[i].Price = data["Price"].toString().toLong()
//                                lstFullBook[i].Counts = data["Counts"].toString().toLong()
//                                lstFullBook[i].rate = data["rate"].toString().toDouble()
//                                break;
//                            }
//                        }
//                    }
//                    DocumentChange.Type.REMOVED -> {
//                        val data: MutableMap<String?, Any?> = dc.document.data;
//                        for(i in 0 until lstFullBook.size-1)
//                        {
//                            if (lstFullBook[i].id == dc.document.id) {
//                                lstFullBook.removeAt(i)
//                                break;
//                            }
//                        }
//                    }
//                }
//
//            }

        }

    }


    private object Holder {
        val INSTANCE = FullBookList()
    }

    companion object {

        fun getInstance(): FullBookList {
            return Holder.INSTANCE
        }

//        private fun getDataFromDb(): MutableList<Book> {
//            val lstFullBook: MutableList<Book> = mutableListOf()
//            val db: FirebaseFirestore = FirebaseFirestore.getInstance()
//            val ref = db.collection("books")
//                .get()
//                .addOnSuccessListener { result ->
//                    for (document in result) {
//                        val doc = document.data;
//                        val authors: String = document.get("Author").toString();
//                        val count: Long = document.get("Counts").toString().toLong();
//                        val description: String = document.get("Description").toString();
//                        val image: Uri = Uri.parse(document.get("Image").toString());
//                        val kind: String = document.get("Kind").toString();
//                        val name: String = document.get("Name").toString();
//                        val rate: Double = document.get("rate").toString().toDouble();
//                        val price: Long = document.get("Price").toString().toLong();
//                        val book: Book = Book(
//                            document.id,
//                            image,
//                            name,
//                            authors,
//                            price,
//                            rate,
//                            kind,
//                            count,
//                            description
//                        )
//                        lstFullBook.add(book)
//                    }
//                }
//
//            return lstFullBook
//        }

    }
}