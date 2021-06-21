package com.example.svbookmarket.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.R.*
import com.example.svbookmarket.activities.adapter.CartItemAdapter
import com.example.svbookmarket.activities.adapter.CheckoutAdapter
import com.example.svbookmarket.activities.data.DataSource
import com.example.svbookmarket.activities.model.Cart
import com.example.svbookmarket.activities.model.UserDeliverAddress
import com.example.svbookmarket.activities.viewmodel.CheckoutViewModel
import com.example.svbookmarket.databinding.ActivityCartBinding
import com.example.svbookmarket.databinding.ActivityCheckoutBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutActivity : AppCompatActivity() {

   private val viewModel: CheckoutViewModel by viewModels()
    private lateinit var binding: ActivityCheckoutBinding
    lateinit var dataset : MutableList<Cart>
    var checkoutAdapter: CheckoutAdapter = CheckoutAdapter(this, mutableListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvAddress.text = viewModel.deliverAddress.value?.fullName + ", " + viewModel.deliverAddress.value?.phoneNumber +  ", " + viewModel.deliverAddress.value?.addressLane + ", " + viewModel.deliverAddress.value?.district + ", " + viewModel.deliverAddress.value?.city
        binding.rcCheckout.apply {
            layoutManager =
                LinearLayoutManager(this@CheckoutActivity)
            setHasFixedSize(true)
            adapter = checkoutAdapter
        }
        val buyReviewDialog = CheckoutDialog()
        binding.coCheckout.setOnClickListener {
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
            buyReviewDialog.show(supportFragmentManager,"tag")
        }

        binding.coBackButton.setOnClickListener{onBackPressed()}

        viewModel.checkoutItem.observe(this, changeObserver)
        viewModel.deliverAddress.observe(this, changeAddress)
    }
    private val changeObserver = Observer<MutableList<Cart>> { value ->
        value?.let {
            dataset = value
             checkoutAdapter.onChange(value)
            checkoutAdapter.notifyDataSetChanged()
            Log.d("0000000000000", value.toString())
        }
    }

    private val changeAddress = Observer<UserDeliverAddress> { value ->
        value?.let {
            binding.tvAddress.text = value.fullName + ", " + value.phoneNumber + "/n" + value.addressLane + ", " + value.district + ", " + value.city
        }
    }
}
