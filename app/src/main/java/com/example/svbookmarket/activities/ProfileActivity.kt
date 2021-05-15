package com.example.svbookmarket.activities

import android.accounts.Account
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.AppAccount
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class ProfileActivity() : AppCompatActivity() {
    lateinit var edtName: EditText
    lateinit var edtBirthday: EditText
    lateinit var edtEmail: EditText
    lateinit var edtPhoneNumber: EditText
    lateinit var edtAddressLine: EditText
    lateinit var edtCity: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        edtName = findViewById(R.id.edtName)
        edtAddressLine = findViewById(R.id.edtAddressLane)
        edtBirthday = findViewById(R.id.edtBirthday)
        edtEmail = findViewById(R.id.edtEmail)
        edtCity = findViewById(R.id.edtCity)
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber)

        val db = Firebase.firestore
        val dbAccountReferences = db.collection("accounts");
        dbAccountReferences.document("Email").get().addOnSuccessListener { result ->


        }
    }
}