package com.example.svbookmarket.activities.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.svbookmarket.activities.model.Book

class CategoryViewModelFactory(private val books:MutableList<Book>):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java))
            return CategoryViewModel(books) as T
        throw IllegalArgumentException("Wrong view model class")
    }
}