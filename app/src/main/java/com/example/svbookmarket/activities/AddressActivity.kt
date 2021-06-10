package com.example.svbookmarket.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment.STYLE_NORMAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.AddressAdapter
import com.example.svbookmarket.activities.common.InsetDividerItemDecoration
import com.example.svbookmarket.activities.common.toPx
import com.example.svbookmarket.activities.data.DataSource
import com.example.svbookmarket.databinding.ActivityAddressBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
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
        val adRecyclerview = binding.adRecyclerview
        val newAddressBtn = binding.adNewAddress
        val editAddressBtn = binding.adEditAdress

        adRecyclerview.apply {
                adapter = _adapter
           layoutManager = _layoutManager
            addItemDecoration(InsetDividerItemDecoration(context,138.toPx()))
            setHasFixedSize(true)
        }

        newAddressBtn.setOnClickListener{
            val newAddressDialog = CreateAddressDialog()
            newAddressDialog.show(supportFragmentManager, "tag")
            newAddressDialog.setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
        }

        editAddressBtn.setOnClickListener{
            val editAddressDialog = EditAddressDialog()
            editAddressDialog.setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
            editAddressDialog.show(supportFragmentManager,"tag")
        }


    }
}

