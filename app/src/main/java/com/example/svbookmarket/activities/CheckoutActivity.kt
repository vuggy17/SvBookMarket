package com.example.svbookmarket.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.R.*
import com.example.svbookmarket.activities.adapter.CheckoutAdapter
import com.example.svbookmarket.activities.data.DataSource
import com.example.svbookmarket.databinding.ActivityCheckoutBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)

        // set activity to display here
        setContentView(binding.root)
        val dataset = DataSource().loadCheckoutCard()
        val recyclerView = findViewById<RecyclerView>(id.rc_checkout)
        recyclerView.adapter = CheckoutAdapter(this, dataset)
        recyclerView.setHasFixedSize(true)

        val buyReviewDialog = CheckoutDialog()
        binding.coCheckout.setOnClickListener {
            Toast.makeText(this, "cc", Toast.LENGTH_SHORT).show()
            buyReviewDialog.show(supportFragmentManager,"tag")

        }
    }


}
