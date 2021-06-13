package com.example.svbookmarket.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.viewmodel.CategoryViewModel
import com.example.svbookmarket.activities.viewmodel.CategoryViewModelFactory
import com.example.svbookmarket.databinding.ActivityCategoryBinding


class CategoryActivity : AppCompatActivity() {
    companion object{
        enum class CATEGORY{
            COMIC, FICTION, NOVEL, BUSINESS, TECHNOLOGY, ART;

            override fun toString(): String {
                return name.toLowerCase().capitalize()
            }
        }
        const val ITEMS = "CATEGORY"
    }
    lateinit var viewModel: CategoryViewModel
    lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle= intent.extras
        val item = bundle?.getParcelableArrayList<Book>(ITEMS)!!
        val viewModelFactory = CategoryViewModelFactory(item)
        viewModel = ViewModelProvider(this,viewModelFactory).get(CategoryViewModel::class.java)

        setupIntent()
    }

    private fun startIntent(type: String){
        val intent = if (type != "back" && type != "search") {
            var bundle = Bundle()
            val i =  viewModel.getBooksOfCategory(type)

            bundle.putParcelableArrayList(CategoryDetailActivity.ITEM, i)

            Intent(this, CategoryDetailActivity::class.java)
                .putExtras(bundle)
                .putExtra(CategoryDetailActivity.CATEGORY_TYPE, type)
        } else {
            when (type) {
                "search" -> Intent(this, SearchActivity::class.java)
                else -> Intent(this, HomeActivity::class.java)
            }
        }
        startActivity(intent)
    }

    private fun setupIntent(){
        binding.apply {
            cSearchView.setOnClickListener { startIntent("search") }
            cBackButton.setOnClickListener { startIntent("back") }
            cArt.setOnClickListener { startIntent(CATEGORY.ART.toString())}
            cComic.setOnClickListener { startIntent(CATEGORY.COMIC.toString())}
            cFiction.setOnClickListener { startIntent(CATEGORY.FICTION.toString()) }
            cNovel.setOnClickListener { startIntent(CATEGORY.NOVEL.toString()) }
            cBusiness.setOnClickListener { startIntent(CATEGORY.BUSINESS.toString()) }
            cTechnology.setOnClickListener { startIntent(CATEGORY.TECHNOLOGY.toString()) }
        }
    }
}