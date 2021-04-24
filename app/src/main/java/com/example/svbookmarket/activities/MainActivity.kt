package com.example.svbookmarket.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.FeatureAdapter
import com.example.svbookmarket.activities.data.Feature_Item
import kotlinx.android.synthetic.main.card_checkout.*
import kotlinx.android.synthetic.main.card_checkout.view.*

class MainActivity : AppCompatActivity() {
    lateinit var feature_recycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set activity to display here
        setContentView(R.layout.activity_feature   )

        //tam thoi de funtion nay o day
//        card.setOnClickListener{
//            card.setChecked(!card.isChecked)
//            true
//        }
        feature_recycler = findViewById(R.id.feature_recycler)
        val featureItem: Feature_Item = Feature_Item("@color/danger".toInt(), "Khang", "Khang")
        val featureItem1: Feature_Item = Feature_Item("@color/danger".toInt(), "Khang", "Khang")
        val featureItem2: Feature_Item = Feature_Item("@color/danger".toInt(), "Khang", "Khang")
        var feature_List = listOf<Feature_Item>(featureItem, featureItem1, featureItem2)
        feature_recycler.adapter = FeatureAdapter(feature_List)
        feature_recycler.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

     //   val myDataset =DataSource().loadCheckoutCard()
       // val recyclerView  = findViewById<RecyclerView>(R.id.rc_checkout)
       // recyclerView.adapter = CheckoutAdapter(this,myDataset)
       // recyclerView.setHasFixedSize(true)
    }
}
