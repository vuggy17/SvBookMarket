package com.example.svbookmarket.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.example.svbookmarket.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val imgBackArrow: AppCompatImageView = findViewById(R.id.imgBackArrow)
        imgBackArrow.setOnClickListener {
            onBackPressed()
        }
    }
}