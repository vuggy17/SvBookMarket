package com.example.svbookmarket.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.CheckoutAdapter
import com.example.svbookmarket.activities.data.DataSource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set activity to display here
        setContentView(R.layout.activity_checkout   )

        //tam thoi de funtion nay o day
//        card.setOnClickListener{
//            card.setChecked(!card.isChecked)
//            true
//        }
        //code ben duoi dung cho card view
//        val myDataset = DataSource().loadCheckoutCard()
//        val recyclerView  = findViewById<RecyclerView>(R.id.rc_checkout)
//        recyclerView.adapter = CheckoutAdapter(this,myDataset)
//        recyclerView.setHasFixedSize(true)


    }
}
