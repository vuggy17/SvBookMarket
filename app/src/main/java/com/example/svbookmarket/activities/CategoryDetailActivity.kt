package com.example.svbookmarket.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.CategoryDetailAdapter
import com.example.svbookmarket.activities.common.MarginItemDecoration
import com.example.svbookmarket.activities.common.RecyclerViewClickListener
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.viewmodel.SharedViewModel
import com.example.svbookmarket.databinding.ActivityCategoryDetailBinding
import java.util.*
import kotlin.collections.ArrayList
import com.example.svbookmarket.activities.data.DataSource as CategoryDetailDataSource

class CategoryDetailActivity : AppCompatActivity(), RecyclerViewClickListener {
    companion object {
        const val CATEGORY_TYPE = "CATEGORY"
        const val ITEM = "ITEMS"
    }

    lateinit var binding: ActivityCategoryDetailBinding
    lateinit var viewmodel: SharedViewModel
   lateinit var items:ArrayList<Book>
    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this).get(SharedViewModel::class.java)

        //temp data
        val bundle = intent.extras
        items = bundle?.getParcelableArrayList<Book>(ITEM) as ArrayList<Book>

        // setup view
        items?.let { setupView(it) }


    }

    override fun recyclerViewListClicked(v: View?, position: Int) = navigate(items, position)

    private fun navigate(items: MutableList<Book>, position: Int) {
        val intent = Intent(this, ItemDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(ItemDetailActivity.ITEM, items[position])
        intent.putExtras(bundle)
        this.binding.root.context.startActivity(intent)

    }

    @ExperimentalStdlibApi
    @SuppressLint("SetTextI18n")
    private fun setupView(items: MutableList<Book>) {
        val categoryName = intent.getStringExtra(CATEGORY_TYPE)

        binding.cdClName.text = "$categoryName Collection"
        binding.cdTitle.text = categoryName
        val backgroundResId = viewmodel.getCollectionImgSource(categoryName!!)

        Glide
            .with(baseContext)
            .load(backgroundResId)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.cdCover);


        binding.cdRc.apply {
            adapter = CategoryDetailAdapter(items, this@CategoryDetailActivity)
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            addItemDecoration(MarginItemDecoration(spaceSize = 14, spanCount = 2))
        }
    }


}






