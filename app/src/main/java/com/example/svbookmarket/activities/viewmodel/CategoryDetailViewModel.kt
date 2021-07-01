package com.example.svbookmarket.activities.viewmodel

import androidx.lifecycle.ViewModel
import com.example.svbookmarket.activities.data.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor(private val categoryRepository: CategoryRepository) :
    ViewModel() {

    fun getCollectionImgSource(categoryName: String): Int {
        return categoryRepository.getCollectionImgSource(categoryName)
    }

}