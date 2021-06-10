package com.example.svbookmarket.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.svbookmarket.databinding.ActivityCategoryBinding


class CategoryActivity : AppCompatActivity() {
    companion object{
        enum class CATEGORY{
            COMIC, FICTION, NOVEL, BUSINESS, TECHNOLOGY, ART;

            override fun toString(): String {
                return name.toLowerCase().capitalize()
            }
        }
    }
    lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setup intent
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

    private fun startIntent(type: String){
        val intent = if (type != "back" && type != "search") {
            Intent(this, CategoryDetailActivity::class.java)
                .putExtra(CategoryDetailActivity.CATEGORY_TYPE, type)
        } else {
            when (type) {
                "search" -> Intent(this, SearchActivity::class.java)
                else -> Intent(this, HomeActivity::class.java)
            }
        }

        startActivity(intent)
    }
}