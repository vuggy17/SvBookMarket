package com.example.svbookmarket.activities.viewmodel

import android.net.Uri
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.CategoryActivity
import com.example.svbookmarket.activities.data.AdsRepository
import com.example.svbookmarket.activities.data.BookRepository
import com.example.svbookmarket.activities.data.CategoryRepository
import com.example.svbookmarket.activities.model.Advertise
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    adsRepository: AdsRepository,
    categoryRepository: CategoryRepository
) : ViewModel() {
    private var _books = bookRepository.books
    val books get() = _books

    private var _ads = adsRepository.ads
    val ads get() = _ads

    private var _category = categoryRepository.category
    val category get() = _category

    //public function
    fun getBooksOfCategory(category:String) = bookRepository.getBooksOfCategory(category)

    @DrawableRes
    fun getCollectionImgSource(categoryName: String): Int {
        return when (categoryName) {
            CategoryActivity.Companion.CATEGORY.COMIC.toString() -> R.drawable.img_comic
            CategoryActivity.Companion.CATEGORY.FICTION.toString() -> R.drawable.img_fiction
            CategoryActivity.Companion.CATEGORY.NOVEL.toString() -> R.drawable.img_novel
            CategoryActivity.Companion.CATEGORY.BUSINESS.toString() -> R.drawable.img_business
            CategoryActivity.Companion.CATEGORY.TECHNOLOGY.toString() -> R.drawable.img_tech
            else -> R.drawable.img_art
        }
    }

    init {
        Log.i("sharedvm", "sharedViewmodel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SHARED_VM", "shared vm cleared!")
    }
}