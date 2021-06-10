package com.example.svbookmarket.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.CategoryDetailAdapter
import com.example.svbookmarket.activities.common.MarginItemDecoration
import com.example.svbookmarket.activities.common.RecyclerViewClickListener
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.databinding.ActivityCategoryDetailBinding
import java.util.*
import com.example.svbookmarket.activities.data.DataSource as CategoryDetailDataSource

class CategoryDetailActivity : AppCompatActivity(), RecyclerViewClickListener {
    companion object {
        val CATEGORY_TYPE = "CATEGORY"
        val CATEGORY_IMG = "CATEGORY_IMG"
    }

    lateinit var binding: ActivityCategoryDetailBinding
    lateinit var items: MutableList<Book>

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: 09/06/2021 implement get data list from model
        // TODO: 09/06/2021 nho filter item truoc khi truyen vao
        // model.loadData(getExtras(categoryName)

        //temp data
        items = intent.getStringExtra(CATEGORY_TYPE).let {
            CategoryDetailDataSource().loadCategory(
                it.toString()
            )
        }

        // setup view
        items?.let { setupView(binding, it) }


    }

    override fun recyclerViewListClicked(v: View?, position: Int) = navigate(items, position)

    @ExperimentalStdlibApi
    @SuppressLint("SetTextI18n")
    private fun setupView(binding: ActivityCategoryDetailBinding, items: MutableList<Book>) {
        val categoryName = intent.getStringExtra(CATEGORY_TYPE)

        binding.cdClName.text = "$categoryName Collection"
        binding.cdTitle.text = categoryName
        binding.cdCover.setImageResource(getCollectionImgSource(categoryName!!))



        binding.cdRc.apply {
            adapter = CategoryDetailAdapter(items, this@CategoryDetailActivity)
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            addItemDecoration(MarginItemDecoration(spaceSize = 14, spanCount = 2))
        }
    }


    private fun navigate(items: MutableList<Book>, position: Int) {
        val intent = Intent(this, ItemDetialActivity::class.java)
        val bundle = Bundle()
        with(items[position]) {
            bundle.let {
                it.putString(ItemDetialActivity.TITLE, this.title)
                it.putString(ItemDetialActivity.AUTHOR, this.author)
                it.putString(ItemDetialActivity.PRICE, this.price.toString())
                it.putString(ItemDetialActivity.RATEPOINT, this.rating.toString())
            }

            intent.putExtra("Bundle", bundle)
        }

        startActivity(intent, bundle);
    }


    @DrawableRes
    private fun getCollectionImgSource(categoryName: String): Int {
        return when (categoryName) {
            CategoryActivity.Companion.CATEGORY.COMIC.toString() -> R.drawable.img_comic
            CategoryActivity.Companion.CATEGORY.FICTION.toString() -> R.drawable.img_fiction
            CategoryActivity.Companion.CATEGORY.NOVEL.toString() -> R.drawable.img_novel
            CategoryActivity.Companion.CATEGORY.BUSINESS.toString() -> R.drawable.img_business
            CategoryActivity.Companion.CATEGORY.TECHNOLOGY.toString() -> R.drawable.img_tech
            else -> R.drawable.img_art
        }
    }



}