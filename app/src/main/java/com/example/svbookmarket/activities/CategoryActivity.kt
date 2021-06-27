package com.example.svbookmarket.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.common.BlurTransformation
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

        setupView()
        setupNavigation()
    }

    private fun setupNavigation() {
        binding.apply {
            cSearchView.setOnClickListener { onBackPressed() }
            cBackButton.setOnClickListener { startIntent("back") }
            cArt.setOnClickListener { startIntent(ART.toString()) }
            cComic.setOnClickListener { startIntent(COMIC.toString()) }
            cFiction.setOnClickListener { startIntent(FICTION.toString()) }
            cNovel.setOnClickListener { startIntent(NOVEL.toString()) }
            cBusiness.setOnClickListener { startIntent(BUSINESS.toString()) }
            cTechnology.setOnClickListener { startIntent(TECHNOLOGY.toString()) }
        }
    }

    private fun setupView() {
        val art = binding.cArtI
        val comic = binding.cComicI
        val fiction = binding.cFictionI
        val novel = binding.cNovelI
        val bus = binding.cBusinessI
        val tech = binding.cTechnologyI

        val list = listOf(
            Pair(R.drawable.img_art, art),
            Pair(R.drawable.img_comic, comic),
            Pair(R.drawable.img_fiction, fiction),
            Pair(R.drawable.img_business, bus),
            Pair(R.drawable.img_tech, tech),
            Pair(R.drawable.img_novel, novel),
        )
        loadImage(list)

    }

    private fun loadImage(list: List<Pair<Int, ImageView>>) {
        for (pair in list) {
            Glide.with(applicationContext)
                .load(pair.first)
                .centerCrop()
                .override(500, 800)
                .transform(BlurTransformation(binding.root.context))
                .placeholder(R.drawable.bg_button_white)
                .error(R.drawable.bg_button_white)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(pair.second)
        }
    }

    private fun startIntent(type: String) {
        val intent = if (type != "back" && type != "search") {

            // get data for intent
            val filteredColl = viewModel.getBooksOfCategory(type)
            val i = Intent(this, CategoryDetailActivity::class.java)

            putDataToIntent(i, filteredColl, type)

        } else {
               Intent(this, SearchActivity::class.java)
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