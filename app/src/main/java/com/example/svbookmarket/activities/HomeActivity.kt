package com.example.svbookmarket.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.AdvertiseAdapter
import com.example.svbookmarket.activities.adapter.CategoryAdapter
import com.example.svbookmarket.activities.adapter.FeaturedAdapter
import com.example.svbookmarket.activities.adapter.SuggestAdapter
import com.example.svbookmarket.activities.common.MarginItemDecoration
import com.example.svbookmarket.activities.common.RecyclerViewClickListener
import com.example.svbookmarket.activities.common.RecyclerViewItemMargin
import com.example.svbookmarket.activities.data.DataSource
import com.example.svbookmarket.activities.data.FullBookList

class HomeActivity : AppCompatActivity(), RecyclerViewClickListener {
    lateinit var suggestRecycler: RecyclerView
    var isBackPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        Load data
        val dataBookSet = DataSource().loadBookFeature()

        //set up advertise recyclerview
        val dataAdvertiseSet = DataSource().loadAdvertise()
        val recyclerAdvertise: RecyclerView = findViewById(R.id.advertise)
        recyclerAdvertise.adapter = AdvertiseAdapter(dataAdvertiseSet)
        val advertiseLayoutManagemer: LinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerAdvertise.layoutManager = advertiseLayoutManagemer
        val snapHelper: LinearSnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerAdvertise)


        //set up category recyclerview
        val recyclerCategory: RecyclerView = findViewById(R.id.bookCategory)
        recyclerCategory.apply {
            val dataCategorySet = DataSource().loadCategory()
            adapter = CategoryAdapter(dataCategorySet, this@HomeActivity)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(MarginItemDecoration(spaceSize = 24, isVerticalLayout = true))

        }


        //set up book feature recyclerview
        suggestRecycler = findViewById(R.id.rc_Suggest)
        fillInSuggestRecycle()


        //set up more recyclerview
        val recyclerAllBook: RecyclerView = findViewById(R.id.h_rcMore)
        recyclerAllBook.apply {
            adapter = FeaturedAdapter(dataBookSet)
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            addItemDecoration(MarginItemDecoration(spaceSize = 24, spanCount = 2))
        }


        //setup intent
        setupIntent()


    }

    private fun startIntent(type: String) {

        val intent = when (type) {
            "menu" -> Intent(this, MenuActivity::class.java)
            "search" -> Intent(this, SearchActivity::class.java)
            "cart" -> Intent(this, CartActivity::class.java)
            "category" -> Intent(this, CategoryActivity::class.java)
            "feature" -> Intent(this, FeatureActivity::class.java)
            else -> {
                Intent(this, CategoryDetailActivity::class.java)
                    .putExtra(CategoryDetailActivity.CATEGORY_TYPE, type)
            }
        }

        startActivity(intent)
    }

    private fun setupIntent() {
        findViewById<ImageView>(R.id.tb_menu).setOnClickListener { startIntent("menu") }
        findViewById<SearchView>(R.id.tb_searchView).setOnClickListener { startIntent("search") }
//        findViewById<ImageView>(R.id.tb_cart).setOnClickListener { startIntent("cart") }
        findViewById<TextView>(R.id.h_allCategory).setOnClickListener { startIntent("category") }
        findViewById<TextView>(R.id.h_allFeature).setOnClickListener { startIntent("feature") }

    }

    fun fillInSuggestRecycle() {
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

        LinearSnapHelper().attachToRecyclerView(suggestRecycler)

    }

    override fun recyclerViewListClicked(v: View?, position: Int) {
        val dataCategorySet = DataSource().loadCategory()
        val itemName = dataCategorySet[position].name
        startIntent(itemName)
    }

    override fun onBackPressed() {
        if (isBackPressedOnce) {
            super.onBackPressed()
            return
        }

        this.isBackPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { isBackPressedOnce = false }, 2000)
    }
}