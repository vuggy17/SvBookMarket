package com.example.svbookmarket.activities.data

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.svbookmarket.activities.HomeActivity
import com.example.svbookmarket.activities.LoginActivity
import com.example.svbookmarket.activities.common.Constants
import com.example.svbookmarket.activities.model.AppAccount
import com.example.svbookmarket.activities.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.ktx.Firebase
import java.lang.Thread.sleep
import javax.inject.Inject
import javax.inject.Named

class UserRepository @Inject constructor(
    @Named(Constants.USERS_REF) private val userCollRef: CollectionReference
) {
    var user = User()

    fun loadData() {
       user = LoginActivity.recentAccountLogin.user
    }
}

