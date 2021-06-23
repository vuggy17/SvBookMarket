package com.example.svbookmarket.activities

import android.os.Bundle
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
class AddressActivity : AppCompatActivity(), OnCreateAddressListener,OnEditAddressListener,
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

        //on click
        binding.adBack.setOnClickListener { onBackPressed() }

        // TODO: 11/06/2021  update address then navigate back
        binding.adContinue.setOnClickListener { onBackPressed() }

        newAddressBtn.setOnClickListener {
            val newAddressDialog = CreateAddressDialog(this)
            newAddressDialog.setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
            newAddressDialog.show(supportFragmentManager, "tag")
        }

        editAddressBtn.setOnClickListener {
            val editAddressDialog = EditAddressDialog()
            editAddressDialog.setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
            editAddressDialog.show(supportFragmentManager, "tag")
        }

        addressList.adapter = addressAdapter

        watchChanges()

//        onListenToDb()
    }

    private fun watchChanges() {
        viewmodel.getAddress().observe(this, { address ->
            val btn = binding.adEditAdress

            if (address.size > 0 && !btn.isEnabled) {
                btn.setTextColor(resources.getColor(R.color.blue_dark))
                btn.isEnabled = true
            }
            addressAdapter.addAddress(address)

        })
    }

    override fun onCreateAddress(address: UserDeliverAddress) {
        viewmodel.addAddress(address)
    }


//    fun onListenToDb()
//    {
//        val ref = FirebaseFirestore.getInstance().collection("accounts").document(CurrentUserInfo.getInstance().currentProfile.email).collection("userDeliverAddresses")
//        ref.addSnapshotListener { snapshot, e ->
//            e?.let {
//                Log.d("00000000000000000", e.message!!)
//                return@addSnapshotListener
//            }
//
//            for (dc in snapshot!!.documentChanges)
//            {
//                when(dc.type)
//                {
//                    DocumentChange.Type.ADDED -> { addressAdapter.onChange()
//                        addressAdapter.notifyDataSetChanged()}
//                    DocumentChange.Type.MODIFIED -> {addressAdapter.onChange()
//                        addressAdapter.notifyDataSetChanged()}
//                    DocumentChange.Type.REMOVED -> { addressAdapter.onChange()
//                        addressAdapter.notifyDataSetChanged()}
//                }
//            }
//        }
//    }


    override fun onAddressChange(old: UserDeliverAddress, new: UserDeliverAddress) {
        viewmodel.updateCurrentAddress(old, new)
    }

    override fun onEditAddress(address: UserDeliverAddress) {
        TODO("Not yet implemented")
    }
}

interface OnCreateAddressListener {
    fun onCreateAddress(address: UserDeliverAddress)
}

interface OnEditAddressListener {
    fun onEditAddress(address: UserDeliverAddress)
}


