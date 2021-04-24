package com.example.svbookmarket.activities.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecycleViewItemMargin: RecyclerView.ItemDecoration() {
    var row: Int = 0;
    var margin: Int = 0;

    /**
     * constructor
     * @param margin desirable margin size in px between the views in the recyclerView
     * @param columns number of columns of the RecyclerView
     */
    init {
        fun RecyclerViewItemMargin(
            margin: Int,
            columns: Int
        ) {
            this.margin = margin;
            this.row = row;
        }
    }


    /**
     * Set different margins for the items inside the recyclerView: no top margin for the first row
     * and no left margin for the first column.
     */
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State): Unit{

        var position: Int = parent.getChildLayoutPosition(view);
        //set right margin to all
        outRect.right = margin;
        //set bottom margin to all
        outRect.bottom = margin;
        //we only add top margin to the first row
        if (position < row) {
            outRect.top = margin;
        }
        //add left margin only to the first column
        if(position%row==0){
            outRect.left = margin;
        }
    }
}
