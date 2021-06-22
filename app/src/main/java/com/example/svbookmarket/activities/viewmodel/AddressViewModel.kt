package com.example.svbookmarket.activities.viewmodel

import androidx.lifecycle.*
import com.example.svbookmarket.activities.data.AddressRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.svbookmarket.activities.model.UserDeliverAddress as MyAddress

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val addressRepository: AddressRepository,
) : ViewModel() {

    private val _address = MutableLiveData<MutableList<MyAddress>>()
    val address: LiveData<MutableList<MyAddress>> get() = _address

    private lateinit var _selectedItem: MyAddress
    var selectedItem:MyAddress
        get() = _selectedItem
        set(value) {
            _selectedItem = value
        }

     fun getAddress(){
        addressRepository.getAddress(CurrentUserInfo.getInstance().currentProfile).
    }


    fun updateCurrentAddress(oldAddress: MyAddress,newAddress: MyAddress ) {
        if(newAddress != oldAddress)
        viewModelScope.launch {
            cartRepository.updateCartWithAddress(oldAddress, false)
            cartRepository.updateCartWithAddress(oldAddress, true)
        }
    }

    init {
        viewModelScope.launch {
            // call repository go get data
           _address.value =  addressRepository.getAddress()
        }
    }

    /***
     * generate tempdata
     */

    private fun generate(): MyAddress = MyAddress(
        "Khuong Duy",
        "0869256174",
        "Ktx khu A, DHQG",
        "Phuong Linh Trung",
        "Quan Thu Duc",
        "Tp.Ho Chi Minh"
    )



}