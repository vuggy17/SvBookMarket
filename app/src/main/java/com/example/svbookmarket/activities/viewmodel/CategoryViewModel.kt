package com.example.svbookmarket.activities.viewmodel

import android.net.Uri
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.CategoryActivity
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.model.Category

class CategoryViewModel(itemPassedIn: MutableList<Book>) :ViewModel() {

    private var _category = MutableLiveData<MutableList<Category>>()
    val category get() = _category

    private var _coverImg = Uri.EMPTY
    val coverImg get() = _coverImg

    private var _books = MutableLiveData<MutableList<Book>>()
    val books get() = _books

    //public function
    fun getBooksOfCategory(category: String):ArrayList<Book> {
        val booksOfCategory = _books.value?.filter { category == it.kind }
        return ArrayList(booksOfCategory)
    }

//    @DrawableRes
//    fun getCollectionImgSource(categoryName: String): Int {
//        return when (categoryName) {
//            CategoryActivity.Companion.CATEGORY.COMIC.toString() -> R.drawable.img_comic
//            CategoryActivity.Companion.CATEGORY.FICTION.toString() -> R.drawable.img_fiction
//            CategoryActivity.Companion.CATEGORY.NOVEL.toString() -> R.drawable.img_novel
//            CategoryActivity.Companion.CATEGORY.BUSINESS.toString() -> R.drawable.img_business
//            CategoryActivity.Companion.CATEGORY.TECHNOLOGY.toString() -> R.drawable.img_tech
//            else -> R.drawable.img_art
//        }
//    }

    //retrieve data from db
    private fun loadCategoryList() {
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
        loadCategoryList()
        _books.value = itemPassedIn
    }
}