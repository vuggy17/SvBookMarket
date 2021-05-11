package com.example.svbookmarket.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.BestsellingAdapter
import com.example.svbookmarket.activities.adapter.FeatureAdapter
import com.example.svbookmarket.activities.adapter.SuggestAdapter
import com.example.svbookmarket.activities.common.RecyclerViewItemMargin
import com.example.svbookmarket.activities.data.DataSource


class FeatureActivity : AppCompatActivity() {
    lateinit var featureRecycler: RecyclerView
    lateinit var bestsellingRecycler: RecyclerView
    lateinit var suggestRecycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature)

        featureRecycler = findViewById(R.id.feature_recycler)
        bestsellingRecycler = findViewById(R.id.rc_Bestselling)
        suggestRecycler = findViewById(R.id.rc_Suggest)
        fillInFetureRecycle()
        fillInBestRecycle()
        fillInSuggestRecycle()

        val snaphelperBest: LinearSnapHelper = LinearSnapHelper()
        val snaphelperSuggest: LinearSnapHelper = LinearSnapHelper()

        snaphelperSuggest.attachToRecyclerView(suggestRecycler)
        snaphelperBest.attachToRecyclerView(bestsellingRecycler)
    }
    fun fillInFetureRecycle()
    {
        val dataset = DataSource().loadFeatureCard()
        val snaphelperFeature: LinearSnapHelper = LinearSnapHelper()
        featureRecycler.adapter = FeatureAdapter(this, dataset)
        snaphelperFeature.attachToRecyclerView(featureRecycler)
        //featureRecycler.layoutManager = LinearLayoutManager(
        //    this,
        //    RecyclerView.HORIZONTAL,
         //   false
        //)
        val linearlayout: LinearLayoutManager = LinearLayoutManager(this)
        linearlayout.orientation = RecyclerView.HORIZONTAL
        linearlayout.scrollToPosition(1)
        featureRecycler.setLayoutManager(linearlayout)
        val decoration = RecyclerViewItemMargin(64, dataset.size)
        featureRecycler.addItemDecoration(decoration)
        featureRecycler.setHasFixedSize(true)
    }
    fun fillInBestRecycle()
    {
        val dataset = DataSource().loadBestsellingCard()
        bestsellingRecycler.adapter = BestsellingAdapter(this, dataset)
        bestsellingRecycler.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.HORIZONTAL,
            false
        )
        val decoration = RecyclerViewItemMargin(22, dataset.size)
        bestsellingRecycler.addItemDecoration(decoration)
        bestsellingRecycler.setHasFixedSize(true)
    }
    fun fillInSuggestRecycle()
    {
        val dataset = DataSource().loadSuggestCard()
        suggestRecycler.adapter = SuggestAdapter(this, dataset)
        suggestRecycler.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.HORIZONTAL,
            false
        )
        val decoration = RecyclerViewItemMargin(8, dataset.size)
        suggestRecycler.addItemDecoration(decoration)
        suggestRecycler.setHasFixedSize(true)
    }
}