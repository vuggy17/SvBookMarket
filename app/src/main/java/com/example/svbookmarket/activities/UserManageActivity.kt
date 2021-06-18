package com.example.svbookmarket.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.svbookmarket.R
import com.example.svbookmarket.databinding.ActivityUserManageBinding

class UserManageActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityUserManageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserManageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButtonBack()
    }

    private fun setButtonBack(){
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }
}