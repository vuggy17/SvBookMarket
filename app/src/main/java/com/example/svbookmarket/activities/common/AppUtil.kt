package com.example.svbookmarket.activities.common

import com.example.svbookmarket.activities.model.AppAccount
import com.example.svbookmarket.activities.model.User

object AppUtil {
    var currentUser: User = User()
    var currentAccount: AppAccount = AppAccount("","", currentUser)
}