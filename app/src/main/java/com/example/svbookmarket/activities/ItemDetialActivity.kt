package com.example.svbookmarket.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.svbookmarket.R

class ItemDetialActivity : AppCompatActivity() {
    companion object{
        const val TITLE = "TITLE"
        const val PRICE = "PRICE"
        const val AUTHOR = "AUTHOR"
        const val RATEPOINT = "RATEPOINT"
    }

    lateinit var tvBookCover: TextView
    lateinit var tvAuthor: TextView
    lateinit var tvPrice: TextView
    lateinit var tvRate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detial)

        findViewById<Button>(R.id.id_buy).setOnClickListener{startIntent("buy")}
        findViewById<Button>(R.id.id_cart).setOnClickListener{startIntent("cart")}

        var intent: Intent = intent;
        var bundle: Bundle? = intent.getBundleExtra("Bundle");

        tvBookCover = findViewById(R.id.tvBookCover)
        tvAuthor = findViewById(R.id.tvAuthor)
        tvPrice = findViewById(R.id.tvPrice)
        tvRate = findViewById(R.id.tvRate)


        tvBookCover.text = bundle?.getString(TITLE)
        tvPrice.text = bundle?.getString(PRICE)
        tvAuthor.text = bundle?.getString(AUTHOR)
        tvRate.text = bundle?.getString(RATEPOINT)
    }
    private fun startIntent(type:String){
        val intent = when(type){
            "cart"->Intent(this,CartActivity::class.java)
            else-> Intent(this, CheckoutActivity::class.java)
        }
        startActivity(intent)
    }
}