package com.example.svbookmarket.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.svbookmarket.databinding.ActivityUserManageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserManageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserManageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserManageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setButtonBack()
        setLogout()
    }

    private fun setButtonBack() {
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private lateinit var auth: FirebaseAuth
    private fun setLogout() {
        binding.logout.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(baseContext, WelcomeActivity::class.java))
        }

    }
}