package com.example.svbookmarket.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.svbookmarket.R
import com.example.svbookmarket.databinding.ActivityEditProfileBinding


class EditProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



//        private fun handleKeyEnter(view: View, keyCode: Int): Boolean {
//            if (keyCode == KeyEvent.KEYCODE_ENTER) {
//                val inputMethodManager =
//                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
//                binding.costOfServiceEditText.clearFocus()
//                return true
//            }
//            return false
//        }

    }
}
// TODO: 10/05/2021 lấy object gọi được truyền vào binding, vd: binding.edittex.binding(view->{}) thì lấy edittext để tuyền vào như một param của binding