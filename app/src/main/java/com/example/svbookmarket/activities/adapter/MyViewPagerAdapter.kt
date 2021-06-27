package com.example.svbookmarket.activities.adapter

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.graphics.luminance
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.MyViewPagerAdapter.ViewHolder
import com.example.svbookmarket.activities.common.Constants
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
                    .asBitmap()
                    .load(Image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .placeholder(Constants.DEFAULT_IMG_PLACEHOLDER)
                    .listener(object : RequestListener<Bitmap> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Log.i("featurecolor", "load failed}")

                            return false
                        }

                        @RequiresApi(Build.VERSION_CODES.O)
                        override fun onResourceReady(
                            resource: Bitmap?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            if (resource != null) {
                                it.getPaletteFromBitmap(resource)
                                it.setColor()
                            }
                            return false
                        }
                    })
                    .into(it.bookThumbnail);

                Log.i("featurecolor", "${it.dominanceColor}")


                it.cardBook.setOnClickListener { listener.onViewPagerClick(this) }
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
        var dominanceColor: Int = Color.WHITE
        val pageDescription = binding.pageDes
        val pageTitle = binding.pageTitle
        val bookTitle = binding.pageBookTitle
        val bookThumbnail = binding.pageBookThumbnail
        val cardBook = binding.cardBook

        val DEFAULT_COLOR = ContextCompat.getColor(
            this.itemView.context,
            R.color.white
        )

        private var palette: Palette? = null
        private var dominantColor = DEFAULT_COLOR


        @RequiresApi(Build.VERSION_CODES.O)
        fun setColor() {
            setMTextColor()
            setBackgroundColor()
        }

        fun getPaletteFromBitmap(bitmap: Bitmap) {
            this.palette = Palette.from(bitmap).generate()
        }

        @RequiresApi(Build.VERSION_CODES.O)
        private fun setMTextColor() {
            val color = this.computeTextColor()
            pageTitle.setTextColor(color)
            pageDescription.setTextColor(color)
        }

        /**
         * return text color based on background color
         */
        @RequiresApi(Build.VERSION_CODES.O)
        private fun computeTextColor(): Int {
            setDominantColor()
            // use dominant color to compute color
            return if (this.dominantColor.luminance > 0.5) Color.BLACK else Color.WHITE
        }

        private fun setDominantColor() {
            this.dominantColor = palette?.getDominantColor(DEFAULT_COLOR)!!
        }

        private fun setBackgroundColor() {
            // get muted color from image, if cannot, return default color (green)
            var c = palette?.lightMutedSwatch?.rgb ?: DEFAULT_COLOR

            // if cannot get light muted color, use dominant color
            if (c.compareTo(DEFAULT_COLOR) == 0) {
                Log.i("comparecolor", "true")
                c = palette?.dominantSwatch?.rgb ?: DEFAULT_COLOR
            }
            //create gradient background
            val colors = intArrayOf(
                c, Color.WHITE
            )
            val gd = GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, colors,
            )

            this.itemView.background = gd
        }


    }


    interface OnViewPagerClickListener {
        fun onViewPagerClick(book: Book)
    }

}