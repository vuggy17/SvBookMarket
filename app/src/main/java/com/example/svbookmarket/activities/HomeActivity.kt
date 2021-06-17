package com.example.svbookmarket.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.AdvertiseAdapter
import com.example.svbookmarket.activities.adapter.CategoryAdapter
import com.example.svbookmarket.activities.adapter.FeaturedAdapter
import com.example.svbookmarket.activities.adapter.SuggestAdapter
import com.example.svbookmarket.activities.common.Constants
import com.example.svbookmarket.activities.common.Constants.ACTIVITY
import com.example.svbookmarket.activities.common.Constants.ACTIVITY.*
import com.example.svbookmarket.activities.common.Constants.ITEM
import com.example.svbookmarket.activities.common.MarginItemDecoration
import com.example.svbookmarket.activities.common.RecyclerViewItemMargin
import com.example.svbookmarket.activities.data.DataSource
import com.example.svbookmarket.activities.data.Response.*
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.viewmodel.HomeViewModel
import com.example.svbookmarket.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import io.grpc.InternalChannelz.id
import java.lang.Thread.sleep


@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), FeaturedAdapter.OnBookClickLitener,
    CategoryAdapter.onCategoryItemClick {
    lateinit var binding: ActivityHomeBinding

    val viewModel: HomeViewModel by viewModels()

    private var adsAdapter = AdvertiseAdapter(mutableListOf())
    private var catgoryAdapter = CategoryAdapter(mutableListOf(), this@HomeActivity)
    private var suggestAdapter = SuggestAdapter(mutableListOf())
    private var moreAdapter = FeaturedAdapter(mutableListOf(), this)
    var isBackPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // watch data change
        getBooks()
        setAdsAdapter()
        setCategoryAdapter()
        setSuggestAdapter()
        setMoreAdapter()
        setupNavigation()
        setUpBottomNavigationView()


    }

    private fun getBooks() {
        viewModel.getBookFrom().observe(this, { changes ->
            moreAdapter.addBooks(changes)
        })

        // TODO: 16/06/2021  ads, suggest se duoc implement khi db hoan thanh
    }

    private fun setSuggestAdapter() {
        val dataset = DataSource().loadSuggestCard()
        binding.rcSuggest.apply {
            adapter = SuggestAdapter(dataset)
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.HORIZONTAL,
                false
            )
            addItemDecoration(RecyclerViewItemMargin(8, dataset.size))
            setHasFixedSize(true)
            LinearSnapHelper().attachToRecyclerView(this)

        }
    }

    private fun setAdsAdapter() {
        viewModel.ads.observe(this, Observer { newAds ->
            binding.advertise.apply {
                adapter = AdvertiseAdapter(newAds)
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                LinearSnapHelper().attachToRecyclerView(this)
            }
        })

    }

    private fun setCategoryAdapter() {
        viewModel.category.observe(this, Observer { newCategory ->
            binding.bookCategory.apply {
                adapter = CategoryAdapter(newCategory, this@HomeActivity)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(MarginItemDecoration(spaceSize = 20, isVerticalLayout = true))
            }
        })

    }

    private fun setMoreAdapter() {
        binding.hRcMore.apply {
            adapter = moreAdapter
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            addItemDecoration(MarginItemDecoration(spaceSize = 15, spanCount = 2))
        }
    }

    /**
     * navigate when click on screen
     */
    private fun setupNavigation() {
//        findViewById<ImageView>(R.id.tb_menu).setOnClickListener { startIntent(MENU) }
        findViewById<SearchView>(R.id.tb_searchView).setOnClickListener { startIntent(SEARCH) }
//       findViewById<ImageView>(R.id.tb_cart).setOnClickListener { startIntent("cart") }
        findViewById<TextView>(R.id.h_allCategory).setOnClickListener { startIntent(CATEGORY) }
        findViewById<TextView>(R.id.h_allFeature).setOnClickListener { startIntent(FEATURE) }
    }


    /**
     * start an intent for navigating
     */
    private fun startIntent(name: ACTIVITY) {
        val intent = when (name) {
            MENU -> Intent(this, MenuActivity::class.java)
            SEARCH -> Intent(this, SearchActivity::class.java)
            CART -> Intent(this, CartActivity::class.java)
            CATEGORY -> {
                Intent(this, CategoryActivity::class.java)
            }
            FEATURE -> Intent(this, FeatureActivity::class.java)
            CATEGORY_DETAIL -> Intent(this, CategoryDetailActivity::class.java)
                .putExtra(CategoryDetailActivity.CATEGORY_TYPE, name)
        }
        startActivity(intent)
    }


    /**
     * put book to intent then start navigation
     */
    override fun onBookClick(book: Book) {
        val intent =
            Intent(binding.root.context, ItemDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(ITEM, book)
        intent.putExtras(bundle)
        binding.root.context.startActivity(intent)
    }


    /**
     * put category name to intent then start navigation
     */
    override fun onCategoryItemClick(categoryName: String) {
        var bundle = Bundle()
        val i = viewModel.getBooksOfCategory(categoryName) as ArrayList<Book>

        bundle.putParcelableArrayList(CATEGORY_DETAIL.toString(), i)

        val intent = Intent(this, CategoryDetailActivity::class.java)
            .putExtras(bundle)
            .putExtra(CategoryDetailActivity.CATEGORY_TYPE, categoryName)

        binding.root.context.startActivity(intent)

    }


    /**
     * prevent instant exit
     */
    override fun onBackPressed() {
        if (isBackPressedOnce) {
            super.onBackPressed()
            return
        }

        this.isBackPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { isBackPressedOnce = false }, 2000)
    }
    private fun setUpBottomNavigationView(){

        binding.bottomNavigation.add(MeowBottomNavigation.Model(id= 1, R.drawable.ic_baseline_person_24))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(id= 2, R.drawable.ic_home))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(id= 3, R.drawable.ic_baseline_shopping_cart_24))
        binding.bottomNavigation.setCount( id=3, "3")
        binding.bottomNavigation.show(id = 2,   true)
        binding.bottomNavigation.setOnClickMenuListener {

            if(it.id ==3){

                startIntent(Constants.ACTIVITY.CART)
            }


        }
        binding.bottomNavigation.setOnShowListener {
            if(it.id ==3){
               binding.bottomNavigation.show(2, true)
            }
        }
    }


}
