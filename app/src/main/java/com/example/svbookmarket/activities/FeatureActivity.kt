package com.example.svbookmarket.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.BestsellingAdapter
import com.example.svbookmarket.activities.adapter.FeatureAdapter
import com.example.svbookmarket.activities.adapter.FeaturedAdapter
import com.example.svbookmarket.activities.adapter.SuggestAdapter
import com.example.svbookmarket.activities.common.Constants
import com.example.svbookmarket.activities.common.MarginItemDecoration
import com.example.svbookmarket.activities.common.RecyclerViewItemMargin
import com.example.svbookmarket.activities.data.DataSource
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.viewmodel.FeatureViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeatureActivity : AppCompatActivity(), FeaturedAdapter.OnBookClickLitener {
    lateinit var featureRecycler: RecyclerView
    lateinit var bestsellingRecycler: RecyclerView
    lateinit var suggestRecycler: RecyclerView
    lateinit var moreRecyclerView: RecyclerView
    private var featuredAdapter = FeaturedAdapter(mutableListOf(), this)

//    lateinit var viewmodel: FeatureViewModel

    private val viewmodel: FeatureViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature)
//        viewmodel = ViewModelProvider(this).get(FeatureViewModel::class.java)

        featureRecycler = findViewById(R.id.feature_recycler)
        bestsellingRecycler = findViewById(R.id.rc_Bestselling)
        suggestRecycler = findViewById(R.id.rc_Suggest)
        moreRecyclerView = findViewById(R.id.f_rcMore)


        fillInFetureRecycle()
        fillInBestRecycle()
        fillInSuggestRecycle()
        fillInMoreRecylerView()


        LinearSnapHelper().let {
            it.attachToRecyclerView(suggestRecycler)
            it.attachToRecyclerView(bestsellingRecycler)
        }

        getBooks()
    }


    private fun getBooks() {
        viewmodel.books.observe(this, { response ->
            featuredAdapter.addBooks(response)
        })
    }

    private fun fillInMoreRecylerView() {

        moreRecyclerView.apply {
            adapter = featuredAdapter
            addItemDecoration(MarginItemDecoration(spaceSize = 24, spanCount = 2))
        }
    }

    fun fillInFetureRecycle() {
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

    fun fillInBestRecycle() {
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
    }

    override fun onBookClick(book: Book) {
        val intent =
            Intent(baseContext, ItemDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(Constants.ITEM, book)
        intent.putExtras(bundle)
        baseContext.startActivity(intent)
    }
}