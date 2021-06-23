package com.example.svbookmarket.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.svbookmarket.R

class CancelConfirmation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancel_confirmation)

        @Suppress("DEPRECATION")
        Handler().postDelayed(
            {
                startActivity(Intent(baseContext,HomeActivity::class.java))
                finish()
            },
            3000 // set time
        )
    }
}