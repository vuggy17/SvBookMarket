package com.example.svbookmarket.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.svbookmarket.activities.adapter.FeaturedAdapter
import com.example.svbookmarket.activities.adapter.SuggestAdapter
import com.example.svbookmarket.activities.common.Constants
import com.example.svbookmarket.activities.common.MarginItemDecoration
import com.example.svbookmarket.activities.data.DataSource
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.viewmodel.FeatureViewModel
import com.example.svbookmarket.databinding.ActivityFeatureBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeatureActivity : AppCompatActivity(), FeaturedAdapter.OnBookClickLitener {

    lateinit var binding: ActivityFeatureBinding

    private var featuredAdapter = FeaturedAdapter(mutableListOf(), this)
    private var bestSellAdapter = FeaturedAdapter(mutableListOf(), this)
    private var suggestAdapter = FeaturedAdapter(mutableListOf(), this)
    private var moreAdapter = FeaturedAdapter(mutableListOf(), this)

    val viewmodel: FeatureViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeatureBinding.inflate(layoutInflater)
        setContentView(binding.root)


        LinearSnapHelper().let {
            it.attachToRecyclerView(binding.rcSuggest)
            it.attachToRecyclerView(binding.featureRecycler)
        }

        watchChanges()

        setFeatureRecyclerView()
        setBestsellingRecyclerView()
        setSuggestRecyclerview()
        setMoreRecyclerView()
    }

    /**
     * watch for changes then update data
     */
    private fun watchChanges() {
        viewmodel.books.observe(this, { books ->
            featuredAdapter.addBooks(books)
            bestSellAdapter.addBooks(books)
            moreAdapter.addBooks(books)
        })
    }

    private fun setFeatureRecyclerView() {
        binding.featureRecycler.let {
            it.addItemDecoration(
                MarginItemDecoration(
                    spaceSize = 64,
                    orientation = LinearLayoutManager.HORIZONTAL
                )
            )
            it.layoutManager?.scrollToPosition(1)
            it.adapter = featuredAdapter
        }
    }

    private fun setBestsellingRecyclerView() {
        binding.rcBestselling.let {
            it.addItemDecoration(MarginItemDecoration(spaceSize = 24, isVerticalLayout = true))
            it.adapter = bestSellAdapter
        }

    }

    /**
     * this use fake data
     */
    private fun setSuggestRecyclerview() {
        // TODO: 16/06/2021 implement load data from db
        val dataset = DataSource().loadSuggestCard()
        binding.rcSuggest.apply {
            adapter = SuggestAdapter(dataset)
            addItemDecoration(
                MarginItemDecoration(
                    spaceSize = 8,
                    orientation = LinearLayoutManager.HORIZONTAL
                )
            )
        }
    }

    private fun setMoreRecyclerView() {
        binding.fRcMore.apply {
            adapter = moreAdapter
            addItemDecoration(MarginItemDecoration(spaceSize = 24, spanCount = 2))
        }

    }

//    private fun fillInMoreRecylerView() {
//        moreRecyclerView.apply {
//            adapter = featuredAdapter
//            addItemDecoration(MarginItemDecoration(spaceSize = 24, spanCount = 2))
//        }
//    }

//    fun fillInFetureRecycle() {
//        val snaphelperFeature: LinearSnapHelper = LinearSnapHelper()
//        featureRecycler.adapter = FeatureAdapter(this, dataset)
//        snaphelperFeature.attachToRecyclerView(featureRecycler)
//        //featureRecycler.layoutManager = LinearLayoutManager(
//        //    this,
//        //    RecyclerView.HORIZONTAL,
//        //   false
//        //)
//        val linearlayout: LinearLayoutManager = LinearLayoutManager(this)
//        linearlayout.orientation = RecyclerView.HORIZONTAL
//        linearlayout.scrollToPosition(1)
//        featureRecycler.setLayoutManager(linearlayout)
//        val decoration = RecyclerViewItemMargin(64, dataset.size)
//        featureRecycler.addItemDecoration(decoration)
//        featureRecycler.setHasFixedSize(true)
//    }
//
//    fun fillInSuggestRecycle() {
//        val dataset = DataSource().loadSuggestCard()
//        suggestRecycler.adapter = SuggestAdapter(dataset)
//        suggestRecycler.layoutManager = LinearLayoutManager(
//            this,
//            RecyclerView.HORIZONTAL,
//            false
//        )
//        val decoration = RecyclerViewItemMargin(8, dataset.size)
//        suggestRecycler.addItemDecoration(decoration)
//        suggestRecycler.setHasFixedSize(true)
//    }

    override fun onBookClick(book: Book) {
        val intent =
            Intent(baseContext, ItemDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(Constants.ITEM, book)
        intent.putExtras(bundle)
        binding.root.context.startActivity(intent)
    }
}