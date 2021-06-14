package com.example.svbookmarket.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment.STYLE_NORMAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.AddressAdapter
import com.example.svbookmarket.activities.common.InsetDividerItemDecoration
import com.example.svbookmarket.activities.common.RecyclerViewClickListener
import com.example.svbookmarket.activities.common.toPx
import com.example.svbookmarket.activities.data.DataSource
import com.example.svbookmarket.activities.viewmodel.AddressViewModel
import com.example.svbookmarket.databinding.ActivityAddressBinding

class AddressActivity : AppCompatActivity() {
    companion object {
        const val CHANGE_ADDRESS = "CHANGE_ADDRESS"
        const val IS_FROM_CART = true
        const val FROM_CART = "FROM_CART"
    }

    lateinit var binding: ActivityAddressBinding
    val viewmodel: AddressViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        // set activity to display here
        setContentView(binding.root)


        val adRecyclerview = binding.adRecyclerview
        val newAddressBtn = binding.adNewAddress
        val editAddressBtn = binding.adEditAdress

        viewmodel.address.observe(this, {data->
            adRecyclerview.apply {
                adapter = AddressAdapter(context, data)
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(InsetDividerItemDecoration(context, 138.toPx()))
                setHasFixedSize(true)
            }
        })


        // ẩn continue btn nêu activity trước đó không phải là cart
        // nểu lấy được giá trị trừ intent thì thiển thị lên

        if (intent.extras?.getBoolean(FROM_CART) != null)
            binding.adContinue.visibility = View.VISIBLE


        //on click
        binding.adBack.setOnClickListener { onBackPressed() }

        // TODO: 11/06/2021  update address then navigate back
        binding.adContinue.setOnClickListener { onBackPressed() }

        newAddressBtn.setOnClickListener {
            val newAddressDialog = CreateAddressDialog()
            newAddressDialog.setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
            newAddressDialog.show(supportFragmentManager, "tag")
            newAddressDialog.setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
        }

        editAddressBtn.setOnClickListener {
            val editAddressDialog = EditAddressDialog()
            editAddressDialog.setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
            editAddressDialog.show(supportFragmentManager, "tag")
        }


    }

    override fun onResume() {
        viewmodel.let {
//            it.updateCurrentAddress()
        }

        super.onResume()
    }

    fun getdata() = ""
}

