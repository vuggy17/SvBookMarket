package com.example.svbookmarket.activities.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.CheckoutCard
import com.google.android.material.card.MaterialCardView

class CheckoutAdapter(private val context: Context, private val items: MutableList<CheckoutCard>) :
    RecyclerView.Adapter<CheckoutAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.card_checkout, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.author.text = context.resources.getString(item.author)
//        holder.cover.text = context.resources.getString(item.cover)
        holder.bookname.text = context.resources.getString(item.name)
        holder.price.text = context.resources.getString(item.price)
        holder.cartNumber.text = context.resources.getString(item.number)

        //check option
        holder.itemView.setOnLongClickListener() {
                (it as MaterialCardView).setChecked(!it.isChecked)
            true
            }
        }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val author: TextView = view.findViewById(R.id.tv_author)
        val price: TextView = view.findViewById(R.id.tv_price)

        //    val cover:TextView = view.findViewById(R.id.img_cover)
        val bookname: TextView = view.findViewById(R.id.tv_bookname)
        val cartNumber: TextView = view.findViewById(R.id.tv_cartnumber)


    }

}