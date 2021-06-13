package com.example.svbookmarket.activities.data

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.svbookmarket.activities.model.Advertise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AdsRepository @Inject constructor( /*database */) {

    private var _ads = MutableLiveData<MutableList<Advertise>>()
    val ads get() = _ads

    suspend fun loadAds() {
        withContext(Dispatchers.IO){
            _ads.value = mutableListOf<Advertise>(
                Advertise("25/4/2021 - 30/4/2021", "Save off all product \nup to 70%", Uri.parse("")),
                Advertise("21/4/2021 - 30/4/2021", "Save off all product \nup to 20%", Uri.parse("")),
                Advertise("20/4/2021 - 30/4/2021", "Save off all product \nup to 50%", Uri.parse("")),
            )
        }

    }

}