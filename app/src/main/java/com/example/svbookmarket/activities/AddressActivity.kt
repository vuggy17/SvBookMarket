package com.example.svbookmarket.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment.STYLE_NORMAL
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.AddressAdapter
import com.example.svbookmarket.activities.model.UserDeliverAddress
import com.example.svbookmarket.activities.viewmodel.AddressViewModel
import com.example.svbookmarket.databinding.ActivityAddressBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressActivity : AppCompatActivity(), OnCreateAddressListener, OnEditAddressListener,
    OnDeleteAddressListener,
    AddressAdapter.NotifyAddressSelectionChanged {
    companion object {
        const val CHANGE_ADDRESS = "CHANGE_ADDRESS"
        const val IS_FROM_CART = true
        const val FROM_CART = "FROM_CART"
    }

    lateinit var binding: ActivityAddressBinding
    val viewmodel: AddressViewModel by viewModels()
    private val addressAdapter = AddressAdapter(mutableListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        // set activity to display here
        setContentView(binding.root)


        val addressList = binding.adRecyclerview
        val newAddressBtn = binding.adNewAddress
        val editAddressBtn = binding.adEditAdress

        if (intent.extras?.getBoolean(FROM_CART) != null)
            binding.adContinue.visibility = View.VISIBLE

        //on back
        binding.adBack.setOnClickListener { onBackPressed() }

        // TODO: 11/06/2021  update address then navigate back
        binding.adContinue.setOnClickListener { onBackPressed() }

        // add form
        newAddressBtn.setOnClickListener {
            val newAddressDialog = CreateAddressDialog(this)
            newAddressDialog.setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
            newAddressDialog.show(supportFragmentManager, "tag")
        }


        // edit form
        editAddressBtn.setOnClickListener {
            Log.i("selectqq", "${viewmodel.selectedItem}")

            val editAddressDialog =
                viewmodel.selectedItem?.let { it1 -> EditAddressDialog(this, it1, this) }
            editAddressDialog?.setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
            editAddressDialog?.show(supportFragmentManager, "tag")
        }

        addressList.adapter = addressAdapter

        watchChanges()
    }

    private fun watchChanges() {
        viewmodel.getAddress().observe(this, { address ->
            val btn = binding.adEditAdress

            if (address.size > 0) {
                if (!btn.isEnabled) {
                    btn.setTextColor(resources.getColor(R.color.blue_dark))
                    btn.isEnabled = true
                }
                val chosen = address.find { it.chose == true }
                Log.i("found", "$chosen")
//                if (chosen == null) {
//                    viewmodel.setSelectStateToTrue(address[0])
//                }
            }

            addressAdapter.addAddress(address)

        })
    }

    override fun onCreateAddress(address: UserDeliverAddress) {
        viewmodel.addAddress(address)
        Log.i("createa", "create call")
    }

    override fun onAddressChange(old: UserDeliverAddress, new: UserDeliverAddress) {
        viewmodel.chageSelectState(old, new)
        viewmodel.selectedItem = new
    }

    override fun onEditAddress(address: UserDeliverAddress) {
        viewmodel.updateCurrentAddress(address)
    }

    override fun onDeleteAddress(address: UserDeliverAddress) {
        viewmodel.deleteAddress(address)
    }
}

interface OnCreateAddressListener {
    fun onCreateAddress(address: UserDeliverAddress)
}

interface OnEditAddressListener {
    fun onEditAddress(address: UserDeliverAddress)
}

interface OnDeleteAddressListener {
    fun onDeleteAddress(address: UserDeliverAddress)
}


