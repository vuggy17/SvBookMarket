package com.example.svbookmarket.activities

import android.R.attr.numColumns
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.FeatureAdapter
import com.example.svbookmarket.activities.common.RecyclerViewItemMargin
import com.example.svbookmarket.activities.data.DataSource


class FeatureActivity : AppCompatActivity() {
    lateinit var featureRecycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature)

        featureRecycler = findViewById(R.id.feature_recycler)

        val dataset = DataSource().loadFeatureCard()
            featureRecycler.adapter = FeatureAdapter(this, dataset)
            featureRecycler.layoutManager = LinearLayoutManager(
                this,
                RecyclerView.HORIZONTAL,
                false
            )
        val decoration = RecyclerViewItemMargin(64, dataset.size)
        featureRecycler.addItemDecoration(decoration)
            featureRecycler.setHasFixedSize(true)
    }
}