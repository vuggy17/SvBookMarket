package com.example.svbookmarket.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.RecentSearchAdapter
import com.example.svbookmarket.activities.data.DataSource
import com.example.svbookmarket.activities.model.SearchResultItem

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
        searchBar = findViewById(R.id.searchView)
        linearLayoutRecent = findViewById(R.id.ln_recent)

        // repair fix dataset for suggest
        var datasetFull: MutableList<SearchResultItem> = DataSource().loadSearchItem()
        var dataset : MutableList<String> = arrayListOf()
        for (i in 0..datasetFull.size-1)
        {
            dataset.add(datasetFull[i].result)
        }

        //suggest adapter
        val adapter : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataset)
        suggestSearch.adapter = adapter
            fillInRecentSearch(recentSearch)

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
        val dataset = DataSource().loadSearchItem()
        val snaphelperFeature: LinearSnapHelper = LinearSnapHelper()
        suggestSearch.adapter = RecentSearchAdapter(this, dataset)
        snaphelperFeature.attachToRecyclerView(suggestSearch)
        suggestSearch.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        suggestSearch.setHasFixedSize(true)
    }

}