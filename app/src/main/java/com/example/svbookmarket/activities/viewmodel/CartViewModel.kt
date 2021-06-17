package com.example.svbookmarket.activities.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.svbookmarket.activities.data.CartRepository
import com.example.svbookmarket.activities.model.Cart
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

    private fun loadCartItem()
    {
            _cartItem.value = cartRepository.getCart(CurrentUserInfo.getInstance().currentProfile)

    }

    fun updateData(list:MutableList<Cart>){
        viewModelScope.launch {
            cartRepository.updateData(list)
            _cartItem.postValue(list)
        }
    }
}