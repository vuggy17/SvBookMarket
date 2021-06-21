package com.example.svbookmarket.activities.data

import android.util.Log
import com.example.svbookmarket.activities.model.AppAccount
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.svbookmarket.activities.model.UserDeliverAddress as MyAddress

class AddressRepository @Inject constructor(/*database */) {


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



    suspend fun delete(item: MyAddress) {
        withContext(Dispatchers.IO) {
            Log.i("my database", "Item deleted ${item.fullName}")
        }
    }

    suspend fun getAddress(): MutableList<MyAddress> {
        val COUNT = 4 // for fake data generation

        // fetch data
        val freshAdds = withContext(Dispatchers.IO) {
            var item = mutableListOf<MyAddress>()
            for (i in 1..COUNT) {
                item.add(
                   MyAddress(
                        "Khuong Duy",
                        "0869256174",
                        "Ktx khu A, DHQG",
                        "Phuong Linh Trung",
                        "Quan Thu Duc",
                        "Tp.Ho Chi Minh"
                    ),
                )
            }
            item
        }
        return freshAdds


    }

    fun getChosenAddress(user: AppAccount) : Query
    {
       return FirebaseFirestore.getInstance().collection("accounts").document(user.email).collection("userDeliverAddresses")
    }

    suspend fun update(item: MyAddress) {
        withContext(Dispatchers.IO) {
            // TODO: 13/06/2021 update data to db
        }
    }

    init {
    }

}