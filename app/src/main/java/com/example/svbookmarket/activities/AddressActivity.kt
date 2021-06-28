package com.example.svbookmarket.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment.STYLE_NORMAL
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.AddressAdapter
import com.example.svbookmarket.activities.common.Constants.DEFAULT_ADDRESS_POS
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
        const val FROM_CART = "FROM_CART"
    }

    lateinit var binding: ActivityAddressBinding
    val viewmodel: AddressViewModel by viewModels()
    private val addressAdapter = AddressAdapter(mutableListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val addressList = binding.adRecyclerview
        val newAddressBtn = binding.adNewAddress
        val editAddressBtn = binding.adEditAdress

        // if this activity has not been opened from cart activity, hide continue button
        if (intent.extras?.getBoolean(FROM_CART) != null)
            binding.adContinue.visibility = View.VISIBLE

        //on back
        binding.adBack.setOnClickListener { onBackPressed() }

        binding.adContinue.setOnClickListener { onBackPressed() }

        // show add address form listener
        newAddressBtn.setOnClickListener {
            val newAddressDialog = CreateAddressDialog(this)
            newAddressDialog.setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
            newAddressDialog.show(supportFragmentManager, "tag")
        }


        // show edit form listener
        editAddressBtn.setOnClickListener {
            // pass selected item to form
            val editAddressDialog =
                viewmodel.selectedItem?.let { selectedItem ->
                    EditAddressDialog(
                        this,
                        selectedItem,
                        this
                    )
                }
            editAddressDialog?.setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
            editAddressDialog?.show(supportFragmentManager, "tag")
        }


        addressList.adapter = addressAdapter
        watchForDataChange()
    }

    /**
     * watch for changes from database, then update ui
     */
    private fun watchForDataChange() {
        viewmodel.address.observe(this, { address ->

            val btn = binding.adEditAdress
            if (address.size > 0) {
                // enable edit button
                if (!btn.isEnabled) {
                    btn.setTextColor(resources.getColor(R.color.blue_dark))
                    btn.isEnabled = true
                }


                // if there is no selected item, set selected item to default position(0)
                if (viewmodel.selectedItem == null)
                    viewmodel.setSelectStateToTrue(address[DEFAULT_ADDRESS_POS])


                // add freshen items to address then notify adapter about changes
                // then show recyclerview
                addItemToRecyclerView(address)
                showRecyclerView()
            } else {
                // if no address, show empty notification
                showEmptyNotification()
                // disable edit button
                btn.setTextColor(resources.getColor(R.color.disable))
                btn.isEnabled = false
            }
        })
    }

    private fun addItemToRecyclerView(address: MutableList<UserDeliverAddress>) {
        addressAdapter.addAddress(address)
    }

    private fun showEmptyNotification() {
        binding.adRecyclerview.visibility = View.GONE
        binding.adEmptyImage.visibility = View.VISIBLE
        binding.adEmptyText.visibility = View.VISIBLE
    }

    private fun showRecyclerView() {
        binding.adRecyclerview.visibility = View.VISIBLE
        binding.adEmptyImage.visibility = View.GONE
        binding.adEmptyText.visibility = View.GONE
    }


    override fun onCreateAddress(address: UserDeliverAddress) {
        viewmodel.addAddress(address)
    }

    override fun onAddressChange(old: UserDeliverAddress, new: UserDeliverAddress) {
        viewmodel.chageSelectState(old, new)
        viewmodel.selectedItem = new
    }

    override fun onEditAddress(address: UserDeliverAddress) {
        viewmodel.updateCurrentAddress(address)
    }

    override fun onDeleteAddress(address: UserDeliverAddress) {
        viewmodel.selectedItem = null
        viewmodel.deleteAddress(address)
        viewmodel.getAddress()

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


