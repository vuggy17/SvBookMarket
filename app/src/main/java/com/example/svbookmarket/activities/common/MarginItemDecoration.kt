package com.example.svbookmarket.activities.common

import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.grpc.internal.SharedResourceHolder


class MarginItemDecoration(
    private val spaceSize: Int,
    private val spanCount: Int = 1,
    private val orientation: Int = GridLayoutManager.VERTICAL,
    private val isHorizontalLinearLayout: Boolean = false
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        val marginLeft = 48
        val parentWidth = parent.measuredWidth


        with(outRect) {
            if (orientation == GridLayoutManager.VERTICAL && !isHorizontalLinearLayout) {
                if (parent.getChildAdapterPosition(view) < spanCount) {
                    top = spaceSize
                }
                if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                    left = marginLeft
                    right = spaceSize *3
                    Log.d("margin", "$parentWidth,$right, ${outRect.width()}, ${parent.getChildAdapterPosition(view)} ")
                }
            } else {
                if (parent.getChildAdapterPosition(view) < spanCount) {
                    left = spaceSize
                }
                if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                    top = spaceSize
                }
            }
            if (isHorizontalLinearLayout) {
                if (parent.getChildAdapterPosition(view) == 0) {
                    left = marginLeft
                }
            }
            right = spaceSize
            bottom = spaceSize
        }
    }
}