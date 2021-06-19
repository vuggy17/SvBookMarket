package com.example.svbookmarket.activities.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.svbookmarket.activities.ProfileActivity
import com.example.svbookmarket.activities.data.AccountRepository
import com.example.svbookmarket.activities.data.UserRepository
import com.example.svbookmarket.activities.model.AppAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.reflect.KProperty

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val accountRepository: AccountRepository
) : ViewModel() {

    fun getUserInfo(): com.example.svbookmarket.activities.model.User
    {
        userRepository.loadData()
        return userRepository.user
    }
    fun getAccountInfo(): AppAccount{
        accountRepository.loadData()
        return  accountRepository.account
    }


}