package com.example.svbookmarket.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.CheckoutAdapter
import com.example.svbookmarket.activities.data.DataSource
import com.example.svbookmarket.databinding.ActivityCheckoutBinding

//import kotlinx.android.synthetic.main.card_checkout.*

class CheckoutActivity : AppCompatActivity() {
    lateinit var binding: ActivityCheckoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set activity to display here
        setContentView(R.layout.activity_checkout)


        val myDataset =DataSource().loadCheckoutCard()
        val recyclerView  = findViewById<RecyclerView>(R.id.rc_checkout)
        recyclerView.adapter = CheckoutAdapter(this,myDataset)
        recyclerView.setHasFixedSize(true)

    }


}
// TODO: 15/04/2021 https://github.com/DevAhamed/MultiViewAdapter#basic-usage