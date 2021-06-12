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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
import com.example.svbookmarket.activities.viewmodel.HomeViewModel
import com.example.svbookmarket.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), RecyclerViewClickListener {
    lateinit var suggestRecycler: RecyclerView
    lateinit var viewModel: HomeViewModel
    var isBackPressedOnce = false
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        //set up advertise recyclerview
        viewModel.ads.observe(this, Observer { newAds ->
            binding.advertise.apply {
                adapter = AdvertiseAdapter(newAds)
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                LinearSnapHelper().attachToRecyclerView(this)
            }
        })

        //set up category recyclerview
        viewModel.category.observe(this, Observer {newCategory->
            binding.bookCategory.apply {
                adapter = CategoryAdapter(newCategory, this@HomeActivity)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(MarginItemDecoration(spaceSize = 24, isVerticalLayout = true))
            }
        })

        //set up book feature recyclerview
        suggestRecycler = findViewById(R.id.rc_Suggest)
        fillInSuggestRecycle()

        //set up more recyclerview
        viewModel.books.observe(this, Observer { newBooks ->
            binding.hRcMore.apply {
                adapter = FeaturedAdapter(newBooks)
                layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                addItemDecoration(MarginItemDecoration(spaceSize = 24, spanCount = 2))
            }
        })





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

    private fun fillInSuggestRecycle() {
        val dataset = DataSource().loadSuggestCard()
        binding.rcSuggest.apply {
            adapter = SuggestAdapter(context, dataset)
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.HORIZONTAL,
                false
            )
            suggestRecycler.addItemDecoration(RecyclerViewItemMargin(8, dataset.size))
            suggestRecycler.setHasFixedSize(true)
            LinearSnapHelper().attachToRecyclerView(suggestRecycler)

        }

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