package com.example.svbookmarket.activities.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.SearchResultItem

class RecentSearchAdapter(context: Context, private val lstRecentSearch: MutableList<SearchResultItem>): RecyclerView.Adapter<RecentSearchAdapter.RecentSearchViewHolder> (){
     class RecentSearchViewHolder(RecentSearchCard: View): RecyclerView.ViewHolder(RecentSearchCard)
    {
        val recentSearchName: TextView = RecentSearchCard.findViewById(R.id.tvRecentSearchName)
        val recentSearchTime: TextView = RecentSearchCard.findViewById(R.id.tvRecentSearchTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_recent_search, parent, false)
        return RecentSearchAdapter.RecentSearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
               holder.recentSearchName.text = lstRecentSearch[position].result
                holder.recentSearchTime.text = lstRecentSearch[position].timeSearch
    }

    override fun getItemCount() = lstRecentSearch.size

}