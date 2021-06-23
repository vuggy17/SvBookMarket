package com.example.svbookmarket.activities.adapter

import CurrentUserInfo
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.UserDeliverAddress
import com.google.android.material.card.MaterialCardView
import kotlin.properties.Delegates


public class AddressAdapter(
    private var items: MutableList<UserDeliverAddress>,
    listener: NotifyAddressSelectionChanged
) :
    RecyclerView.Adapter<AddressAdapter.VH>() {
    private var selectedPos = -1

    private var selectedPosition by Delegates.observable(selectedPos) { _, oldPos, newPos ->
        if (newPos in items.indices && newPos != oldPos && oldPos!= -1) {
            //update list
            items[oldPos].chose = false
            items[newPos].chose = true

            listener.onAddressChange(items[oldPos], items[newPos])

            //update adapter
            notifyItemChanged(oldPos)
            notifyItemChanged(newPos)


            Log.i("customtag", "old: $oldPos new: $newPos")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.card_address, parent, false)
        return VH(adapterLayout)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.name.text = items[position].fullName
        holder.phoneNumber.text = items[position].phoneNumber
        holder.firstAddress.text = items[position].addressLane
        holder.secondAddress.text = items[position].district
        holder.thirdAddress.text = items[position].city

        (holder.itemView as MaterialCardView).isChecked = items[position].chose

        if (items[position].chose)
            selectedPosition = position

        Log.i("customtag", "onbind: ${selectedPos}")
        //toggle selected
        holder.itemView.setOnClickListener {
            selectedPosition = position
        }

    }

    fun addAddress(address: List<UserDeliverAddress>) {
        if (this.items.isNotEmpty()) {
            this.items.clear()
        }
        this.items.addAll(address)
        notifyDataSetChanged()
    }


    fun onChange() {
        items = CurrentUserInfo.getInstance().lstUserDeliverAddress
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {

        var name: TextView = view.findViewById(R.id.tvName)
        var phoneNumber: TextView = view.findViewById(R.id.tvPhoneNumber)
        var firstAddress: TextView = view.findViewById(R.id.tvAddress)
        var secondAddress: TextView = view.findViewById(R.id.tvSecondAddress)
        var thirdAddress: TextView = view.findViewById(R.id.tvThirdAddress)
        var fourthAddress: TextView = view.findViewById(R.id.tvFourthAddress)
        var vieww: View = view
    }

    interface NotifyAddressSelectionChanged {
        fun onAddressChange(old: UserDeliverAddress, new: UserDeliverAddress)
    }
}
