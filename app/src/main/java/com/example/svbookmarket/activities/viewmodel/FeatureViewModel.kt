package com.example.svbookmarket.activities.viewmodel

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import coil.Coil
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Scale
import com.example.svbookmarket.activities.common.Constants.VMTAG
import com.example.svbookmarket.activities.data.BookRepository
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.model.DominantColors
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FeatureViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    @ApplicationContext val context: Context
) : ViewModel() {

    private var _books = MutableLiveData<MutableList<Book>>()
    val books get() = _books

    private var _top5Books = MutableLiveData<List<Book>>()
    val top5 get() = _top5Books

    private var _top10Books = MutableLiveData<List<Book>>()
    val top10 get() = _top10Books

    fun getBookFrom(): MutableLiveData<MutableList<Book>> {
        bookRepository.getBooksFromCloudFirestore1().addSnapshotListener(object :
            EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    Log.w(VMTAG, "Listen failed.", error)
                    return
                }

                val bookList: MutableList<Book> = ArrayList()
                for (doc in value!!) {
                    var bookItem = doc.toObject(Book::class.java)
                    bookList.add(bookItem)

                }
                books.value = bookList
            }

        })
        setTop5()
        setTop10()
        return books
    }


    fun setTop5() {
        _top5Books.value = _books.value?.take(5)
        _books.value?.drop(5)
    }

    fun setTop10() {
        _top5Books.value = _books.value?.take(10)
        _books.value?.drop(10)

    }

    private fun isColorValid(color: Color): Boolean = true

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun calculateDominantColor(url: String): DominantColors? {
        // Otherwise we calculate the swatches in the image, and return the first valid color
        return calculateSwatchesInImage(context, url)
            // First we want to sort the list by the color's population
            .sortedByDescending { swatch -> swatch.population }
            // Then we want to find the first valid color
            .firstOrNull { swatch -> isColorValid(Color.valueOf(swatch.rgb)) }
            // If we found a valid swatch, wrap it in a [DominantColors]
            ?.let { swatch ->
                DominantColors(
                    color = Color.valueOf(swatch.rgb),
                    onColor = Color.valueOf(swatch.bodyTextColor)
                )
            }

    }

    private suspend fun calculateSwatchesInImage(
        context: Context,
        imageUrl: String
    ): List<Palette.Swatch> {
        val r = ImageRequest.Builder(context)
            .data(imageUrl)
            // We scale the image to cover 128px x 128px (i.e. min dimension == 128px)
            .size(128).scale(Scale.FILL)
            // Disable hardware bitmaps, since Palette uses Bitmap.getPixels()
            .allowHardware(false)
            .build()

        val bitmap = when (val result = Coil.execute(r)) {
            is SuccessResult -> result.drawable.toBitmap()
            else -> null
        }

        return bitmap?.let {
            withContext(Dispatchers.Default) {
                val palette = Palette.Builder(bitmap)
                    // Disable any bitmap resizing in Palette. We've already loaded an appropriately
                    // sized bitmap through Coil
                    .resizeBitmapArea(0)
                    // Clear any built-in filters. We want the unfiltered dominant color
                    .clearFilters()
                    // We reduce the maximum color count down to 8
                    .maximumColorCount(8)
                    .generate()

                palette.swatches
            }
        } ?: emptyList()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getColor(url: String) {
        viewModelScope.launch {
            calculateDominantColor(url)
        }
    }

    init {
        getBookFrom()
    }
}