package com.example.svbookmarket.activities.di

import com.example.svbookmarket.activities.common.Constants.ADDRESS_REF
import com.example.svbookmarket.activities.common.Constants.BOOK_REF
import com.example.svbookmarket.activities.common.Constants.USERS_REF
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Named(BOOK_REF)
    fun provideCloudFirestoreBooksRef(rootRef: FirebaseFirestore): CollectionReference {
        return rootRef.collection(BOOK_REF)
    }

    @Provides
    @Named(USERS_REF)
    fun provideCloudFirestoreUsersRef(rootRef: FirebaseFirestore):CollectionReference{
        return rootRef.collection(USERS_REF)
    }

}