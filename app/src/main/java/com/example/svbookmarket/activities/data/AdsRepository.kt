package com.example.svbookmarket.activities.data

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.svbookmarket.activities.model.Advertise
import javax.inject.Inject

class AdsRepository @Inject constructor( /*database */) {

    private var _ads = MutableLiveData<MutableList<Advertise>>()
    val ads get() = _ads


    /**
     * retrieve data from db
     */
//    suspend fun load(){
//        with(Dispatchers.IO){
//       // do something with db
//
//        }
//    }


    /**
     * bellow is test function region
     */
    fun loadAds() {
        _ads.value = mutableListOf<Advertise>(
            Advertise("25/4/2021 - 30/4/2021", "Save off all product \nup to 70%", Uri.parse("")),
            Advertise("21/4/2021 - 30/4/2021", "Save off all product \nup to 20%", Uri.parse("")),
            Advertise("20/4/2021 - 30/4/2021", "Save off all product \nup to 50%", Uri.parse("")),
        )
    }
    init {
        loadAds()
    }

}