package com.example.svbookmarket.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.databinding.ItemBillingBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CheckoutDialog:BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_checkout, container, false)


    return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buyButton = view.findViewById<Button>(R.id.mdco_checkout)
        buyButton.setOnClickListener{
            Toast.makeText(context, "sheet click", Toast.LENGTH_SHORT).show()
        }



        val billingItemList = view.findViewById<RecyclerView>(R.id.mdco_itemBill)
        billingItemList.layoutManager = LinearLayoutManager(context)
        billingItemList.adapter = ItemAdapter(3)

    }

    private inner class ViewHolder internal constructor(binding: ItemBillingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal val itemName = binding.itName
        val itemPrice = binding.itPrice
    }

    private inner class ItemAdapter internal constructor(private val mItemCount: Int) :
        RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            return ViewHolder(
                ItemBillingBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          holder.itemPrice.text = (position * 100).toString()
            holder.itemName.text = "Little prince"
            Log.d("modal","created")

        }

        override fun getItemCount(): Int {
            return mItemCount
        }
    }
}