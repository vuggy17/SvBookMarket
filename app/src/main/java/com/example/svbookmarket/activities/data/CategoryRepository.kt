package com.example.svbookmarket.activities.data

import androidx.annotation.DrawableRes
import androidx.lifecycle.MutableLiveData
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.CategoryActivity
import com.example.svbookmarket.activities.model.Category
import javax.inject.Inject

class CategoryRepository @Inject constructor( /*database */) {

    private var _category = MutableLiveData<MutableList<Category>>()
    val category get() = _category


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


}