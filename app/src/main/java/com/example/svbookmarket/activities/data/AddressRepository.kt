package com.example.svbookmarket.activities.data

import android.util.Log
import com.example.svbookmarket.activities.common.Constants.ADDRESS_REF
import com.example.svbookmarket.activities.common.Constants.USERS_REF
import com.example.svbookmarket.activities.common.Constants.VMTAG
import com.example.svbookmarket.activities.model.AppAccount
import com.example.svbookmarket.activities.model.UserDeliverAddress
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named


class AddressRepository @Inject constructor(
    @Named(USERS_REF) private val accountColRef: CollectionReference
) {

    private val testAddress = UserDeliverAddress(
        "",
        "khuong duy",
        "012321322",
        "lams son",
        "cam thanh bac",
        "khanh hoa",
        false
    )


    fun setNewAddress(user: AppAccount, address: UserDeliverAddress) {
        accountColRef.document(user.email).collection(ADDRESS_REF).document().set(testAddress)
        Log.i(VMTAG, "$address")
    }

    fun getAddress(user: AppAccount) {
        accountColRef.document(user.email).collection(ADDRESS_REF).document().get()
            .addOnSuccessListener { }
    }

    fun getChosenAddress(user: AppAccount): Query {
        return FirebaseFirestore.getInstance().collection("accounts").document(user.email)
            .collection("userDeliverAddresses")
    }

    suspend fun update(item: UserDeliverAddress) {
        withContext(Dispatchers.IO) {
            // TODO: 13/06/2021 update data to db
        }
    }

    init {
    }

}