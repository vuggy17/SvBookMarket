package com.example.svbookmarket.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.svbookmarket.activities.common.Constants.ACTIVITY.CATEGORY_DETAIL
import com.example.svbookmarket.activities.common.Constants.CATEGORY.*
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.viewmodel.CategoryViewModel
import com.example.svbookmarket.databinding.ActivityCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryActivity : AppCompatActivity() {

    lateinit var binding: ActivityCategoryBinding
    private val viewModel: CategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {
        binding.apply {
            cSearchView.setOnClickListener { startIntent("search") }
            cBackButton.setOnClickListener { startIntent("back") }
            cArt.setOnClickListener { startIntent(ART.toString()) }
            cComic.setOnClickListener { startIntent(COMIC.toString()) }
            cFiction.setOnClickListener { startIntent(FICTION.toString()) }
            cNovel.setOnClickListener { startIntent(NOVEL.toString()) }
            cBusiness.setOnClickListener { startIntent(BUSINESS.toString()) }
            cTechnology.setOnClickListener { startIntent(TECHNOLOGY.toString()) }
        }
    }

    private fun startIntent(type: String) {
        val intent = if (type != "back" && type != "search") {

            // get data for intent
            val filteredColl = viewModel.getBooksOfCategory(type)
            val i = Intent(this, CategoryDetailActivity::class.java)

            putDataToIntent(i, filteredColl, type)

        } else {
            when (type) {
                "search" -> Intent(this, SearchActivity::class.java)
                else -> Intent(this, HomeActivity::class.java)
            }
        }
        startActivity(intent)
    }

    /**
     * put into and return intent
     */
    private fun putDataToIntent(i: Intent, data: ArrayList<Book>?, type: String): Intent {
        val bundle = putDataToBundle(data)
        i.putExtras(bundle)
            .putExtra(CategoryDetailActivity.CATEGORY_TYPE, type)
        return i
    }

    /**
     * put into and return bunde
     */
    private fun putDataToBundle(data: ArrayList<Book>?): Bundle {
        val bundle = Bundle()
        bundle.putParcelableArrayList(CATEGORY_DETAIL.toString(), data)
        return bundle
    }

}