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
) : ViewModel() {

    private val currentUser = CurrentUserInfo.getInstance().currentProfile

    private val _address = MutableLiveData<MutableList<UserDeliverAddress>>()
    val address: LiveData<MutableList<UserDeliverAddress>> get() = _address

    /**
     * save address selected by user
     */
    var selectedItem: UserDeliverAddress? = null

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

                //set selected item
                if (addressItem.chose) {
                    selectedItem = addressItem
                }
            }
            _address.value = addressList

            if (selectedItem == null && addressList.size > 0)
                selectedItem = _address.value?.get(0)
        }
        return _address
    }

    fun chageSelectState(oldAddress: UserDeliverAddress, newAddress: UserDeliverAddress) {
        if (newAddress != oldAddress)
            viewModelScope.launch {
                Log.i("custom1", "$oldAddress")
                Log.i("custom1", "$newAddress")

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

    fun updateCurrentAddress(address: UserDeliverAddress) {
        viewModelScope.launch {
            addressRepository.updateAddress(address)
        }
    }

    fun deleteAddress(address: UserDeliverAddress) {
        viewModelScope.launch {
            addressRepository.deleteAddress(address, currentUser)
        }
    }

    suspend fun deleteAddress1(address: UserDeliverAddress) {
        val job = viewModelScope.launch {
            addressRepository.deleteAddress(address, currentUser)
            getAddress()
        }
        job.join()
//        _address.value?.get(0)?.let { setSelectStateToTrue(it) }
    }

    fun setSelectStateToTrue(address: UserDeliverAddress) {
        viewModelScope.launch {
            addressRepository.setSelectState(address, true)
        }
    }

init {
    getAddress()
}

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