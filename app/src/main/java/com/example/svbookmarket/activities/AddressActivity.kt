package com.example.svbookmarket.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.AddressAdapter
import com.example.svbookmarket.activities.common.InsetDividerItemDecoration
import com.example.svbookmarket.activities.common.toPx
import com.example.svbookmarket.activities.data.DataSource
import com.example.svbookmarket.databinding.ActivityAddressBinding
import com.google.android.material.card.MaterialCardView


class AddressActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        // set activity to display here
        setContentView(binding.root)


        val myDataset = DataSource().loadAddressCard()
        val _layoutManager = LinearLayoutManager(this)
        val _adapter = AddressAdapter(this,myDataset)

        binding.recyclerview.apply {
                adapter = _adapter
           layoutManager = _layoutManager
            addItemDecoration(InsetDividerItemDecoration(context,138.toPx()))
            setHasFixedSize(true)
        }

    }
}

