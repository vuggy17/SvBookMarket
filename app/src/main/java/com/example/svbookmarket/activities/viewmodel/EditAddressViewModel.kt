package com.example.svbookmarket.activities.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.CreateAddressFormState
import com.example.svbookmarket.activities.model.UserDeliverAddress

class EditAddressViewModel:ViewModel() {

    private val _createAddressForm = MutableLiveData<CreateAddressFormState>()
    val createAddressFormState: LiveData<CreateAddressFormState> = _createAddressForm


    fun formDataChanged(a: UserDeliverAddress) {
        if (!isNameValid(a.fullName))
            _createAddressForm.value = CreateAddressFormState(nameError = R.string.invalid_name)
        else if (!isInputValid(a.addressLane))
            _createAddressForm.value = CreateAddressFormState(laneError = R.string.invalid_lane)
        else if (!isInputValid(a.city))
            _createAddressForm.value = CreateAddressFormState(cityError = R.string.invalid_city)
        else if (!isInputValid(a.district))
            _createAddressForm.value = CreateAddressFormState(districtError = R.string.invalid_district)
        else if (!isInputValid(a.phoneNumber))
            _createAddressForm.value = CreateAddressFormState(phoneError = R.string.invalid_phone)
        else
            _createAddressForm.value = CreateAddressFormState(isDataValid = true)
    }


    fun isNameValid(input: String): Boolean {
//        val regex = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$".toRegex()
//
//        return if (input.isNotBlank()) {
//            input.matches(regex)
//        } else false
        return input.isNotBlank()
    }

    /**
     * validate address
     * if conntain @, /,.. -> invalid
     */
    fun isInputValid(input: String): Boolean {
        // TODO: 22/06/2021 chuyen ve khong dau bang regex,sau do check \
        return input.isNotBlank()
    }




}