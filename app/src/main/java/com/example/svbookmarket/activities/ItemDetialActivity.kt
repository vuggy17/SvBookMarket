package com.example.svbookmarket.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.svbookmarket.R

class ItemDetialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detial)

        findViewById<Button>(R.id.id_buy).setOnClickListener{startIntent("buy")}
        findViewById<Button>(R.id.id_cart).setOnClickListener{startIntent("cart")}
    }
    private fun startIntent(type:String){
        val intent = when(type){
            "cart"->Intent(this,CartActivity::class.java)
            else-> Intent(this, CheckoutActivity::class.java)
        }
        startActivity(intent)
    }
}