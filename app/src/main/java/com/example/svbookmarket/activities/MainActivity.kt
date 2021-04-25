package com.example.svbookmarket.activities

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.FeatureAdapter
import com.example.svbookmarket.activities.data.Feature_Item
import com.example.svbookmarket.activities.adapter.CheckoutAdapter
import com.example.svbookmarket.activities.data.DataSource


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set activity to display here
        setContentView(R.layout.activity_feature)

        //tam thoi de funtion nay o day
//        card.setOnClickListener{
//            card.setChecked(!card.isChecked)
//            true
//        }


     //   val myDataset =DataSource().loadCheckoutCard()
       // val recyclerView  = findViewById<RecyclerView>(R.id.rc_checkout)
       // recyclerView.adapter = CheckoutAdapter(this,myDataset)
       // recyclerView.setHasFixedSize(true)
    }
}
