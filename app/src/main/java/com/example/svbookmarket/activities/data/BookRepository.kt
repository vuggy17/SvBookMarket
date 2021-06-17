package com.example.svbookmarket.activities.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.svbookmarket.activities.common.Constants.BOOK_REF
import com.example.svbookmarket.activities.model.Book
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


@Singleton
class BookRepository @Inject constructor(
    @Named(BOOK_REF) private val bookCollRef: CollectionReference
) {

    private var _books = MutableLiveData<MutableList<Book>>()
    val books get() = _books

    fun getBooksFromCloudFirestore1(): Query {
        return bookCollRef.orderBy(
            "Name",
            Query.Direction.DESCENDING
        )
    }


    init {
        Log.i("WTF", "book repo created")
    }
}


// giu class nay de sau nay lam loader
//    fun getBookFrom() = flow {
//        emit(Loading())
//        emit(
//            Success(
//                getBooksFromCloudFirestore()
//            )
//        )
//    }.catch { error ->
//        error.message?.let { errorMessage ->
//            emit(Failure(errorMessage))
//        }
//    }


// giu class nay de sau nay lam loader
//    suspend fun getBooksFromCloudFirestore(): List<Book> {
//        return bookCollRef.orderBy(
//            "Name",
//            Query.Direction.DESCENDING
//        )
//            .get().await().documents.mapNotNull { doc ->
//                doc.toObject(Book::class.java)
//            }
//    }
