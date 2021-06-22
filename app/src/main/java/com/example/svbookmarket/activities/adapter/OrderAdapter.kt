package com.example.svbookmarket.activities.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.CheckoutDialog
import com.example.svbookmarket.activities.model.Cart
import com.example.svbookmarket.activities.model.Order
import com.example.svbookmarket.databinding.ItemBillingBinding

class OrderAdapter(
    var listOder: MutableList<Order>,
    var context: Context
): RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    class ViewHolder (view: View): RecyclerView.ViewHolder(view){
        val status: TextView = view.findViewById(R.id.status)
        val dateTime: TextView = view.findViewById(R.id.dateTime)
        val name: TextView = view.findViewById(R.id.orderName)
        val address: TextView = view.findViewById(R.id.orderAddress)
        val phone: TextView = view.findViewById(R.id.orderPhoneNumber)
        val listItemOrder: RecyclerView = view.findViewById(R.id.orderItemBill)
        val totalPrice: TextView = view.findViewById(R.id.orderSum)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_order, parent, false)
        return ViewHolder(view)
    }
    fun addOrder(change: MutableList<Order>){
        if (this.listOder.isNotEmpty()) {
            this.listOder.clear()
        }
        this.listOder.addAll(change)
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentOrder: Order = listOder[position]
        holder.apply {
            status.text = currentOrder.status
            name.text = currentOrder.userDeliverAddress.fullName
            phone.text = currentOrder.userDeliverAddress.phoneNumber
            address.text = currentOrder.userDeliverAddress.addressLane +", "+ currentOrder.userDeliverAddress.district +", "+ currentOrder.userDeliverAddress.city+"."
            dateTime.text = currentOrder.dateTime
            totalPrice.text = currentOrder.totalPrince
            val billingItemAdapter = BillingItemAdapter(currentOrder.listbooks)
            listItemOrder.adapter = billingItemAdapter
            listItemOrder.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun getItemCount(): Int {
        return listOder.size
    }


}