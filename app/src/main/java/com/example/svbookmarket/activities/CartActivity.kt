package com.example.svbookmarket.activities

import android.content.Intent
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.CartItemAdapter
import com.example.svbookmarket.activities.data.CartViewModel
import com.example.svbookmarket.activities.model.Cart
import com.example.svbookmarket.databinding.ActivityCartBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    lateinit var cartData: MutableList<Cart>
    lateinit var viewModel: CartViewModel
    lateinit var cartAdapter: CartItemAdapter

    var deleteItem: View? = null

    //variable to save deleted item, later is used for redo action
    lateinit var delete: Cart


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        // set activity to display here
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        viewModel.cart.observe(this, { datas ->
            if (datas != null) {
                cartAdapter = CartItemAdapter(this, datas)
                cartData = datas

                binding.rcCardList.apply {
                    adapter = cartAdapter
                    layoutManager = LinearLayoutManager(this@CartActivity)
                    setHasFixedSize(true)

                    // swipe to delete
                    touchHelper.attachToRecyclerView(this)
                }
            }
        })


        binding.backButton.setOnClickListener { onBackPressed() }
        binding.ctCheckout.setOnClickListener { startIntent("checkout") }
        binding.ctChangeLocation.setOnClickListener { startIntent(AddressActivity.CHANGE_ADDRESS) }
    }


    private fun startIntent(type: String) {
        val intent = when (type) {
            "checkout" -> Intent(this, CheckoutActivity::class.java)
            AddressActivity.CHANGE_ADDRESS -> Intent(this, AddressActivity::class.java).putExtra(
                AddressActivity.FROM_CART,
                true
            )
            else -> Intent(this, HomeActivity::class.java)
        }
        startActivity(intent)
    }

    private var touchHelper = ItemTouchHelper(
        object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: ViewHolder, target: ViewHolder
            ): Boolean {
                val fromPos = viewHolder.adapterPosition
                val toPos = target.adapterPosition
                // move item in `fromPos` to `toPos` in adapter.
                return false // true if moved, false otherwise
            }

            override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                // remove from adapter
                val itemPos = viewHolder.adapterPosition
                delete = cartData[itemPos]
                deleteItem = viewHolder.itemView

                cartAdapter.removeItem(itemPos)

                showSnackBar(itemPos)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )

                RecyclerViewSwipeDecorator.Builder(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addBackgroundColor(
                        ContextCompat.getColor(
                            this@CartActivity,
                            R.color.danger_light
                        )
                    )
                    .addActionIcon(R.drawable.ic_trashbin).setIconHorizontalMargin(50)
                    .create()
                    .decorate()
            }

            fun showSnackBar(itemPos: Int) =
                Snackbar.make(binding.rcCardList, "Item deleted", 2000).setAction("Undo") {
                    undoDeletion(itemPos)
                }.show()

            fun undoDeletion(position: Int) {
                cartAdapter.addItem(position, delete)
            }
        })

    override fun onPause() {
        super.onPause()
        //save data to repository

        viewModel.updateData(cartData)

    }
}




