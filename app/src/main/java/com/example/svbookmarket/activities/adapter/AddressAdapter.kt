package com.example.svbookmarket.activities.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.AddressActivity
import com.example.svbookmarket.activities.model.UserDeliverAddress
import com.google.android.material.card.MaterialCardView
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.properties.Delegates


public class AddressAdapter(var context: Context, private var dataset: MutableList<UserDeliverAddress>) :
    RecyclerView.Adapter<AddressAdapter.VH>() {

    val defaultSelection = 0

    // default position = 0
    var selectedPosition by Delegates.observable(defaultSelection) { property, oldPos, newPos ->
        if (newPos in dataset.indices) {
            notifyItemChanged(oldPos)
            notifyItemChanged(newPos)

            if(context is AddressActivity){
                // TODO: 14/06/2021 update selected address trong viewmodeld de goi ham update trong viewmodel  
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.card_address, parent, false)
        return VH(adapterLayout)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.name.text = dataset[position].fullName
        holder.phoneNumber.text = dataset[position].phoneNumber
        holder.firstAddress.text = dataset[position].addressLane
        holder.secondAddress.text = dataset[position].district
        holder.thirdAddress.text = dataset[position].city

        //default position

        (holder.itemView as MaterialCardView).setOnClickListener {
            (it as MaterialCardView).isChecked = !it.isChecked

        }

        if (position in dataset.indices) {
            holder.bind()
            holder.itemView.setOnClickListener { selectedPosition = position
                CurrentUserInfo.getInstance().lstUserDeliverAddress[position].chose = true
                val ref = FirebaseFirestore.getInstance().collection("accounts").document(CurrentUserInfo.getInstance().currentProfile.email).collection("userDeliverAddresses")
                for (i in 0 until CurrentUserInfo.getInstance().lstUserDeliverAddress.size)
                {
                    if ( CurrentUserInfo.getInstance().lstUserDeliverAddress[i].chose)
                    {
                        CurrentUserInfo.getInstance().lstUserDeliverAddress[i].chose = false
                        ref.document(CurrentUserInfo.getInstance().lstUserDeliverAddress[i].id).update("isChose", false).addOnCompleteListener {
                            Log.d("000000000000000", "dcm1")
                        }
                    }
                }
                ref.document(CurrentUserInfo.getInstance().lstUserDeliverAddress[position].id).update("isChose", true).addOnCompleteListener{
                    Log.d("000000000000000", "dcm2")
                }
            }

        }
    }

    fun onChange()
    {
        dataset = CurrentUserInfo.getInstance().lstUserDeliverAddress
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
