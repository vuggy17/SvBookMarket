package com.example.svbookmarket.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.svbookmarket.activities.common.Constants.DEFAULT_IMG_PLACEHOLDER
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.viewmodel.ItemDetailViewModel
import com.example.svbookmarket.databinding.ActivityItemDetailBinding
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityItemDetailBinding
    private val viewModel: ItemDetailViewModel by viewModels()

    // TODO: 11/06/2021 thay bttoin add to cart to material button</todo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.itemToDisplay.observe(this, changeObserver)

        //getItemToDisplayFromBundle()
        setupOnlickListener()
        onDeleteListen()
    }

    private val changeObserver = Observer<Book> { value ->
        value?.let {
            if (value.Name != "null") {
                binding.idTitle.text = it.Name
                binding.idPrice.text = it.Price.toString()
                binding.idAuthor.text = it.Author
                binding.idRate.text = it.rate.toString()
                binding.idDescription.text = it.Description
                it.Image?.let { uri -> loadImageFromUri(Uri.parse(uri)) }
            } else {
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }
    }


    private fun goToCart() {
        val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
    }

    private fun addItemToCart() {
        viewModel.addToCart()
    }

    fun setupOnlickListener() {
        binding.idBuy.setOnClickListener {
            addItemToCart()
            goToCart()
        }
        binding.idAddCart.setOnClickListener { addItemToCart() }
        binding.idBack.setOnClickListener { onBackPressed() }
    }

    private fun loadImageFromUri(uri: Uri) {
        Glide
            .with(baseContext)
            .load(uri)
            .centerCrop()
            .placeholder(DEFAULT_IMG_PLACEHOLDER)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.idTnBackground)
        Glide
            .with(baseContext)
            .load(uri)
            .centerCrop()
            .placeholder(DEFAULT_IMG_PLACEHOLDER)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.idThumbnail)
    }

    fun onDeleteListen() {
        FirebaseFirestore.getInstance().collection("books").addSnapshotListener { snapshots, e ->
            e?.let {
                Log.d("0000000", e.message!!)
            }

            for (dc in snapshots!!.documentChanges) {
                if (dc.document.id == viewModel.itemToDisplay.value?.id)
                    when (dc.type) {
                        DocumentChange.Type.REMOVED -> {
                            if (currentFocus == ItemDetailActivity::class.java) {
                                Log.d("000000000000000", "dcmmmmmmmmmmmmmmmmm")
                                startActivity(Intent(this, HomeActivity::class.java))
                            }
                            finish()
                        }
                    }
            }
        }
    }
}