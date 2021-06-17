package com.example.svbookmarket.activities.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.Book
import com.google.android.material.card.MaterialCardView

class SuggestAdapter(private val books: MutableList<Book>,
private val listener: OnSuggestClickListener) :
    RecyclerView.Adapter<SuggestAdapter.SuggestViewHolder>() {
    val colors = mutableListOf<String>(
        "#9ca17d",
        "#7E8A97",
        "#7B6079",
        "#A7D0CD",
        "#DE8971",
        "#BB8082",
        "#70AF85",

    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_suggest, parent, false)
        return SuggestViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SuggestViewHolder, position: Int) {
        with(books[position]) {
            holder.let {
                Glide
                    .with(holder.itemView)
                    .load(Image)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(it.thumbnail);
                it.title.text = Name
                it.author.text = Author
                it.des.text = Description

                if (it.itemView is MaterialCardView) {
                    val colorString = getBackgroundColor(position)
                    it.itemView.setCardBackgroundColor(Color.parseColor(colorString))

                    it.itemView.setOnClickListener{
                        listener.onSuggestClick(this)
                    }
                }

            }
        }
    }

    private fun getBackgroundColor(position: Int): String {
        return colors[position % colors.size]
    }

    fun addBooks(book: List<Book>) {
        if (this.books.isNotEmpty()) {
            this.books.clear()
        }
        this.books.addAll(book)
        notifyDataSetChanged()
    }

    override fun getItemCount() = books.size

    inner class SuggestViewHolder(ViewHolder: View) : RecyclerView.ViewHolder(ViewHolder) {
        val thumbnail: ImageView = ViewHolder.findViewById(R.id.imgSuggest)
        val title: TextView = ViewHolder.findViewById(R.id.tvBookSuggest)
        val author: TextView = ViewHolder.findViewById(R.id.tvAuthorSuggest)
        val des: TextView = ViewHolder.findViewById(R.id.sg_description)
    }

    interface OnSuggestClickListener {
        fun onSuggestClick(book: Book)
    }
}