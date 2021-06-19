package com.example.svbookmarket.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.common.Constants
import com.example.svbookmarket.activities.data.CartRepository
import com.example.svbookmarket.activities.model.Cart
import com.example.svbookmarket.activities.viewmodel.CheckoutDialogViewModel
import com.example.svbookmarket.databinding.ItemBillingBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutDialog: BottomSheetDialogFragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_checkout, container, false)
    return view
    }

    private var items: MutableList<Cart> = mutableListOf()
    private var sum: Long = 0
    private var itemAdapter: ItemAdapter = ItemAdapter(items)
    lateinit var tvSum:TextView
    lateinit var billingItemList: RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buyButton = view.findViewById<Button>(R.id.mdco_checkout)
        buyButton.setOnClickListener{
            Toast.makeText(context, "sheet click", Toast.LENGTH_SHORT).show()
        }

        tvSum = view.findViewById(R.id.tv_sum)
        tvSum.text = sum.toString() + " đ"
        billingItemList = view.findViewById(R.id.mdco_itemBill)
        billingItemList.layoutManager = LinearLayoutManager(context)
        billingItemList.adapter = itemAdapter

        loadData()

    }

    private fun loadData()
    {
        FirebaseFirestore.getInstance().collection("accounts").document(CurrentUserInfo.getInstance().currentProfile.email).collection("userCart")
            .addSnapshotListener(object :
            EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    Log.w(Constants.VMTAG, "Listen failed.", error)
                    return
                }

                val checkoutList: MutableList<Cart> = ArrayList()
                for (doc in value!!) {
                    if (doc.data["isChose"].toString() == "true") {
                        var item = Cart(
                            "",
                            doc.data["image"].toString(),
                            doc.data["title"].toString(),
                            doc.data["author"].toString(),
                            doc.data["Quantity"].toString().toInt(),
                            doc.data["price"].toString().toLong(),
                            true
                        )
                        item.id = doc.id
                        checkoutList.add(item)
                    }
                }
                items= checkoutList
                sum = 0
                for (item in items)
                {
                    sum += item.price * item.numbers
                }
                tvSum.text = sum.toString() + " đ"
                itemAdapter.onChange(items)
                itemAdapter.notifyDataSetChanged()
            }

        })
    }

    private inner class ViewHolder internal constructor(binding: ItemBillingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal val itemName = binding.itName
        val itemPrice = binding.itPrice
    }

    private inner class ItemAdapter internal constructor(private var billingItem: MutableList<Cart>) :
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

        fun onChange(newItems: MutableList<Cart>)
        {
            billingItem = newItems
        }
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          holder.itemPrice.text = (billingItem[position].price * billingItem[position].numbers).toString() + " đ"
            holder.itemName.text = billingItem[position].name
            Log.d("modal","created")

        }

        override fun getItemCount(): Int {
            return billingItem.size
        }
    }
}