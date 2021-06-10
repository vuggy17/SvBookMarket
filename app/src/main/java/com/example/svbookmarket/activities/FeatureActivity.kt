package com.example.svbookmarket.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.BestsellingAdapter
import com.example.svbookmarket.activities.adapter.FeatureAdapter
import com.example.svbookmarket.activities.adapter.FeaturedAdapter
import com.example.svbookmarket.activities.adapter.SuggestAdapter
import com.example.svbookmarket.activities.common.MarginItemDecoration
import com.example.svbookmarket.activities.common.RecyclerViewItemMargin
import com.example.svbookmarket.activities.data.DataSource


class FeatureActivity : AppCompatActivity() {
    lateinit var featureRecycler: RecyclerView
    lateinit var bestsellingRecycler: RecyclerView
    lateinit var suggestRecycler: RecyclerView
    lateinit var moreRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature)

        featureRecycler = findViewById(R.id.feature_recycler)
        bestsellingRecycler = findViewById(R.id.rc_Bestselling)
        suggestRecycler = findViewById(R.id.rc_Suggest)
        moreRecyclerView= findViewById(R.id.f_rcMore)

        fillInFetureRecycle()
        fillInBestRecycle()
        fillInSuggestRecycle()
        fillInMoreRecylerView()


        LinearSnapHelper().let {
            it.attachToRecyclerView(suggestRecycler)
            it.attachToRecyclerView(bestsellingRecycler)
        }

    }

    private fun fillInMoreRecylerView() {
        val dataBookSet = DataSource().loadBookFeature()

        moreRecyclerView.apply {
            adapter = FeaturedAdapter(dataBookSet)
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            addItemDecoration(MarginItemDecoration(spaceSize = 24, spanCount = 2))
        }
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