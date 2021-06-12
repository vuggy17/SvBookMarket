package com.example.svbookmarket.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.viewmodel.ItemDetailViewModel
import com.example.svbookmarket.activities.viewmodel.ItemDetailViewModelFactory
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
    lateinit var viewModel: ItemDetailViewModel
    lateinit var viewModelFactory: ItemDetailViewModelFactory

    // TODO: 11/06/2021 thay bttoin add to cart to material button</todo> 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent: Intent = intent;
        var bundle: Bundle? = intent.getBundleExtra("Bundle");

        //setup view model
        viewModelFactory = ItemDetailViewModelFactory(bundle!!)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ItemDetailViewModel::class.java)

        //on click
        binding.idBuy.setOnClickListener { startIntent("buy") }
        binding.idAddCart.setOnClickListener { startIntent("cart") }
        binding.idCart.setOnClickListener { startIntent("cart") }
        binding.idBack.setOnClickListener { onBackPressed() }

        //setup view
        viewModel.itemToDisplay.observe(this,{
            binding.idTitle.text = it.title
            binding.idPrice.text = it.price.toString()
            binding.idAuthor.text = it.author
            binding.idRate.text = it.rating.toString()
            binding.idDescription.text = it.description
            loadImageFromUri(it.imageURL)
        })


    }

    private fun startIntent(type: String) {
        val intent = when (type) {
            "cart" -> Intent(this, CartActivity::class.java)
            else -> Intent(this, CheckoutActivity::class.java)
        }
        startActivity(intent)
    }

    private fun loadImageFromUri(uri:Uri){
        Glide
            .with(baseContext)
            .load(uri)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.idTnBackground)
        Glide
            .with(baseContext)
            .load(uri)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.idThumbnail)
    }
}