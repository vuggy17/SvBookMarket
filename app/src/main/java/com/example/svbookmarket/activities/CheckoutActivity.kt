package com.example.svbookmarket.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.svbookmarket.R
import com.example.svbookmarket.R.*
import com.example.svbookmarket.activities.adapter.CheckoutAdapter
import com.example.svbookmarket.activities.model.Cart
import com.example.svbookmarket.activities.model.UserDeliverAddress
import com.example.svbookmarket.activities.viewmodel.CheckoutViewModel
import com.example.svbookmarket.databinding.ActivityCheckoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutActivity : AppCompatActivity() {

    private val viewModel: CheckoutViewModel by viewModels()
    private lateinit var binding: ActivityCheckoutBinding
    lateinit var dataset: MutableList<Cart>
    var checkoutAdapter: CheckoutAdapter = CheckoutAdapter(this, mutableListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayAddress()
        setCheckoutAdapter()
        setCheckoutDialog()
        setupNavigation()
        watchChanges()
    }

    private fun watchChanges() {
        viewModel.checkoutItem.observe(this, changeObserver)
        viewModel.deliverAddress.observe(this, changeAddress)
    }

    private fun setupNavigation() {
        binding.coBackButton.setOnClickListener { onBackPressed() }
        binding.ctChangeLocation.setOnClickListener { navigateToAddress() }
    }

    private fun setCheckoutDialog() {
        val buyReviewDialog = CheckoutDialog()
        binding.coCheckout.setOnClickListener {
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
            buyReviewDialog.show(supportFragmentManager, "tag")
        }
    }

    private fun setCheckoutAdapter() {
        binding.rcCheckout.apply {
            layoutManager =
                LinearLayoutManager(this@CheckoutActivity)
            setHasFixedSize(true)
            adapter = checkoutAdapter
        }
    }

    private fun displayAddress() {
        val s = StringBuilder("")
        viewModel.deliverAddress.value?.let {
            s.append(it.fullName)
                .append(it.phoneNumber)
                .append(it.addressLane)
                .append(it.district)
                .append(it.city)
        }
        binding.tvAddress.text = s
    }

    private fun navigateToAddress() {
        val i = Intent(this, AddressActivity::class.java).putExtra(
            AddressActivity.FROM_CART, true
        )
        startActivity(i)
    }

    private val changeObserver = Observer<MutableList<Cart>> { value ->
        value?.let {
            dataset = value
            checkoutAdapter.onChange(value)
            checkoutAdapter.notifyDataSetChanged()
            Log.d("00000000000001", "$value")
        }
    }

    private val changeAddress = Observer<UserDeliverAddress> { value ->
        value?.let {
            binding.tvAddress.text =
                value.fullName + ", " + value.phoneNumber + "/n" + value.addressLane + ", " + value.district + ", " + value.city
        }
    }
}
