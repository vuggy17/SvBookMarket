package com.example.svbookmarket.activities.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.Feature_Item
import com.makeramen.roundedimageview.RoundedImageView


class FeatureAdapter(
    private val context: Context,
    private val lstFeatureCard: MutableList<Feature_Item>
) : RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_feature, parent, false)
        return FeatureViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        holder.imgBookCover.setImageResource(lstFeatureCard[position].imgBookCover)
        holder.tvAuthor.text = lstFeatureCard[position].textAuthor
        holder.tvBookName.text = lstFeatureCard[position].textBookName
    }

    override fun getItemCount() = lstFeatureCard.size

    class FeatureViewHolder(FeatureCard: View) : RecyclerView.ViewHolder(FeatureCard) {
        val imgBookCover: AppCompatImageView = FeatureCard.findViewById(R.id.imgFeatureCover)
        val tvAuthor: TextView = FeatureCard.findViewById(R.id.tvAuthor)
        val tvBookName: TextView = FeatureCard.findViewById(R.id.tvBookName)
    }
}