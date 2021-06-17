package com.example.svbookmarket.activities.data

import android.net.Uri
import android.util.Log
import com.example.svbookmarket.activities.di.CartCache
import com.example.svbookmarket.activities.model.AppAccount
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.model.Cart
import com.example.svbookmarket.activities.model.CartModel
import com.google.firebase.firestore.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import com.example.svbookmarket.activities.model.UserDeliverAddress as MyAddress


@Singleton
class CartRepository @Inject constructor( /*database */
    val cartCache: CartCache
) {

    /**
     * get data from database then save it into _book
     * now use temp data for test
     */
//    private var _cart = MutableLiveData<MutableList<Cart>>()
//    val cart get() = _cart

    /**
     * retrieve data from db
     */
//    suspend fun load(){
//        with(Dispatchers.IO){
//            // do something with db
//
//        }
//    }


    /**
     * bellow is test function region
     */

    /**
     * to update value of variable in background thread, use postValue
     */


    fun updateCartWithAddress(newAddress: MyAddress, isChosen:Boolean){
        //call database to update current address
    }

    fun addCart(book: Book, user: AppAccount)
    {
        val rootRef = FirebaseFirestore.getInstance()
        val docIdRef = rootRef.collection("accounts").document(user.email).collection("userCart").document(book.id!!)
        docIdRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document: DocumentSnapshot? = task.result
                if (document!!.exists()) {
                    addOldCart(book, user)
                } else {
                    addNewCart(book, user)
                }
            }
        }
    }

    private fun addOldCart(book: Book, user: AppAccount)
    {
        FirebaseFirestore.getInstance().collection("accounts").document(user.email)
            .collection("userCart").document(book.id!!).update("Quantity", FieldValue.increment(1))
    }

    private fun addNewCart(book : Book, user: AppAccount)
    {
        var newCart : MutableMap<String, *> = mutableMapOf("Quantity" to 1, "author" to book.Author, "image" to book.Image, "title" to book.Name, "isChose" to false, "price" to book.Price)
            FirebaseFirestore.getInstance().collection("accounts").document(user.email)
                .collection("userCart").document(book.id!!).set(newCart)
    }

    suspend fun delete(item: Cart) {
        withContext(Dispatchers.IO) {
            Log.i("my database", "Item deleted ${item.name}")
            cartCache.remove(item)
        }
    }

    fun getCart(user: AppAccount): MutableList<Cart> {
        val lstUserCart: MutableList<Cart> = mutableListOf()
        FirebaseFirestore.getInstance().collection("accounts").document(user.email)
            .collection("userCart").addSnapshotListener { snapshot, e ->
                e?.let {
                    Log.d("error", e.message!!)
                }
                for (dc in snapshot!!.documentChanges)
                {
                    when(dc.type)
                    {
                        DocumentChange.Type.ADDED -> {
                            var data:MutableMap<String?, Any?> = dc.document.data
                            var bool = data["isChose"].toString() == "true"
                            lstUserCart.add(
                                Cart(dc.document.id, data["image"].toString(),data["title"].toString(), data["author"].toString(),
                                    data["Quantity"].toString().toInt(), data["price"].toString().toLong(), bool)
                            )
                        }
                        DocumentChange.Type.MODIFIED -> {
                            var data:MutableMap<String?, Any?> = dc.document.data
                            var bool = data["isChose"].toString() == "true"
                            for(i in 0 until lstUserCart.size)
                            {
                                if (lstUserCart[i].id == dc.document.id)
                                {
                                    lstUserCart[i].name =  data["title"].toString()
                                    lstUserCart[i].author =  data["author"].toString()
                                    lstUserCart[i].numbers =  data["Quantity"].toString().toInt()
                                    lstUserCart[i].imgUrl = data["image"].toString()
                                    lstUserCart[i].price= data["price"].toString().toLong()
                                    lstUserCart[i].isChose = bool
                                    break;
                                }
                            }
                        }
                        DocumentChange.Type.REMOVED -> {
                            var data:MutableMap<String?, Any?> = dc.document.data
                            for(i in 0 until lstUserCart.size)
                            {
                                if (lstUserCart[i].id == dc.document.id)
                                {
                                    lstUserCart.removeAt(i)
                                }
                            }
                        }
                    }
                }
            }
        return lstUserCart
    }

    suspend fun updateData(list: MutableList<Cart>) {
        withContext(Dispatchers.IO) {
            // TODO: 13/06/2021 update data to db
           cartCache.set(list)
            Log.i("repository", "${list.size}")
        }
    }

    init {
    }

}
