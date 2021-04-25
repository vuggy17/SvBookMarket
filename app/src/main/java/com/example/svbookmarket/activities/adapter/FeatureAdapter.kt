package com.example.svbookmarket.activities.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.data.Feature_Item
import kotlinx.android.synthetic.main.card_feature.*

class FeatureAdapter (private val lstFeatureCard: List<Feature_Item>): RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder>() {

    class FeatureViewHolder(FeatureCard: View): RecyclerView.ViewHolder(FeatureCard){
        val imgBookCover: ImageView = FeatureCard.findViewById(R.id.imgBookCover)
        val tvAuthor: TextView = FeatureCard.findViewById(R.id.tvAuthor)
        val tvBookName: TextView = FeatureCard.findViewById(R.id.tvBookName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.activity_feature, parent, false)
        return FeatureViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        holder.imgBookCover.setImageResource(lstFeatureCard[position].imgBookCover)
        holder.tvAuthor.text = lstFeatureCard[position].textAuthor
        holder.tvBookName.text= lstFeatureCard[position].textBookName
    }

    override fun getItemCount() = lstFeatureCard.size
}