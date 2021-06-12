package com.example.svbookmarket.activities.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.Category

class CategoryViewModel:ViewModel() {

    private var _category = MutableLiveData<MutableList<Category>>()
    val category get() = _category

    //retrieve data from db
    private fun loadCategory() {
        _category.value = mutableListOf(
            Category(R.drawable.img_art, "Art"),
            Category(R.drawable.img_fiction, "Fiction"),
            Category(R.drawable.img_comic, "Comic"),
            Category(R.drawable.img_novel, "Novel"),
            Category(R.drawable.img_business, "Business"),
            Category(R.drawable.img_tech, "Technology"),
        )
    }

    init {
        loadCategory()
    }
}