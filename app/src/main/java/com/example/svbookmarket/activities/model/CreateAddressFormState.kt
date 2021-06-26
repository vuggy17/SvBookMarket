package com.example.svbookmarket.activities.model

data class CreateAddressFormState(
    val nameError: Int? =null,
    val phoneError:  Int? =null,
    val laneError:  Int? =null,
    val districtError:  Int? =null,
    val cityError:  Int? =null,
    val isDataValid:Boolean = false,

)