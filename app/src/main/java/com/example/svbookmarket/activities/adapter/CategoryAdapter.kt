package com.example.svbookmarket.activities.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
        with(items[position]){
            holder.let {
                it.name.text = name

                // TODO: 10/06/2021 set image for category
                //it.background.setImageResource()

                //setup listener
                it.itemView.setOnClickListener { listener.recyclerViewListClicked(it, position) }
            }
        }
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.categoryName)
        val background: ImageView = view.findViewById(R.id.categoryImage)
    }

}