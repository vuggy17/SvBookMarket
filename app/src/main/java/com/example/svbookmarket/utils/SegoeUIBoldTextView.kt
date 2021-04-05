package com.example.svbookmarket.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat
import com.google.android.material.resources.CancelableFontCallback
import com.google.android.material.textview.MaterialTextView

class SegoeUIBoldTextView(context: Context, attrs: AttributeSet) :
    MaterialTextView(context, attrs) {
    init {
        ApplyFont()
    }

    private fun ApplyFont() {
        val typeFace: Typeface = Typeface.createFromAsset(context.assets, "segoeuib.ttf")
        typeface = typeFace
    }
}