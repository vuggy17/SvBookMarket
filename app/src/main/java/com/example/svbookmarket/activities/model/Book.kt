package com.example.svbookmarket.activities.model

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable


data class Book(
    var id: String? = "1",
    var imageURL: Uri? = Uri.EMPTY,
    var title: String? = "1",
    var author: String? = "1",
    var price: Long = 1L,
    var rating: Double = 1.0,
    var kind: String? ="1",
    var ratesCount: Long = 1L,
    var description: String? = "1" ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(Uri::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeParcelable(imageURL, flags)
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeLong(price)
        parcel.writeDouble(rating)
        parcel.writeString(kind)
        parcel.writeLong(ratesCount)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}

