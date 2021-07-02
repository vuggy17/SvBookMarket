package com.example.svbookmarket.activities.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.common.AppUtil
import com.example.svbookmarket.activities.model.CreateAddressFormState
import com.example.svbookmarket.activities.model.UserDeliverAddress

class EditAddressViewModel:ViewModel() {

    private val _createAddressForm = MutableLiveData<CreateAddressFormState>()
    val createAddressFormState: LiveData<CreateAddressFormState> = _createAddressForm


    fun formDataChanged(a: UserDeliverAddress) {
        if (!isNameValid(a.fullName))
            _createAddressForm.value = CreateAddressFormState(nameError = R.string.invalid_name)
        else if (!isAddressValid(a.addressLane))
            _createAddressForm.value = CreateAddressFormState(laneError = R.string.invalid_lane)
        else if (!isNameValid(a.city))
            _createAddressForm.value = CreateAddressFormState(cityError = R.string.invalid_city)
        else if (!isAddressValid(a.district))
            _createAddressForm.value = CreateAddressFormState(districtError = R.string.invalid_district)
        else if (!isPhoneNumberValid(a.phoneNumber))
            _createAddressForm.value = CreateAddressFormState(phoneError = R.string.invalid_phone)
        else
            _createAddressForm.value = CreateAddressFormState(isDataValid = true)

        Log.i("test name",isAddressValid(a.fullName).toString())
    }

    private fun isNameValid(input: String): Boolean = input.isBlank() || AppUtil.checkName(input)

    private fun isAddressValid(address:String):Boolean = address.isBlank() || AppUtil.checkAddress(address)

    private fun isPhoneNumberValid(number:String):Boolean = number.isBlank() || AppUtil.checkPhoneNumber(number)

}