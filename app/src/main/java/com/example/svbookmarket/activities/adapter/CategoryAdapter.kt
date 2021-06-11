package com.example.svbookmarket.activities.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.common.RecyclerViewClickListener
import com.example.svbookmarket.activities.model.Category

class CategoryAdapter(private val items: ArrayList<Category>, private val listener: RecyclerViewClickListener) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val startTime = System.currentTimeMillis()
        with(items[position]){
            holder.let {
                it.name.text = name
                Glide
                    .with(holder.itemView)
                    .load(backgroundResId)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(it.background);

                //setup listener
                it.itemView.setOnClickListener { listener.recyclerViewListClicked(it, position) }
            }
        }
        Log.i("TAG", "bindView time: " + (System.currentTimeMillis() - startTime));
    }

    override fun getItemCount(): Int = items.size



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.categoryName)
        val background: ImageView = view.findViewById(R.id.categoryImage)
    }

}