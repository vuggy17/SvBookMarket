package com.example.svbookmarket.activities.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.svbookmarket.activities.di.CartCache
import com.example.svbookmarket.activities.model.Cart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.svbookmarket.activities.model.UserDeliverAddress as MyAddress

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


    suspend fun updateCartWithAddress(newAddress: MyAddress, isChosen:Boolean){
        //call database to update current address
    }

    suspend fun delete(item: Cart) {
        withContext(Dispatchers.IO) {
            Log.i("my database", "Item deleted ${item.name}")
            cartCache.remove(item)
        }
    }

    suspend fun getCart(): MutableList<Cart> {
        val COUNT = 4 // for fake data generation

        val cached = cartCache.get()
        Log.i("repository", "${cached?.size}")
        if (cached != null && cartCache.get()?.size != 0){
            Log.i("Cartrepo", "${cached?.size} run before return cached")

            return cached!!

        }

        // fetch data
        val freshCart = withContext(Dispatchers.IO) {
            var item = mutableListOf<Cart>()
            for (i in 1..COUNT) {
                item.add(
                    Cart(
                        "name", "name", "name", COUNT + 3, (COUNT * i * 100)
                    )
                )
            }
            item
        }
        Log.i("Cartrepo", "${freshCart.size}")
        cartCache.set(freshCart)
        return freshCart


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
