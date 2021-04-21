package com.example.svbookmarket.activities.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.CartModel
import com.google.android.material.card.MaterialCardView
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder


class CartItemBinder : ItemBinder<CartModel, CartItemBinder.VH>() {
    override fun createViewHolder(parent: ViewGroup): VH {
        return VH(inflate(parent, R.layout.card_checkout))
    }
    override fun bindViewHolder(holder: VH, item: CartModel) {
        holder.name.setText(R.string.app_name)
        holder.author.setText(R.string.app_name)
        holder.coverimg.setImageResource(R.drawable.welcome)
        holder.price.setText(R.string.app_name)
        holder.number.setText(R.string.app_name)

    }
    override fun canBindData(item: Any?): Boolean {
        return item is CartModel
    }

     class VH(itemView: View) : ItemViewHolder<CartModel>(itemView) {
        var number :TextView = itemView.findViewById(R.id.tv_numbers)
        var name :TextView = itemView.findViewById(R.id.tv_bookname)
        var author :TextView = itemView.findViewById(R.id.tv_author)
        var coverimg :ImageView = itemView.findViewById(R.id.img_cover)
        var price :TextView = itemView.findViewById(R.id.tv_price)

        init {
            itemView.setOnClickListener { toggleItemSelection() }
            itemView.setOnLongClickListener() {
                (it as MaterialCardView).isChecked = !it.isChecked
                true
            }
        }

         override fun getSwipeDirections(): Int {
             return ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
         }
    }

}
