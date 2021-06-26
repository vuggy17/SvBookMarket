package com.example.svbookmarket.activities.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MarginItemDecoration(
    private val spaceSize: Int,
    private val leftSpaceSize: Int = -1,
    private val spanCount: Int = 1,
    private val orientation: Int = GridLayoutManager.VERTICAL,
    private val isHorizontalLayout: Boolean = false
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            if (!isHorizontalLayout) {
                if (orientation == GridLayoutManager.VERTICAL) {
                    if (parent.getChildAdapterPosition(view) < spanCount) {
                        top = spaceSize
                    }
                    if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                        left = spaceSize
                    }
                } else {
                    if (parent.getChildAdapterPosition(view) < spanCount) {
                        left = spaceSize
                    }
                    if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                        top = spaceSize
                    }
                }
            }

            left = if(leftSpaceSize!= -1) leftSpaceSize else spaceSize
            right = spaceSize
            bottom = spaceSize
        }
    }
}