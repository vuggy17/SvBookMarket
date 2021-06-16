package com.example.svbookmarket.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.CategoryDetailAdapter
import com.example.svbookmarket.activities.common.Constants.ACTIVITY.CATEGORY_DETAIL
import com.example.svbookmarket.activities.common.MarginItemDecoration
import com.example.svbookmarket.activities.common.RecyclerViewClickListener
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.viewmodel.CategoryDetailViewModel
import com.example.svbookmarket.databinding.ActivityCategoryDetailBinding

class CategoryDetailActivity : AppCompatActivity(), RecyclerViewClickListener {
    companion object {
        const val CATEGORY_TYPE = "CATEGORY"
        const val ITEM = "ITEMS"
    }

    lateinit var binding: ActivityCategoryDetailBinding
    lateinit var items: ArrayList<Book>
    private val viewmodel: CategoryDetailViewModel by viewModels()

    private val detailApt = CategoryDetailAdapter(mutableListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    override fun recyclerViewListClicked(v: View?, position: Int) = navigate(items, position)

    private fun navigate(items: MutableList<Book>, position: Int) {
        val intent = Intent(this, ItemDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(ITEM, items[position])
        intent.putExtras(bundle)
        this.binding.root.context.startActivity(intent)

        setupView()
    }

    private fun setupView() {
        setDetailAdapter()
        displayCategory(getCategoryNameFromIntent())
    }


    @SuppressLint("SetTextI18n")
    private fun displayCategory(categoryName: String) {
        binding.cdClName.text = "$categoryName Collection"
        binding.cdTitle.text = categoryName
        val backgroundResId = getCategorybg(categoryName)

        // load image
        Glide
            .with(baseContext)
            .load(backgroundResId)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.cdCover);


        binding.cdRc.apply {
            adapter = detailApt
            addItemDecoration(MarginItemDecoration(spaceSize = 14, spanCount = 2))
        }

    }

    private fun getCategorybg(categoryName: String): Int =
        viewmodel.getCollectionImgSource(categoryName)

    private fun setDetailAdapter() {
        val books = getBooksFromIntent()
        detailApt.addBooks(books)
    }

    private fun getBooksFromIntent(): ArrayList<Book> {
        val bundle = intent.extras
        items = bundle?.getParcelableArrayList<Book>(CATEGORY_DETAIL.toString()) as ArrayList<Book>
        return items
    }
    private fun getCategoryNameFromIntent(): String = intent.getStringExtra(CATEGORY_TYPE)!!



}






