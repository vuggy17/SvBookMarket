package com.example.svbookmarket.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.example.svbookmarket.R

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        var login: Button = findViewById(R.id.btnLogin)
        login.setOnClickListener {
            startActivity(Intent(baseContext,LoginActivity::class.java))
        }
        var guestLogin = findViewById<TextView>(R.id.tvContinueAsGuest)
        guestLogin.setOnClickListener { startActivity(Intent(this, HomeActivity::class.java)) }
    }
}