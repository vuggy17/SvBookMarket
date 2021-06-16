package com.example.svbookmarket.activities.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.Cart
import com.google.android.material.card.MaterialCardView

class CartItemAdapter(val context: Context, private val cartList:MutableList<Cart>):RecyclerView.Adapter<CartItemAdapter.VH>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.card_checkout, parent, false)
        return VH(adapterLayout)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.name.text = cartList[position].name
        holder.author.text = cartList[position].author
        holder.coverimg.setImageResource(R.drawable.welcome)
        holder.price.text = cartList[position].price.toString()
//        holder.number.text = cartList[position].quantity.toString()

        // increase and decrease button listenerc
        holder.increaseButton.setOnClickListener {
            Toast.makeText(
                context,
                "+ pressed",
                Toast.LENGTH_SHORT
            ).show()
        }
        holder.decreaseButton.setOnClickListener {
            Toast.makeText(
                context,
                "- pressed",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return cartList.size
    }
    fun removeItem(position: Int) {
        cartList.removeAt(position)
        notifyItemRemoved(position)
    }
    fun addItem(position: Int, model: Cart){
        cartList.add(position,model )
       notifyItemInserted(position)
    }

    class VH(view:View):RecyclerView.ViewHolder(view){
        init {
            // on selection -> highlight
            view.setOnClickListener{
                (it as MaterialCardView).isChecked = !it.isChecked
            }
        }
        val increaseButton:AppCompatImageButton = view.findViewById(R.id.increaseNumber)
        val decreaseButton:AppCompatImageButton = view.findViewById(R.id.decreaseNumber)
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