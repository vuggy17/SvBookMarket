package com.example.svbookmarket.activities.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.svbookmarket.activities.common.Constants
import com.example.svbookmarket.activities.data.CartRepository
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.model.Cart
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository) :ViewModel() {
    private var _cartItem = MutableLiveData<MutableList<Cart>>()
    val cartItem get() = _cartItem


    init{
        loadCartItem()
    }

    fun loadCartItem()
    {
        cartRepository.getCart(CurrentUserInfo.getInstance().currentProfile).addSnapshotListener (object :
            EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    Log.w(Constants.VMTAG, "Listen failed.", error)
                    return
                }

                val cartList: MutableList<Cart> = ArrayList()
                for (doc in value!!) {
                    var bool = doc.data["isChose"].toString() == "true"
                    var item = Cart("", doc.data["image"].toString(),doc.data["title"].toString(), doc.data["author"].toString(),
                        doc.data["Quantity"].toString().toInt(), doc.data["price"].toString().toLong(), bool)
                    item.id = doc.id
                    cartList.add(item)
                }
                cartItem.value = cartList
            }

        })
    }


    fun updateQuantity(id: String, plusOrMinus:Boolean)
    {
        cartRepository.newQuantityForItem(id, plusOrMinus, CurrentUserInfo.getInstance().currentProfile)
    }

    fun updateData(list:MutableList<Cart>){
        viewModelScope.launch {
            cartRepository.updateData(list)
            _cartItem.postValue(list)
        }
    }
    fun deleteCart(id: String)
    {
        cartRepository.deleteCart(CurrentUserInfo.getInstance().currentProfile, id)
    }

    fun isChoseChange(id: String, isChose: Boolean)
    {
        cartRepository.isChoseChange(CurrentUserInfo.getInstance().currentProfile, id, isChose)
    }
}