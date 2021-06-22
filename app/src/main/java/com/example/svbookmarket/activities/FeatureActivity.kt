package com.example.svbookmarket.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.svbookmarket.activities.adapter.FeaturedAdapter
import com.example.svbookmarket.activities.adapter.MyViewPagerAdapter
import com.example.svbookmarket.activities.adapter.SuggestAdapter
import com.example.svbookmarket.activities.common.Constants
import com.example.svbookmarket.activities.common.MarginItemDecoration
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.viewmodel.FeatureViewModel
import com.example.svbookmarket.databinding.ActivityFeatureBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs


@AndroidEntryPoint
class FeatureActivity : AppCompatActivity(), FeaturedAdapter.OnBookClickLitener,
    SuggestAdapter.OnSuggestClickListener, MyViewPagerAdapter.OnViewPagerClickListener {

    lateinit var binding: ActivityFeatureBinding

    private var featuredAdapter = MyViewPagerAdapter(mutableListOf(), this)
    private var bestSellAdapter = FeaturedAdapter(mutableListOf(), this)
    private var suggestAdapter = SuggestAdapter(mutableListOf(), this)
    private var moreAdapter = FeaturedAdapter(mutableListOf(), this)

    val viewmodel: FeatureViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeatureBinding.inflate(layoutInflater)
        setContentView(binding.root)


        LinearSnapHelper().let {
            it.attachToRecyclerView(binding.rcSuggest)
//            it.attachToRecyclerView(binding.featureRecycler)
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
            var b = books.toList()
            val top5 = b.take(5)
            b = b.drop(5)
            Log.i("something", "${b.size}")

            val top10 = b.take(10)
            b = books.drop(10)
            Log.i("something", "${b.size}")

            featuredAdapter.addBooks(top5)
            bestSellAdapter.addBooks(top10)
            suggestAdapter.addBooks(b)
            moreAdapter.addBooks(b)
        })
    }

    private fun setFeatureRecyclerView() {
//        binding.featureRecycler.let {
//            it.addItemDecoration(
//                MarginItemDecoration(
//                    spaceSize = 64,
//                    orientation = LinearLayoutManager.HORIZONTAL
//                )
//            )
//            it.layoutManager?.scrollToPosition(1)
//            it.adapter = featuredAdapter
//        }

        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(8))
        transformer.addTransformer { page, position ->
            when {
                position < -1 -> {    // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    page.alpha = 0f
                }
                position <= 0 -> {    // [-1,0]
                    page.alpha = 1f
                    page.translationX = 0f
                    page.scaleX = 1f
                    page.scaleY = 1f
                }
                position <= 1 -> {    // (0,1]
                    page.translationX = -position * page.width
                    page.alpha = 1 - abs(position)
                    page.scaleX = 1 - abs(position)
                    page.scaleY = 1 - abs(position)
                }
                else -> {    // (1,+Infinity]
                    // This page is way off-screen to the right.
                    page.alpha = 0f
                }
            }
        }
        binding.fVp2.apply {
            adapter = featuredAdapter
            setPageTransformer(transformer)
        }
    }

    private fun setBestsellingRecyclerView() {
        binding.rcBestselling.let {
            it.layoutManager = LinearLayoutManager(binding.root.context,
                RecyclerView.HORIZONTAL,
                false
            )
            it.addItemDecoration(MarginItemDecoration(spaceSize = 48, isHorizontalLayout = true))
            it.adapter = bestSellAdapter
        }

    }

    /**
     * this use fake data
     */
    private fun setSuggestRecyclerview() {
        binding.rcSuggest.apply {
            adapter = suggestAdapter
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

    /**
     * setup to navigate to detail screen
     */
    override fun onBookClick(book: Book) {
        val i = putBookIntoIntent(book)
        navigate(i)
    }

    override fun onSuggestClick(book: Book) {
        val i = putBookIntoIntent(book)
        navigate(i)
    }

    private fun navigate(mIntent: Intent) = this.binding.root.context.startActivity(mIntent)

    private fun putBookIntoIntent(item: Book): Intent {
        val bundle = Bundle()
        bundle.putParcelable(Constants.ITEM, item)
        val i = Intent(this, ItemDetailActivity::class.java)
        i.putExtras(bundle)
        return i
    }

    override fun onViewPagerClick(book: Book) {
        TODO("Not yet implemented")
    }
}