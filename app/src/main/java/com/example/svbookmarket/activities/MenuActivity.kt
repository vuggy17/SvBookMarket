package com.example.svbookmarket.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.svbookmarket.R

class MenuActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.menu)

        Toast.makeText(this,"menu created",Toast.LENGTH_SHORT).show();
    }
}