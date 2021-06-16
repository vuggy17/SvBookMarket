package com.example.svbookmarket.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.common.Constants.ITEM
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.databinding.ActivityItemDetailBinding

class ItemDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityItemDetailBinding
    private lateinit var itemToDisplay: Book

    // TODO: 11/06/2021 thay bttoin add to cart to material button</todo> 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getItemToDisplayFromBundle()
        setupOnlickListener()
        displayData()
    }

    private fun startIntent(type: String) {
        val intent = Intent(this, CheckoutActivity::class.java)
        startActivity(intent)
    }

    private fun addItemToCart() {
        // TODO: 16/06/2021 need implement
        Toast.makeText(applicationContext, "Item added to cart", Toast.LENGTH_SHORT).show()
    }

    private fun setupOnlickListener(){
        binding.idBuy.setOnClickListener { startIntent("buy") }
        binding.idAddCart.setOnClickListener { addItemToCart() }
        binding.idBack.setOnClickListener { onBackPressed() }

    }

    private fun displayData(){
        itemToDisplay?.let {
            Log.i(ITEM, it.Name.toString())
            binding.idTitle.text = it.Name
            binding.idPrice.text = it.Price.toString()
            binding.idAuthor.text = it.Author
            binding.idRate.text = it.rate.toString()
            binding.idDescription.text = it.Description
            it.Image?.let { uri -> loadImageFromUri(Uri.parse(uri)) }
        }
    }

    private fun getItemToDisplayFromBundle() {
        var bundle: Bundle? = intent.extras
        itemToDisplay = bundle?.getParcelable(ITEM)!!
    }

    private fun loadImageFromUri(uri: Uri) {
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