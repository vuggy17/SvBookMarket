package com.example.svbookmarket.activities.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.svbookmarket.activities.model.Cart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository) :ViewModel() {
    private var _cart = MutableLiveData<MutableList<Cart>>()
    val cart get() = _cart

    fun updateData(list:MutableList<Cart>){
        viewModelScope.launch {
            cartRepository.updateData(list)
            _cart.postValue(list)
        }
    }

    init {
        viewModelScope.launch {
            _cart.postValue(cartRepository.getCart())
        }

    }

}