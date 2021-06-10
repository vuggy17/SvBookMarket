package com.example.svbookmarket.activities.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.common.RecyclerViewClickListener
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.databinding.CardBookBinding


class CategoryDetailAdapter(
    var items: MutableList<Book>,
    val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<CategoryDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CardBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(items[position]) {
            holder.let { it ->
                // TODO: 09/06/2021 fix image url mismatch

                it.title.text = title
                it.author.text = author
                it.price.text = price.toString()
                it.rate.text = rating.toString()

                //load image from uri
                Glide
                    .with(holder.itemView)
                    .load(imageURL)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(it.img);

                //set up position touch listener
                it.itemView.setOnClickListener { itemView ->
                    listener.recyclerViewListClicked(itemView, position)
                }
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(binding: CardBookBinding) : RecyclerView.ViewHolder(binding.root) {
        var title = binding.bookTitle
        var author = binding.bookAuthor
        var price = binding.bookPrice
        var rate = binding.tvRate
        var img = binding.BookImage
    }
}