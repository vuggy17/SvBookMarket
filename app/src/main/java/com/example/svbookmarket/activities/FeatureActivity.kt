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
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.viewmodel.FeatureViewModel
import com.example.svbookmarket.databinding.ActivityFeatureBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeatureActivity : AppCompatActivity(), FeaturedAdapter.OnBookClickLitener,
    SuggestAdapter.OnSuggestClickListener {

    lateinit var binding: ActivityFeatureBinding

    private var featuredAdapter = FeaturedAdapter(mutableListOf(), this)
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
            suggestAdapter.addBooks(books)
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
            it.addItemDecoration(MarginItemDecoration(spaceSize = 24, isHorizontalLayout = true))
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
}