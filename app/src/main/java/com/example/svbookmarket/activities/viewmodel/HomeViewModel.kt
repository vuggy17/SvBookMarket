package com.example.svbookmarket.activities.viewmodel

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.common.Constants.CATEGORY.*
import com.example.svbookmarket.activities.common.Constants.VMTAG
import com.example.svbookmarket.activities.data.AdsRepository
import com.example.svbookmarket.activities.data.BookRepository
import com.example.svbookmarket.activities.data.CategoryRepository
import com.example.svbookmarket.activities.data.Response
import com.example.svbookmarket.activities.model.Book
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    adsRepository: AdsRepository,
    categoryRepository: CategoryRepository
) : ViewModel() {
    private var _books = MutableLiveData<MutableList<Book>>()
    val books get() = _books

    // giu lai funtion nay de sau nay lam loader
//    fun getBookFrom() = liveData(Dispatchers.IO) {
//        bookRepository.getBookFrom().collect { respone ->
//            Log.i(VMTAG, "$respone")
//            emit(respone)
//        }
//    }

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
                   bookItem.id = doc.id
                   bookList.add(bookItem)

               }
               books.value = bookList
           }

       })
                        Log.d(VMTAG, "getbookcalled")

        return books
    }

    private var _ads = adsRepository.ads
    val ads get() = _ads

    private var _category = categoryRepository.category
    val category get() = _category

    fun getBooksOfCategory(categoryName: String): ArrayList<Book> {
        val filted =  _books.value?.filter { category ->
            category.Kind == categoryName
        }
        return ArrayList(filted)
    }

    init {
        Log.i("sharedvm", "sharedViewmodel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SHARED_VM", "shared vm cleared!")
    }
}