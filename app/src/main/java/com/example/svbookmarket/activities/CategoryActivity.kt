package com.example.svbookmarket.activities

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.ImageButton
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.example.svbookmarket.R



class CategoryActivity:AppCompatActivity() {
//    private val appbar: AppBarLayout by bindView(R.id.appbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        findViewById<SearchView>(R.id.c_searchView).setOnClickListener{
         startIntent("search")
        }

        findViewById<ImageButton>(R.id.c_backButton).setOnClickListener{
            startIntent("back")
        }
//        (appbar.layoutParams as CoordinatorLayout.LayoutParams).behavior = ToolbarBehavior()

    }
    private fun startIntent(type:String){
        val intent = when(type){
            "search"-> Intent(this, SearchActivity::class.java)
//            "order"-> Intent(this, ::class.java)
            else-> Intent(this, HomeActivity::class.java)
        }
        startActivity(intent)
    }
}