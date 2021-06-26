package com.example.svbookmarket.activities.data

import CurrentUserInfo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val user = CurrentUserInfo.getInstance().currentProfile

    private val _address = MutableLiveData<MutableList<UserDeliverAddress>>()
    val address: LiveData<MutableList<UserDeliverAddress>> get() = _address


    fun addAddress(address: UserDeliverAddress) {
        accountColRef.document(user.email).collection(ADDRESS_REF).add(address)
            .addOnSuccessListener { Log.i(VMTAG, "new address created with id: {$it.id}") }
            .addOnFailureListener { Log.i(VMTAG, "new address created failed") }
    }

    fun getAddress(user: AppAccount): CollectionReference {
        return accountColRef.document(user.email).collection(ADDRESS_REF)
    }

    fun getChosenAddress(user: AppAccount): Query {
        return FirebaseFirestore.getInstance().collection("accounts").document(user.email)
            .collection("userDeliverAddresses")
    }

    suspend fun setSelectState(item: UserDeliverAddress, state: Boolean) {
        withContext(Dispatchers.IO) {
            accountColRef.document(user.email).collection(ADDRESS_REF).document(item.id)
                .update("chose", state)
        }
    }

    suspend fun updateAddress(item: UserDeliverAddress) {
        withContext(Dispatchers.IO) {
            accountColRef.document(user.email).collection((ADDRESS_REF)).document(item.id).set(item)
        }
    }

    suspend fun deleteAddress(item: UserDeliverAddress, user: AppAccount) {
        withContext(Dispatchers.IO) {
            accountColRef.document(user.email).collection((ADDRESS_REF)).document(item.id).delete()
        }
    }
}