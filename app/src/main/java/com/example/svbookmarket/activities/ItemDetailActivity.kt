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
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.common.Constants.ITEM
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.viewmodel.ItemDetailViewModel
import com.example.svbookmarket.databinding.ActivityItemDetailBinding
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
            if (value.Name != "null")
            {
            binding.idTitle.text = it.Name
            binding.idPrice.text = it.Price.toString()
            binding.idAuthor.text = it.Author
            binding.idRate.text = it.rate.toString()
            binding.idDescription.text = it.Description
            it.Image?.let { uri -> loadImageFromUri(Uri.parse(uri)) } }
        else
            {
                startActivity(Intent(this, HomeActivity::class.java))
            }}
    }


    private fun startIntent(type: String) {
        val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
    }

    private fun addItemToCart() {
        viewModel.addToCart()
    }

    fun setupOnlickListener(){
        binding.idBuy.setOnClickListener { startIntent("buy") }
        binding.idAddCart.setOnClickListener { addItemToCart() }
        binding.idBack.setOnClickListener { onBackPressed() }
    }

    //private fun displayData(){
    //    itemToDisplay?.let {
    //        Log.i(ITEM, it.Name.toString())
    //        binding.idTitle.text = it.Name
    //        binding.idPrice.text = it.Price.toString()
    //        binding.idAuthor.text = it.Author
    //        binding.idRate.text = it.rate.toString()
    //        binding.idDescription.text = it.Description
    //        it.Image?.let { uri -> loadImageFromUri(Uri.parse(uri)) }
    //    }
    //}

    //private fun getItemToDisplayFromBundle() {
    //    var bundle: Bundle? = intent.extras
    //    itemToDisplay = bundle?.getParcelable(ITEM)!!
    //}

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

    fun onDeleteListen()
    {
        FirebaseFirestore.getInstance().collection("books").addSnapshotListener { snapshots, e ->
            e?.let {
                Log.d("0000000", e.message!!)
            }

            for (dc in snapshots!!.documentChanges) {
                if(dc.document.id == viewModel.itemToDisplay.value?.id)
                when (dc.type) {
                    DocumentChange.Type.REMOVED -> {
                        if(currentFocus == ItemDetailActivity::class.java) {
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