package com.example.svbookmarket.activities.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.Book
import com.makeramen.roundedimageview.RoundedImageView

class FeaturedAdapter(
    private val dataSet: MutableList<Book>,
    private val listener: OnBookClickLitener
) :
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
                it.bookTitle.text = Name
                it.bookAuthor.text = Author
                it.bookPrice.text =
                    holder.itemView.context.getString(R.string.price_format_vn, Price.toString())
                it.bookRate.text = rate.toString()

                Glide
                    .with(holder.itemView)
                    .load(Image)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(it.bookImage);

                it.itemView.setOnClickListener{listener.onBookClick(this)}
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun addBooks(book: List<Book>) {
        if (this.dataSet.isNotEmpty()) {
            this.dataSet.clear()
        }
        this.dataSet.addAll(book)
        notifyDataSetChanged()
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookImage: RoundedImageView = view.findViewById(R.id.BookImage)
        val bookTitle: TextView = view.findViewById(R.id.bookTitle)
        val bookAuthor: TextView = view.findViewById(R.id.bookAuthor)
        val bookPrice: TextView = view.findViewById(R.id.bookPrice)
        val bookRate: TextView = view.findViewById(R.id.bookRate)
    }

    interface OnBookClickLitener {
        fun onBookClick(book: Book)
    }
}