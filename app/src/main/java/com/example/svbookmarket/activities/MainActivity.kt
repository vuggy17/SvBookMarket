package com.example.svbookmarket.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.CheckoutAdapter
import com.example.svbookmarket.activities.data.DataSource


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set activity to display here
        setContentView(R.layout.activity_checkout)

        //tam thoi de funtion nay o day
//        card.setOnClickListener{
//            card.setChecked(!card.isChecked)
//            true
//        }
//

<<<<<<< HEAD
        val myDataset = DataSource().loadCheckoutCard()
        val recyclerView  = findViewById<RecyclerView>(R.id.rc_checkout)
        recyclerView.adapter = CheckoutAdapter(this,myDataset)
        recyclerView.setHasFixedSize(true)
=======
//        val myDataset =DataSource().loadCheckoutCard()
//        val recyclerView  = findViewById<RecyclerView>(R.id.rc_checkout)
//        recyclerView.adapter = CheckoutAdapter(this,myDataset)
//        recyclerView.setHasFixedSize(true)
>>>>>>> 51c1a6097fb2510828dab55f1ea5b36439885093
    }
}
