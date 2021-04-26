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
import kotlin.properties.Delegates


public class AddressAdapter(var context: Context, private var dataset: MutableList<AddressModel>) :
    RecyclerView.Adapter<AddressAdapter.VH>() {

    val defaultSelection = 0

    // default position = 0
    var selectedPosition by Delegates.observable(defaultSelection) { property, oldPos, newPos ->
        if (newPos in dataset.indices) {
            notifyItemChanged(oldPos)
            notifyItemChanged(newPos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.card_address, parent, false)
        return VH(adapterLayout)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.name.text = dataset[position].name
        holder.phoneNumber.text = dataset[position].phoneNumber
        holder.firstAddress.text = dataset[position].firstAdress
        holder.secondAddress.text = dataset[position].secondAddress
        holder.thirdAddress.text = dataset[position].thirdAddress
        holder.fourthAddress.text = dataset[position].fourthAddress

        //default position

        (holder.itemView as MaterialCardView).setOnClickListener {
            (it as MaterialCardView).isChecked = !it.isChecked
        }

        if (position in dataset.indices) {
            holder.bind()
            holder.itemView.setOnClickListener { selectedPosition = position }
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {

        var name: TextView = view.findViewById(R.id.tvName)
        var phoneNumber: TextView = view.findViewById(R.id.tvPhoneNumber)
        var firstAddress: TextView = view.findViewById(R.id.tvAddress)
        var secondAddress: TextView = view.findViewById(R.id.tvSecondAddress)
        var thirdAddress: TextView = view.findViewById(R.id.tvThirdAddress)
        var fourthAddress: TextView = view.findViewById(R.id.tvFourthAddress)
        var vieww: View = view


        fun bind() {
            (vieww as MaterialCardView).isChecked = selectedPosition == adapterPosition
        }

    }
}
