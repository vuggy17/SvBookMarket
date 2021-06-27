package com.example.svbookmarket.activities

import android.content.Intent
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.transition.Visibility
import android.util.Log
import android.view.View
import android.view.View.GONE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.AdvertiseAdapter
import com.example.svbookmarket.activities.adapter.CartItemAdapter
import com.example.svbookmarket.activities.model.Book
import com.example.svbookmarket.activities.model.Cart
import com.example.svbookmarket.activities.viewmodel.CartViewModel
import com.example.svbookmarket.databinding.ActivityCartBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

@AndroidEntryPoint
class CartActivity : AppCompatActivity(), CartItemAdapter.OnButtonClickListener {
    lateinit var binding: ActivityCartBinding
    private val viewModel: CartViewModel by viewModels()
    var cartAdapter: CartItemAdapter = CartItemAdapter(this, mutableListOf())
    lateinit var dataCart : MutableList<Cart>
    var deleteItem: View? = null

    //variable to save deleted item, later is used for redo action
    lateinit var delete: Cart


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        // set activity to display here
        setContentView(binding.root)

        viewModel.cartItem.observe(this, changeObserver)

        binding.rcCardList.apply {
            layoutManager =
                LinearLayoutManager(this@CartActivity)
            setHasFixedSize(true)
            adapter = cartAdapter
            touchHelper.attachToRecyclerView(this)
        }
        binding.backButton.setOnClickListener { onBackPressed() }
        binding.ctCheckout.setOnClickListener { startIntent("checkout") }
//        binding.ctChangeLocation.setOnClickListener { startIntent(AddressActivity.CHANGE_ADDRESS) }
    }

    private val changeObserver = Observer<MutableList<Cart>> { value ->
        value?.let {
            dataCart = value
            cartAdapter.onChange(value)
            cartAdapter.notifyDataSetChanged()

            //show empty text if there is no item in list
            // otherwise show recyclerview
            showItem(value.size)
            Log.d("0000000000000", value.toString())
        }
    }

    /**
     * change item's visual effect based on item in cartList's size
     */
    private fun showItem(listSize:Int) {
        if (listSize > 0) {
            setButtonColor(R.color.green)
            binding.ctCheckout.isClickable = true
            binding.ctEmptyText.visibility = GONE
            binding.rcCardList.visibility = View.VISIBLE
        } else {
            setButtonColor(R.color.disable)
            binding.ctCheckout.isClickable = false
            binding.rcCardList.visibility = GONE
            binding.ctEmptyText.visibility = View.VISIBLE
        }
    }


    private fun setButtonColor(color: Int) {
        binding.ctCheckout.backgroundTintList = getColorStateList(color)
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
                delete = dataCart[itemPos]
                deleteItem = viewHolder.itemView
                // TODO: add delete on db
                viewModel.deleteCart(dataCart[itemPos].id)
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
        })

    override fun onButtonClick(id: String, plusOrMinus: Boolean) {
            viewModel.updateQuantity(id, plusOrMinus)
    }

    override fun onItemClick(id: String, isChose: Boolean) {
        viewModel.isChoseChange(id, isChose)
    }

}




