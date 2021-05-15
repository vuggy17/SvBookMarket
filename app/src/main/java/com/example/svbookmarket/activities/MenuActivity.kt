package com.example.svbookmarket.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.svbookmarket.R
import com.example.svbookmarket.databinding.MenuBinding

class MenuActivity:AppCompatActivity() {
//    lateinit var binding: MenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = MenuBinding.inflate(layoutInflater)
        setContentView(R.layout.menu)


        findViewById<TextView>(R.id.m_home).setOnClickListener{startIntent("home")}
        findViewById<TextView>(R.id.m_profile).setOnClickListener{startIntent("profile")}
        findViewById<TextView>(R.id.m_cart).setOnClickListener{startIntent("cart")}
        findViewById<TextView>(R.id.m_item).setOnClickListener{startIntent("item")}
//        binding.mOrder.setOnClickListener{startIntent("order")}

    }

    private fun startIntent(type:String){
        val intent = when(type){
            "home"-> Intent(this, HomeActivity::class.java)
            "profile"-> Intent(this, ProfileActivity::class.java)
            "cart"-> Intent(this, CartActivity::class.java)
//            "order"-> Intent(this, ::class.java)
            else-> Intent(this, ItemDetialActivity::class.java)
        }
        startActivity(intent)
    }
}