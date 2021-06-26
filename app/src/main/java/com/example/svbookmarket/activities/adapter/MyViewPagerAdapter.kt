package com.example.svbookmarket.activities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.MyViewPagerAdapter.ViewHolder
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.databinding.PagerItemBinding

class MyViewPagerAdapter(
    private val items: MutableList<Book>,
    private val listener: OnViewPagerClickListener
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PagerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(items[position]) {
            holder.let {
                it.bookTitle.text = Name
                it.pageDescription.text = Description
                it.pageTitle.text = Name

                Glide
                    .with(it.itemView)
                    .load(Image)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(it.bookThumbnail);

                it.cardBook.setOnClickListener{ listener.onViewPagerClick(this)}
            }
        }
    }

    fun addBooks(book: List<Book>) {
        if (this.items.isNotEmpty()) {
            this.items.clear()
        }
        this.items.addAll(book)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = items.size

    inner class ViewHolder(binding: PagerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val pageDescription = binding.pageDes
        val pageTitle = binding.pageTitle
        val bookTitle = binding.pageBookTitle
        val bookThumbnail = binding.pageBookThumbnail
        val cardBook = binding.cardBook
    }


    interface OnViewPagerClickListener {
        fun onViewPagerClick(book: Book)
    }

}