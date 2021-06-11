package com.example.svbookmarket.activities.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.ItemDetailActivity
import com.example.svbookmarket.activities.model.Book
import com.makeramen.roundedimageview.RoundedImageView

class FeaturedAdapter(private val dataSet: ArrayList<Book>) :
    RecyclerView.Adapter<FeaturedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_book, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(dataSet[holder.adapterPosition]) {
            holder.let {
                it.bookTitle.text = title
                it.bookAuthor.text = author
                it.bookPrice.text = "đ $price"
                it.bookRate.text = rating.toString()

                Glide
                    .with(holder.itemView)
                    .load(imageURL)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(it.bookImage);


                //set click listener
                it.itemView.setOnClickListener {
                    var intentDetail =
                        Intent(holder.itemView.context, ItemDetailActivity::class.java)
                    var bundle = Bundle()
                    bundle.putString(ItemDetailActivity.TITLE, holder.bookTitle.text.toString())
                    bundle.putString(ItemDetailActivity.AUTHOR, holder.bookTitle.text.toString())
                    bundle.putString(ItemDetailActivity.PRICE, holder.bookPrice.text.toString())
                    bundle.putString(ItemDetailActivity.RATEPOINT, holder.bookRate.text.toString())
                    bundle.putString(ItemDetailActivity.THUMBNAIL_URL, imageURL.toString())
                    bundle.putString(ItemDetailActivity.DESCRIPTION, description)

                    intentDetail.putExtra("Bundle", bundle)
                    startActivity(holder.itemView.context, intentDetail, bundle);
                }
            }

        }

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookImage: RoundedImageView = view.findViewById(R.id.BookImage)
        val bookTitle: TextView = view.findViewById(R.id.bookTitle)
        val bookAuthor: TextView = view.findViewById(R.id.bookAuthor)
        val bookPrice: TextView = view.findViewById(R.id.bookPrice)
        val bookRate: TextView = view.findViewById(R.id.tvRate)
    }
}