package com.example.svbookmarket.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.svbookmarket.R

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)


        val gender: AutoCompleteTextView = findViewById(R.id.gender)
        val adapter = ArrayAdapter(
            this,
            R.layout.gender_item,
            resources.getStringArray(R.array.gender)
        )
        gender.setAdapter(adapter)

    }
}