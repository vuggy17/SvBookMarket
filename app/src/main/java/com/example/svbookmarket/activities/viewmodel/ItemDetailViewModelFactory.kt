package com.example.svbookmarket.activities.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ItemDetailViewModelFactory(private val bundle: Bundle):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemDetailViewModel::class.java))
            return ItemDetailViewModel(bundle) as T
        throw IllegalArgumentException("Wrong view model class")
    }
}
