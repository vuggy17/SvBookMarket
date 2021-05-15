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

class FeaturedAdapter(private val dataSet: ArrayList<Book>) :
    RecyclerView.Adapter<FeaturedAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookImage: AppCompatImageView = view.findViewById(R.id.bookImage)
        val bookTitle: TextView = view.findViewById(R.id.bookTitle)
        val bookAuthor: TextView = view.findViewById(R.id.bookAuthor)
        val bookPrice: TextView = view.findViewById(R.id.bookPrice)
        val bookRating: RatingBar = view.findViewById(R.id.bookRatingBar)
        val bookRatesCount: TextView = view.findViewById(R.id.bookRatesCount)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_book, parent, false)
//        view.findViewById<View>(R.layout.card_book)
        view.findViewById<View>(R.id.cb_card).setOnClickListener({})
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
                bookPrice.text = bookItem.price.toString() +" VNĐ"
                bookRating.rating = bookItem.rating.toFloat()
                bookRatesCount.text = bookItem.ratesCount.toString()+ " reviews"
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
//    public  fun startIntent(type:String){
//        val intent = when(type){
//            "menu"-> Intent(this, MenuActivity::class.java)
//            "search"-> Intent(this, SearchActivity::class.java)
//            "cart"-> Intent(this, CartActivity::class.java)
//            "category"-> Intent(this, CategoryActivity::class.java)
//            else-> Intent(this, FeatureActivity::class.java)
//        }
//        startActivity(intent)
//    }
}