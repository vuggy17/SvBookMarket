package com.example.svbookmarket.activities.viewmodel

import CurrentUserInfo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.svbookmarket.activities.common.Constants.VMTAG
import com.example.svbookmarket.activities.data.AddressRepository
import com.example.svbookmarket.activities.data.CartRepository
import com.example.svbookmarket.activities.model.UserDeliverAddress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val addressRepository: AddressRepository,
    private val cartRepository: CartRepository
) : ViewModel() {


    private val currentUser = CurrentUserInfo.getInstance().currentProfile

    private val _address = MutableLiveData<MutableList<UserDeliverAddress>>()
    val address: LiveData<MutableList<UserDeliverAddress>> get() = _address

    private lateinit var _selectedItem: UserDeliverAddress
    var selectedItem: UserDeliverAddress
        get() = _selectedItem
        set(value) {
            _selectedItem = value
        }


    fun getAddress(): MutableLiveData<MutableList<UserDeliverAddress>> {
        addressRepository.getAddress(currentUser).addSnapshotListener { value, error ->
            if (error != null) {
                Log.e(VMTAG, "Listen failed, $error")
            }

            val addressList: MutableList<UserDeliverAddress> = ArrayList()

            for (doc in value!!) {
                val addressItem = doc.toObject(UserDeliverAddress::class.java)
                addressItem.id = doc.id
                addressList.add(addressItem)

            }
            _address.value = addressList
            Log.i("customtag", "${_address.value}")
        }
        return _address
    }

    fun updateCurrentAddress(oldAddress: UserDeliverAddress, newAddress: UserDeliverAddress) {
        if (newAddress != oldAddress)
            viewModelScope.launch {
                Log.i("custom1","$oldAddress")
                Log.i("custom1","$newAddress")

                addressRepository.setSelectState(oldAddress, false)
                addressRepository.setSelectState(newAddress, true)
            }
    }

    fun isAddressExist(address: UserDeliverAddress): Boolean {
        val index = _address.value?.indexOf(address)
        return index == -1
    }

    fun addAddress(address: UserDeliverAddress) {
        addressRepository.addAddress(address)
    }

    /***
     * generate tempdata
     */


    private val testAddress = UserDeliverAddress(
        "",
        "khuong duy",
        "012321322",
        "lams son",
        "cam thanh bac",
        "khanh hoa",
        false
    )
}