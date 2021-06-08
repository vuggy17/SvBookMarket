package com.example.svbookmarket.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.AdvertiseAdapter
import com.example.svbookmarket.activities.adapter.CategoryAdapter
import com.example.svbookmarket.activities.adapter.FeaturedAdapter
import com.example.svbookmarket.activities.common.MarginItemDecoration
import com.example.svbookmarket.activities.data.DataSource

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_home)


//        Load data
        val dataBookSet = DataSource().loadBookFeature()


        //set up book feature catalog
        val recyclerFeature: RecyclerView = findViewById(R.id.booksFeatured)
        recyclerFeature.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = FeaturedAdapter(dataBookSet)
            addItemDecoration(MarginItemDecoration(spaceSize = 24, isHorizontalLinearLayout = true))
        }


        //set up all book catalog
        val recyclerAllBook: RecyclerView = findViewById(R.id.bookCatalog)
        recyclerAllBook.apply {
            adapter = FeaturedAdapter(dataBookSet)
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            addItemDecoration(MarginItemDecoration(spaceSize = 14, spanCount = 2))
        }


        //set up adapter to category
        val recyclerCategory: RecyclerView = findViewById(R.id.bookCategory)
        recyclerCategory.apply {
            val dataCategorySet = DataSource().loadCategory()
            adapter = CategoryAdapter(dataCategorySet)
            addItemDecoration(MarginItemDecoration(spaceSize = 24, isHorizontalLinearLayout = true))
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }


        //set up adapter to advertise
        val dataAdvertiseSet = DataSource().loadAdvertise()
        val recyclerAdvertise: RecyclerView = findViewById(R.id.advertise)
        recyclerAdvertise.adapter = AdvertiseAdapter(dataAdvertiseSet)
        val advertiseLayoutManagemer: LinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerAdvertise.layoutManager = advertiseLayoutManagemer
        val snapHelper: LinearSnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerAdvertise)


        //binding for intent
        findViewById<ImageView>(R.id.tb_menu).setOnClickListener { startIntent("menu") }
        findViewById<SearchView>(R.id.tb_searchView).setOnClickListener { startIntent("search") }
        findViewById<ImageView>(R.id.tb_cart).setOnClickListener { startIntent("cart") }
        findViewById<TextView>(R.id.h_allCategory).setOnClickListener { startIntent("category") }
        findViewById<TextView>(R.id.h_allFeature).setOnClickListener { startIntent("feature") }
    }

    private fun startIntent(type: String) {
        val intent = when (type) {
            "menu" -> Intent(this, MenuActivity::class.java)
            "search" -> Intent(this, SearchActivity::class.java)
            "cart" -> Intent(this, CartActivity::class.java)
            "category" -> Intent(this, CategoryActivity::class.java)
            else -> Intent(this, FeatureActivity::class.java)
        }
        startActivity(intent)
    }
}