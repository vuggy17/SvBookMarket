package com.example.svbookmarket.activities.data

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.svbookmarket.activities.di.CartCache
import com.example.svbookmarket.activities.model.AppAccount
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.model.Cart
import com.example.svbookmarket.activities.model.CartModel
import com.google.firebase.firestore.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.concurrent.thread
import kotlin.math.roundToInt
import com.example.svbookmarket.activities.model.UserDeliverAddress as MyAddress


@Singleton
class CartRepository @Inject constructor( /*database */ @ApplicationContext val context: Context,
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


    fun updateCartWithAddress(newAddress: MyAddress, isChosen: Boolean) {
        //call database to update current address
    }

    suspend fun addCart(book: Book, user: AppAccount) {
        val rootRef = FirebaseFirestore.getInstance()
        val docIdRef = rootRef.collection("accounts").document(user.email).collection("userCart")
            .document(book.id!!)

        docIdRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document: DocumentSnapshot? = task.result
                if (document!!.exists()) {
                    GlobalScope.launch {
                        withContext(Dispatchers.IO) {
                            addOldCart(book, user)
                        }
                    }
                } else {
                    GlobalScope.launch {
                        withContext(Dispatchers.IO) {
                            addNewCart(book, user)
                        }
                    }
                }
            }
        }
    }

    private suspend fun addOldCart(book: Book, user: AppAccount) {
        var avaiBook: Double = -1.0
        var currenOnCart: Double = -1.0
        var bookData: DocumentSnapshot = FirebaseFirestore.getInstance().collection("books").document(book.id!!).get().await()
        var currentNumInCart: DocumentSnapshot = FirebaseFirestore.getInstance().collection("accounts")
            .document(user.email).collection("userCart").document(book.id!!).get().await()

        avaiBook = bookData.data?.get("Counts").toString().toDouble()
        currenOnCart = currentNumInCart.data?.get("Quantity").toString().toDouble()

        if (avaiBook > currenOnCart && avaiBook != -1.0 && currenOnCart != -1.0 && avaiBook != 0.0) {
            FirebaseFirestore.getInstance().collection("accounts").document(user.email)
                .collection("userCart").document(book.id!!)
                .update("Quantity", FieldValue.increment(1))

            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context, "Add to cart success", Toast.LENGTH_SHORT).show()
            }
        } else {
            Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, "At max in store", Toast.LENGTH_SHORT).show()
        }
        }
    }

    private suspend fun addNewCart(book: Book, user: AppAccount) {
        var avaiBook: Double = -1.0
        var dataBook: DocumentSnapshot = FirebaseFirestore.getInstance().collection("books").document(book.id!!).get().await()
        avaiBook = dataBook.data?.get("Counts").toString().toDouble()
        if (avaiBook != -1.0 && avaiBook != 0.0) {
            var newCart: MutableMap<String, *> = mutableMapOf(
                "Quantity" to 1,
                "author" to book.Author,
                "image" to book.Image,
                "title" to book.Name,
                "isChose" to false,
                "price" to book.Price
            )
            FirebaseFirestore.getInstance().collection("accounts").document(user.email)
                .collection("userCart").document(book.id!!).set(newCart)
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context, "Add to cart success", Toast.LENGTH_SHORT).show()
            }
        }
        else
        {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context, "At max in store", Toast.LENGTH_SHORT).show()
            }
        }
    }

    suspend fun delete(item: Cart) {
        withContext(Dispatchers.IO) {
            Log.i("my database", "Item deleted ${item.name}")
            cartCache.remove(item)
        }
    }

    fun getCart(user: AppAccount): Query {
        return FirebaseFirestore.getInstance().collection("accounts").document(user.email)
            .collection("userCart")
    }

    suspend fun newQuantityForItem(id: String, plusOrMinus: Boolean, user: AppAccount) {

        var avaiBook: Double = -1.0
        var currenOnCart: Double = -1.0
        var bookData: DocumentSnapshot = FirebaseFirestore.getInstance().collection("books").document(id).get().await()
        var currentNumInCart: DocumentSnapshot = FirebaseFirestore.getInstance().collection("accounts")
            .document(user.email).collection("userCart").document(id).get().await()

        if (bookData.exists() && currentNumInCart.exists()) {
            avaiBook = bookData.data?.get("Counts").toString().toDouble()
            currenOnCart = currentNumInCart.data?.get("Quantity").toString().toDouble()

            if (currenOnCart < avaiBook && plusOrMinus && avaiBook != -1.0 && currenOnCart != -1.0) {
                FirebaseFirestore.getInstance().collection("accounts").document(user.email)
                    .collection("userCart").document(id).update("Quantity", FieldValue.increment(1))
            }
            else if (currenOnCart > 0 && !plusOrMinus && avaiBook != -1.0 && currenOnCart != -1.0 && currenOnCart<= avaiBook) {
                FirebaseFirestore.getInstance().collection("accounts").document(user.email)
                    .collection("userCart").document(id)
                    .update("Quantity", FieldValue.increment(-1))
                currenOnCart = currentNumInCart.data?.get("Quantity").toString().toDouble().roundToInt() - 1.0
                if (currenOnCart == 0.0) {
                    deleteCart(user, id)
                }
            } else if (currenOnCart == avaiBook && avaiBook != -1.0 && currenOnCart != -1.0) {
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(context, "At max in store", Toast.LENGTH_SHORT).show()
                }
            }
                else if (currenOnCart > avaiBook && avaiBook != -1.0 && currenOnCart != -1.0)
            {
                FirebaseFirestore.getInstance().collection("accounts").document(user.email)
                    .collection("userCart").document(id)
                    .update("Quantity", avaiBook)
                }
        }
    }

    suspend fun updateData(list: MutableList<Cart>) {
        withContext(Dispatchers.IO) {
            // TODO: 13/06/2021 update data to db
            cartCache.set(list)
            Log.i("repository", "${list.size}")
        }
    }

    fun deleteCart(user: AppAccount, id: String) {
        FirebaseFirestore.getInstance().collection("accounts").document(user.email)
            .collection("userCart").document(id).delete()
    }

    fun isChoseChange(user: AppAccount, id: String, isChose: Boolean) {
        FirebaseFirestore.getInstance().collection("accounts").document(user.email)
            .collection("userCart").document(id).update("isChose", isChose)
    }

    suspend fun moveToUserOrDer(user: AppAccount, listNeedToMove: MutableList<Cart>, deliverAddress: MyAddress)
    {
        val mapOfAddress = hashMapOf<String, Any>("addressId" to deliverAddress.id, "addressLane" to deliverAddress.addressLane, "city" to deliverAddress.city,
            "district" to deliverAddress.district,"fullName" to deliverAddress.fullName,"phoneNumber" to deliverAddress.phoneNumber, "userId" to user.email)
       val newOrderId : String = FirebaseFirestore.getInstance().collection("accounts").document(user.email)
            .collection("userOrder").add(mapOfAddress).await().get().await().id

        for (i in 0 until listNeedToMove.size) {

            val mapOfOrder = hashMapOf<String, Any>("Quantity" to listNeedToMove[i].numbers, "author" to listNeedToMove[i].author, "image" to listNeedToMove[i].imgUrl,
                "price" to listNeedToMove[i].price,"title" to listNeedToMove[i].name)
            FirebaseFirestore.getInstance().collection("accounts").document(user.email)
                .collection("userOrder").document(newOrderId).collection("books").document(listNeedToMove[i].id).set(mapOfAddress)
        }
    }

    suspend fun clickBuy()
    {

    }

    init {
    }
}
