package com.example.svbookmarket.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
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
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), RecyclerViewClickListener {
    companion object {
        enum class ACTIVITY {
            MENU, SEARCH, CART, CATEGORY, FEATURE, CATEGORY_DETAIL;

            override fun toString(): String {
                return name.toLowerCase().capitalize()
            }

        }
    }

    lateinit var suggestRecycler: RecyclerView
    val viewModel: HomeViewModel by viewModels()
    var isBackPressedOnce = false
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        viewModel.category.observe(this, Observer { newCategory ->
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
        setupNavigation()


    }

    private fun startIntent(name: ACTIVITY) {
        val intent = when (name) {
            ACTIVITY.MENU -> Intent(this, MenuActivity::class.java)
            ACTIVITY.SEARCH -> Intent(this, SearchActivity::class.java)
            ACTIVITY.CART -> Intent(this, CartActivity::class.java)
            ACTIVITY.CATEGORY -> {
                val bundle = Bundle()
                bundle.putParcelableArrayList(
                    CategoryActivity.ITEMS,
                    ArrayList(viewModel.books.value)
                )
                Intent(this, CategoryActivity::class.java).putExtras(bundle)
            }
            ACTIVITY.FEATURE -> Intent(this, FeatureActivity::class.java)
            ACTIVITY.CATEGORY_DETAIL -> Intent(this, CategoryDetailActivity::class.java)
                .putExtra(CategoryDetailActivity.CATEGORY_TYPE, name)
        }
        startActivity(intent)
    }

    private fun navigateToCategory(name: CategoryActivity.Companion.CATEGORY) {
        var bundle = Bundle()
        val i = viewModel.getBooksOfCategory(name.toString())

        bundle.putParcelableArrayList(CategoryDetailActivity.ITEM, i)

        val intent = Intent(this, CategoryDetailActivity::class.java)
            .putExtras(bundle)
            .putExtra(CategoryDetailActivity.CATEGORY_TYPE, name.toString())

        binding.root.context.startActivity(intent)

    }

    private fun setupNavigation() {
        findViewById<ImageView>(R.id.tb_menu).setOnClickListener { startIntent(ACTIVITY.MENU) }
        findViewById<SearchView>(R.id.tb_searchView).setOnClickListener { startIntent(ACTIVITY.SEARCH) }
//        findViewById<ImageView>(R.id.tb_cart).setOnClickListener { startIntent("cart") }
        findViewById<TextView>(R.id.h_allCategory).setOnClickListener { startIntent(ACTIVITY.CATEGORY) }
        findViewById<TextView>(R.id.h_allFeature).setOnClickListener { startIntent(ACTIVITY.FEATURE) }

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
        val itemName = viewModel.category.value?.get(position)?.name?.toUpperCase()?.trim()
        try {
            navigateToCategory(CategoryActivity.Companion.CATEGORY.valueOf(itemName!!))
        } catch (e: IllegalArgumentException) {
            Log.i("homea", "catch exception ${e.message}")
        }
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