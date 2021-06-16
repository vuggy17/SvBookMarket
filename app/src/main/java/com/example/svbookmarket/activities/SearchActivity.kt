package com.example.svbookmarket.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.RecentSearchAdapter
import com.example.svbookmarket.activities.common.Constants.ITEM
import com.example.svbookmarket.activities.data.FullBookList

class SearchActivity : AppCompatActivity() {
    lateinit var suggestSearch: ListView
    lateinit var recentSearch: RecyclerView
    lateinit var searchBar: SearchView
    lateinit var linearLayoutRecent: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        suggestSearch = findViewById(R.id.rc_suggest_search)
        recentSearch = findViewById(R.id.rc_recent_search)
        searchBar = findViewById(R.id.tb_searchView)
        linearLayoutRecent = findViewById(R.id.ln_recent)

        // repair fix dataset for suggest
        var dataset : MutableList<String> = mutableListOf()
        for (i in 0..FullBookList.getInstance().lstFullBook.size-1)
        {
//            dataset.add(FullBookList.getInstance().lstFullBook[i].title)
        }

        //suggest adapter
        val adapter : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataset)
        suggestSearch.adapter = adapter
        fillInRecentSearch(recentSearch)

        //Click suggest search
        suggestSearch.setOnItemClickListener(
            AdapterView.OnItemClickListener { parent, view, position, id ->
                for (book in FullBookList.getInstance().lstFullBook)
                {
                    if (book.Name == suggestSearch.getItemAtPosition(position))
                    {
                        val intent = Intent(parent.context, ItemDetailActivity::class.java)
                        var bundle = Bundle()
                        bundle.putParcelable(ITEM,book)
                        intent.putExtra("Bundle",bundle)
                        ContextCompat.startActivity(parent.context, intent, bundle);
                    }
                }

            }

        )


        //searching
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchBar.clearFocus()

                if (query == null)
                {
                    suggestSearch.visibility = View.INVISIBLE
                    linearLayoutRecent.visibility = View.VISIBLE
                    return false
                }
                if(dataset.contains(query))
                {
                    adapter.filter.filter(query)
                    suggestSearch.visibility = View.VISIBLE
                    linearLayoutRecent.visibility = View.INVISIBLE
                }
                else
                {
                    Toast.makeText(applicationContext,"Item not found !", Toast.LENGTH_LONG).show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != "") {
                    adapter.filter.filter(newText)
                    suggestSearch.visibility = View.VISIBLE
                    linearLayoutRecent.visibility = View.INVISIBLE
                }
                else
                {
                    suggestSearch.visibility = View.INVISIBLE
                    linearLayoutRecent.visibility = View.VISIBLE
                }
                return false
            }

        })
    }

    fun fillInRecentSearch(suggestSearch: RecyclerView)
    {
        val dataset = FullBookList.getInstance().lstFullBook
        val snaphelperFeature: LinearSnapHelper = LinearSnapHelper()
        suggestSearch.adapter = RecentSearchAdapter(this, dataset)
        snaphelperFeature.attachToRecyclerView(suggestSearch)
        suggestSearch.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        suggestSearch.setHasFixedSize(true)
    }


}