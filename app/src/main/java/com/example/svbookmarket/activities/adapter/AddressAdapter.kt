package com.example.svbookmarket.activities.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.AddressModel
import com.google.android.material.card.MaterialCardView

class AddressAdapter(var context: Context, private var dataset:MutableList<AddressModel>):RecyclerView.Adapter<AddressAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
       val adapterLayout  = LayoutInflater.from(parent.context).inflate(R.layout.card_address,parent,false)
        return VH(adapterLayout)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.name.text = dataset[position].name
        holder.phoneNumber.text = dataset[position].phoneNumber
        holder.firstAddress.text = dataset[position].firstAdress
        holder.secondAddress.text = dataset[position].secondAddress
        holder.thirdAddress.text = dataset[position].thirdAddress
        holder.fourthAddress.text = dataset[position].fourthAddress

        // highlight current item
        if(holder.adapterPosition == 1){
            (holder.itemView as MaterialCardView).isChecked = true
        }
    }

    override fun getItemCount(): Int {
       return dataset.size
    }
    class VH(view: View):RecyclerView.ViewHolder(view){

        var name:TextView = view.findViewById(R.id.tvName)
        var phoneNumber:TextView = view.findViewById(R.id.tvPhoneNumber)
        var firstAddress:TextView = view.findViewById(R.id.tvAddress)
        var secondAddress:TextView = view.findViewById(R.id.tvSecondAddress)
        var thirdAddress:TextView = view.findViewById(R.id.tvThirdAddress)
        var fourthAddress:TextView = view.findViewById(R.id.tvFourthAddress)
    }
}