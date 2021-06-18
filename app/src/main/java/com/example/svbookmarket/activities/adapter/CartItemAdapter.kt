package com.example.svbookmarket.activities.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.Cart
import com.google.android.material.card.MaterialCardView

class CartItemAdapter(val listener: OnButtonClickListener, private var cartList:MutableList<Cart>):RecyclerView.Adapter<CartItemAdapter.VH>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.card_checkout, parent, false)
        return VH(adapterLayout)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.name.text = cartList[position].name
        holder.author.text = cartList[position].author

        Glide
            .with(holder.itemView)
            .load(cartList[position].imgUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.coverimg);

        holder.price.text = cartList[position].price.toString()
        holder.number.text = cartList[position].numbers.toString()

        // increase and decrease button listenerc
        holder.increaseButton.setOnClickListener {
            holder.number.text = (holder.number.text.toString().toInt() + 1).toString()
            // TODO: may need reset cardList
            listener.onButtonClick(cartList[position].id, true)
        }
        holder.decreaseButton.setOnClickListener {
            if (cartList[position].numbers > 0) {
                holder.number.text = (holder.number.text.toString().toInt() - 1).toString()
                listener.onButtonClick(cartList[position].id, false)
            }
        }
        holder.itemView.setOnClickListener {
            (it as MaterialCardView).isChecked = !it.isChecked
            listener.onItemClick(cartList[position].id, it.isChecked)
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
    fun onChange(newList: MutableList<Cart>)
    {
        cartList = newList
        notifyDataSetChanged()
    }

    class VH(view:View):RecyclerView.ViewHolder(view){
        init {
            // on selection -> highlight
//            view.setOnClickListener{
//                (it as MaterialCardView).isChecked = !it.isChecked
//            }
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

    interface OnButtonClickListener
    {
        fun onButtonClick(id: String, plusOrMinus: Boolean)
        fun onItemClick(id: String, isChose: Boolean)
    }

}