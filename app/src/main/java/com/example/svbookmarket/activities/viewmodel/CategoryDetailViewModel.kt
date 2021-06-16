package com.example.svbookmarket.activities.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.common.Constants.CATEGORY.*
import dagger.hilt.android.lifecycle.HiltViewModel

class CategoryDetailViewModel:ViewModel() {


    @DrawableRes
    fun getCollectionImgSource(categoryName: String): Int {
        return when (categoryName) {
          COMIC.toString() -> R.drawable.img_comic
           FICTION.toString() -> R.drawable.img_fiction
          NOVEL.toString() -> R.drawable.img_novel
          BUSINESS.toString() -> R.drawable.img_business
          TECHNOLOGY.toString() -> R.drawable.img_tech
            else -> R.drawable.img_art
        }
    }

}