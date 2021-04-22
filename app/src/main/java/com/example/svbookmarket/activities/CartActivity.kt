package com.example.svbookmarket.activities

import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.adapter.CartItemAdapter
import com.example.svbookmarket.activities.data.DataSource
import com.example.svbookmarket.activities.model.CartModel
import com.example.svbookmarket.databinding.ActivityCartBinding
import com.google.android.material.snackbar.Snackbar
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding

    val cartData = DataSource().loadCart()
    val cartAdapter = CartItemAdapter(this, cartData)

    var deleteItem: View? = null
    var deleteModel: CartModel = CartModel("cc", "cc", "cc", "cc", "Cc")

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
                deleteModel = cartData[itemPos]
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
                cartAdapter.addItem(position, deleteModel)
            }
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        // set activity to display here
        setContentView(binding.root)

        binding.rcCardList.adapter = cartAdapter
        binding.rcCardList.layoutManager = LinearLayoutManager(this)
        binding.rcCardList.setHasFixedSize(true)

        // swipe to delete
        touchHelper.attachToRecyclerView(binding.rcCardList)

        //select all
        val checkButton: CompoundButton = binding.materialCheckBox
        checkButton.isChecked = true
//        checkButton.setOnCheckedChangeListener()
        binding.materialButton.setOnClickListener { onCheckedChange(!checkButton.isChecked) }

    }

    private fun onCheckedChange(checked: Boolean) {
        if (checked) {
            for (it in 0..cartAdapter.itemCount) {
                (binding.rcCardList.findViewHolderForLayoutPosition(it) as CartItemAdapter.VH).toggleChecked(checked)
            }
        }
    }
}




