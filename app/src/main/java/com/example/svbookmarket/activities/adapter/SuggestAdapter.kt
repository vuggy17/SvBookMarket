package com.example.svbookmarket.activities.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.data.Feature_Item

class SuggestAdapter(context:Context, private val lstSuggestCard: MutableList<Feature_Item>): RecyclerView.Adapter<SuggestAdapter.SuggestViewHolder>() {
    class SuggestViewHolder(SuggestCard: View) : RecyclerView.ViewHolder(SuggestCard){
        val imgBookCover: ImageView = SuggestCard.findViewById(R.id.imgSuggest)
        val tvBookName: TextView = SuggestCard.findViewById(R.id.tvBookSuggest)
        val tvAuthor: TextView = SuggestCard.findViewById(R.id.tvAuthorSuggest)
        val tvNumReviews: TextView = SuggestCard.findViewById(R.id.tvNumReviews)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_suggest, parent, false)
        return SuggestViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SuggestViewHolder, position: Int) {
        holder.imgBookCover.setImageResource(lstSuggestCard[position].imgBookCover)
        holder.tvAuthor.text = lstSuggestCard[position].textAuthor
        holder.tvBookName.text = lstSuggestCard[position].textBookName
        holder.tvNumReviews.text = lstSuggestCard[position].NumReviews
    }

    override fun getItemCount() = lstSuggestCard.size
}