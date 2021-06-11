package com.example.svbookmarket.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.svbookmarket.R
import com.example.svbookmarket.databinding.ActivityItemDetailBinding

class ItemDetailActivity : AppCompatActivity() {
    companion object {
        const val TITLE = "TITLE"
        const val PRICE = "PRICE"
        const val AUTHOR = "AUTHOR"
        const val RATEPOINT = "RATEPOINT"
        const val THUMBNAIL_URL = "THUMBNAIL_URL"
        const val DESCRIPTION = "DESCRIPTION"

    }

    lateinit var binding: ActivityItemDetailBinding

    // TODO: 11/06/2021 thay bttoin add to cart to material button</todo> 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent: Intent = intent;
        var bundle: Bundle? = intent.getBundleExtra("Bundle");

        //on click
        binding.idBuy.setOnClickListener { startIntent("buy") }
        binding.idAddCart.setOnClickListener { startIntent("cart") }
        binding.idCart.setOnClickListener { startIntent("cart") }
        binding.idBack.setOnClickListener{onBackPressed()}

        //setup view
        binding.tvBookCover.text = bundle?.getString(TITLE)
        binding.tvPrice.text = bundle?.getString(PRICE)
        binding.tvAuthor.text = bundle?.getString(AUTHOR)
        binding.tvRate.text = bundle?.getString(RATEPOINT)
        binding.idDescription.text = bundle?.getString(DESCRIPTION)
        val thumbnailUri = bundle?.getString(THUMBNAIL_URL)?.toUri()

        Glide
            .with(baseContext)
            .load(thumbnailUri)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.idTnBackground)
        Glide
            .with(baseContext)
            .load(thumbnailUri)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.idThumbnail)


    }

    private fun startIntent(type: String) {
        val intent = when (type) {
            "cart" -> Intent(this, CartActivity::class.java)
            else -> Intent(this, CheckoutActivity::class.java)
        }
        startActivity(intent)
    }
}