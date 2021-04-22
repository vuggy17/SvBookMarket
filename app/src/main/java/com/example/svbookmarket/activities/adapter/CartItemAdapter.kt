package com.example.svbookmarket.activities.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.CartModel
import com.example.svbookmarket.databinding.CardCheckoutBinding
import com.google.android.material.card.MaterialCardView

class CartItemAdapter(val context: Context, val cartList:MutableList<CartModel>):RecyclerView.Adapter<CartItemAdapter.VH>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.card_checkout, parent, false)
        return VH(adapterLayout)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.name.setText(cartList[position].name)
        holder.author.setText(cartList[position].author)
        holder.coverimg.setImageResource(R.drawable.welcome)
        holder.price.setText(cartList[position].price)
        holder.number.setText(cartList[position].numbers)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }
    fun removeItem(position: Int) {
        cartList.removeAt(position)
        notifyItemRemoved(position)
    }
    fun addItem(position: Int, model: CartModel){
        cartList.add(position,model )
       notifyItemInserted(position)
    }

    class VH(view:View):RecyclerView.ViewHolder(view){
        private var binding = CardCheckoutBinding.inflate(LayoutInflater.from(view.context))

        init {
            view.setOnClickListener{
                (it as MaterialCardView).isChecked = !it.isChecked
            }
        }
        var number : TextView = view.findViewById(R.id.tv_numbers)
        var name : TextView = view.findViewById(R.id.tv_bookname)
        var author : TextView = view.findViewById(R.id.tv_author)
        var coverimg : ImageView = view.findViewById(R.id.img_cover)
        var price : TextView = view.findViewById(R.id.tv_price)

        fun toggleChecked(isChecked:Boolean){
            (this.itemView as MaterialCardView).isChecked = isChecked
        }
    }
}