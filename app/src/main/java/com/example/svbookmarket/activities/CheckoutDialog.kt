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
import com.example.svbookmarket.activities.data.FullBookList
import com.example.svbookmarket.activities.model.AppAccount
import com.example.svbookmarket.activities.model.Cart
import com.example.svbookmarket.activities.model.UserDeliverAddress
import com.example.svbookmarket.activities.viewmodel.CheckoutDialogViewModel
import com.example.svbookmarket.databinding.ItemBillingBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class CheckoutDialog : BottomSheetDialogFragment(){
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
    lateinit var btn_buy: MaterialButton
    private var lstOverMount: MutableList<String> = mutableListOf()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btn_buy = view.findViewById(R.id.mdco_checkout)
        tvSum = view.findViewById(R.id.tv_sum)
        tvSum.text = sum.toString() + " đ"
        billingItemList = view.findViewById(R.id.mdco_itemBill)
        billingItemList.layoutManager = LinearLayoutManager(context)
        billingItemList.adapter = itemAdapter

        btn_buy.setOnClickListener{
            if(checkIfComeFirst()) {
                moveToOrder(
                    CurrentUserInfo.getInstance().currentProfile,
                    CurrentUserInfo.getInstance().lstUserCart,
                    CurrentUserInfo.getInstance().lstUserDeliverAddress
                )
            }
            else
            {
                Toast.makeText(context, "0000000", Toast.LENGTH_SHORT).show()
            }
        }

        loadData()

    }

    fun moveToOrder(user: AppAccount, lstCheckout: MutableList<Cart>, lstAddress: MutableList<UserDeliverAddress>) {
        for (i in 0 until lstAddress.size) {
            if (lstAddress[i].isChose) {
                GlobalScope.launch {
                    withContext(Dispatchers.IO) {
                        moveToUserOrDer(user, lstCheckout, lstAddress[i])
                    }
                }
                break
            }
        }
    }
    suspend fun moveToUserOrDer(user: AppAccount, listNeedToMove: MutableList<Cart>, deliverAddress: UserDeliverAddress)
    {
        val mapOfAddress = hashMapOf<String, Any>("addressId" to deliverAddress.id, "addressLane" to deliverAddress.addressLane, "city" to deliverAddress.city,
            "district" to deliverAddress.district,"fullName" to deliverAddress.fullName,"phoneNumber" to deliverAddress.phoneNumber, "userId" to user.email)

        val newOrderId : String = FirebaseFirestore.getInstance().collection("accounts").document(user.email)
            .collection("userOrder").add(mapOfAddress).await().get().await().id

        var lstIdOnMove: MutableList<String> = mutableListOf()
        var lstNumOnMove: MutableList<Int> = mutableListOf()
        for (i in 0 until listNeedToMove.size) {
            if (listNeedToMove[i].isChose == true) {
                lstIdOnMove.add(listNeedToMove[i].id)
                lstNumOnMove.add(listNeedToMove[i].numbers)
                val mapOfOrder = hashMapOf<String, Any>(
                    "Quantity" to listNeedToMove[i].numbers,
                    "author" to listNeedToMove[i].author,
                    "image" to listNeedToMove[i].imgUrl,
                    "price" to listNeedToMove[i].price,
                    "title" to listNeedToMove[i].name
                )
                FirebaseFirestore.getInstance().collection("accounts").document(user.email)
                    .collection("userOrder").document(newOrderId).collection("books")
                    .document(listNeedToMove[i].id).set(mapOfOrder)
            }
        }

        for (i in 0 until lstIdOnMove.size)
        {
            FirebaseFirestore.getInstance().collection("accounts").document(user.email)
                .collection("userCart").document(lstIdOnMove[i]).delete()
            FirebaseFirestore.getInstance().collection("books").document(lstIdOnMove[i]).update("rate", FieldValue.increment(lstNumOnMove[i].toDouble()))
            FirebaseFirestore.getInstance().collection("books").document(lstIdOnMove[i]).update("Counts", FieldValue.increment(-lstNumOnMove[i].toDouble()))
        }
    }

    fun checkIfComeFirst(): Boolean
    {
        for (i in 0 until CurrentUserInfo.getInstance().lstUserCart.size)
        {
            for (j in 0 until FullBookList.getInstance().lstFullBook.size)
            {
                if (CurrentUserInfo.getInstance().lstUserCart[i].id == FullBookList.getInstance().lstFullBook[j].id &&
                    FullBookList.getInstance().lstFullBook[j].Counts < CurrentUserInfo.getInstance().lstUserCart[i].numbers)
                {
                    return false
                }
            }
        }
        return true
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