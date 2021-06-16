package com.example.svbookmarket.activities.common

import android.view.View
import android.view.View.*

class ManageViews {
    companion object {
        fun display(view: View) {
            if (view.visibility == GONE) {
                view.visibility = VISIBLE
            }
        }

        fun hide(view: View) {
            if (view.visibility == VISIBLE) {
                view.visibility = GONE
            }
        }
    }
}