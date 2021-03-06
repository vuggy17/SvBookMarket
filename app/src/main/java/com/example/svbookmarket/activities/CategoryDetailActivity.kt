package com.example.svbookmarket.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.signature.MediaStoreSignature
import com.bumptech.glide.signature.ObjectKey
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.CategoryDetailAdapter
import com.example.svbookmarket.activities.common.BlurTransformation
import com.example.svbookmarket.activities.common.Constants.ACTIVITY.CATEGORY_DETAIL
import com.example.svbookmarket.activities.common.Constants.ITEM
import com.example.svbookmarket.activities.common.MarginItemDecoration
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.viewmodel.CategoryDetailViewModel
import com.example.svbookmarket.databinding.ActivityCategoryDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryDetailActivity : AppCompatActivity(), CategoryDetailAdapter.OnCategoryClickListener {
    companion object {
        const val CATEGORY_TYPE = "CATEGORY"
    }

    lateinit var binding: ActivityCategoryDetailBinding
    lateinit var items: ArrayList<Book>
    private val viewmodel: CategoryDetailViewModel by viewModels()

    private val detailApt = CategoryDetailAdapter(mutableListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            .placeholder(R.drawable.bg_button_white)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.cdCover);


        binding.cdRc.apply {
            adapter = detailApt
            addItemDecoration(MarginItemDecoration(spaceSize = 14, spanCount = 2))
        }

    }

    @DrawableRes
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


    override fun onCategoryItemClick(item: Book) {
        val i = putBookIntoIntent(item)
        navigate(i)
    }

    private fun navigate(mIntent: Intent) = this.binding.root.context.startActivity(mIntent)

    private fun putBookIntoIntent(item: Book): Intent {
        val bundle = Bundle()
        bundle.putParcelable(ITEM, item)
        val i = Intent(this, ItemDetailActivity::class.java)
        i.putExtras(bundle)
        return i
    }
}






