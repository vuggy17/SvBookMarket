package com.example.svbookmarket.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import com.example.svbookmarket.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditAddressDialog:BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.activity_edit_adress,container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val rootView = View.inflate(context, R.layout.activity_edit_adress, null)
        dialog.setContentView(rootView)

        val bottomSheet = dialog.window?.findViewById(R.id.design_bottom_sheet) as FrameLayout
        val behaviour = BottomSheetBehavior.from(bottomSheet)

        behaviour.peekHeight = 1600

    }
}