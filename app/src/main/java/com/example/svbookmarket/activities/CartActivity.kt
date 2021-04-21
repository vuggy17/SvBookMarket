package com.example.svbookmarket.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.activities.adapter.CartItemBinder
import com.example.svbookmarket.activities.data.DataSource
import com.example.svbookmarket.activities.model.CartModel
import com.example.svbookmarket.databinding.ActivityCartBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import mva2.adapter.util.Mode


class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        // set activity to display here
        setContentView(binding.root)

//        val multiViewAdapter = MultiViewAdapter()
//        binding.recyclerview.adapter = multiViewAdapter
//
//
//        multiViewAdapter.registerItemBinders(CartItemBinder())
//
//        val listSection = ListSection<CartModel>()
//        val dt = DataSource().loadCart()
//        listSection.addAll(
//            dt
//        )
//        multiViewAdapter.addSection(listSection)
//
//        binding.recyclerview.setHasFixedSize(true)

        var adapter = MultiViewAdapter()
        adapter.setSelectionMode(Mode.MULTIPLE)
        adapter.getItemTouchHelper().attachToRecyclerView(binding.rc1);

        binding.rc1.adapter = adapter

val layoutManager:RecyclerView.LayoutManager = LinearLayoutManager(this)

//        val layoutManager = FlexboxLayoutManager(this)
//        layoutManager.flexDirection = FlexDirection.COLUMN
//        layoutManager.flexWrap=FlexWrap.NOWRAP
//        layoutManager.justifyContent = JustifyContent.CENTER

        binding.rc1.layoutManager = layoutManager
        adapter.registerItemBinders(CartItemBinder())

        var listSection = ListSection<CartModel>()
        listSection.addAll(DataSource().loadCart())
        adapter.addSection(listSection)
//        MultiViewAdapter adapter = new MultiViewAdapter();
//        recyclerView.setAdapter(adapter);
//
//// Register Binder
//        adapter.registerBinders(new CarItemBinder());
//
//// Create Section and add items
//        ListSection<YourModel> listSection = new ListSection<>();
//        listSection.addAll(cars);
//
//// Add Section to the adapter
//        adapter.addSection(listSection);
            binding.rc1.setHasFixedSize(true)

    }

}
// Create Adapter


