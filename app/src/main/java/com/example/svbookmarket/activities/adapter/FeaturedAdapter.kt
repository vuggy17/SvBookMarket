package com.example.svbookmarket.activities.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.*
import com.example.svbookmarket.activities.model.Book
import com.makeramen.roundedimageview.RoundedImageView

class FeaturedAdapter(private val dataSet: ArrayList<Book>) :
    RecyclerView.Adapter<FeaturedAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookImage: RoundedImageView = view.findViewById(R.id.BookImage)
        val bookTitle: TextView = view.findViewById(R.id.bookTitle)
        val bookAuthor: TextView = view.findViewById(R.id.bookAuthor)
        val bookPrice: TextView = view.findViewById(R.id.bookPrice)
        val bookRatesCount: TextView = view.findViewById(R.id.tvRate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_book, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookItem: Book= dataSet[position]
        holder.itemView.apply {
            holder.apply {
                // load image from database here!!!
                bookTitle.text = bookItem.title
                bookAuthor.text = bookItem.author
                bookPrice.text = "đ" + bookItem.price.toString()
                bookRatesCount.text = bookItem.rating.toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}