package com.example.svbookmarket.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.CheckoutAdapter
import com.example.svbookmarket.activities.data.DataSource
import com.example.svbookmarket.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set activity to display here
        setContentView(binding.root)

        val dataset = DataSource().loadCheckoutCard()
        val recyclerView  = findViewById<RecyclerView>(R.id.rc_checkout)
        recyclerView.adapter = CheckoutAdapter(this,dataset)
        recyclerView.setHasFixedSize(true)

    }


}
