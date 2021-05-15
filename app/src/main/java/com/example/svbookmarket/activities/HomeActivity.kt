package com.example.svbookmarket.activities

import android.content.Intent
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout.HORIZONTAL
import android.widget.SearchView
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.*
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.AdvertiseAdapter
import com.example.svbookmarket.activities.adapter.CategoryAdapter
import com.example.svbookmarket.activities.adapter.FeaturedAdapter
import com.example.svbookmarket.activities.data.DataSource
import com.example.svbookmarket.databinding.ActivityCategoryBinding
import com.example.svbookmarket.databinding.ActivityHomeBinding
import com.example.svbookmarket.databinding.HomeToolbarBinding
import com.google.android.material.appbar.AppBarLayout

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_home)


//        Load data
        val dataBookSet = DataSource().loadBookFeature()


        //set up book feature catalog
        val recyclerFeature: RecyclerView = findViewById(R.id.booksFeatured)
        recyclerFeature.adapter = FeaturedAdapter(dataBookSet)
        val featureLayoutManager: LinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerFeature.layoutManager = featureLayoutManager


        //set up all book catalog
        val recyclerAllBook: RecyclerView = findViewById(R.id.bookCatalog)
        recyclerAllBook.adapter = FeaturedAdapter(dataBookSet)
        val allBookCatalogLayoutManager: GridLayoutManager = GridLayoutManager(this, 2)
        recyclerAllBook.layoutManager = allBookCatalogLayoutManager


        //set up adapter to category
        val dataCategorySet = DataSource().loadCategory()
        val recyclerCategory: RecyclerView = findViewById(R.id.bookCategory)
        recyclerCategory.adapter = CategoryAdapter(dataCategorySet)
        val categoryLayoutManager: LinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerCategory.layoutManager = categoryLayoutManager

        //set up adapter to advertise
        val dataAdvertiseSet =  DataSource().loadAdvertise()
        val recyclerAdvertise: RecyclerView = findViewById(R.id.advertise)
        recyclerAdvertise.adapter = AdvertiseAdapter(dataAdvertiseSet)
        val advertiseLayoutManagemer: LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerAdvertise.layoutManager = advertiseLayoutManagemer
        val snapHelper: LinearSnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerAdvertise)


        //binding for intent
        findViewById<ImageView>(R.id.tb_menu).setOnClickListener { startIntent("menu") }
        findViewById<SearchView>(R.id.tb_searchView).setOnClickListener { startIntent("search") }
        findViewById<ImageView>(R.id.tb_cart).setOnClickListener{ startIntent("cart") }
        findViewById<TextView>(R.id.h_allCategory).setOnClickListener{startIntent("category")}
        findViewById<TextView>(R.id.h_allFeature).setOnClickListener{startIntent("feature")}
    }

    private fun startIntent(type:String){
        val intent = when(type){
            "menu"-> Intent(this, MenuActivity::class.java)
            "search"-> Intent(this, SearchActivity::class.java)
            "cart"-> Intent(this, CartActivity::class.java)
            "category"-> Intent(this, CategoryActivity::class.java)
            else-> Intent(this, FeatureActivity::class.java)
        }
        startActivity(intent)
    }
}