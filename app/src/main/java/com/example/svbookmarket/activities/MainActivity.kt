package com.example.svbookmarket.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.svbookmarket.R
import kotlinx.android.synthetic.main.card_checkout.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set activity to display here
        setContentView(R.layout.card_checkout)

//        //tam thoi de funtion nay o day
//        card.setOnClickListener{
//            card.setChecked(!card.isChecked)
//            true
//        }
    }
}
