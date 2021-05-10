package com.example.svbookmarket.activities

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.example.svbookmarket.R
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val imgBackArrow: AppCompatImageView = findViewById(R.id.imgBackArrow)
        imgBackArrow.setOnClickListener {
            onBackPressed()
        }
        val btnSignUp: Button = findViewById(R.id.btn_Register)
        btnSignUp.setOnClickListener {
            signUpClick()
        }

    }

    private fun signUpClick() {

        if(isValidName() && isValidEmail() && isValidPassword() && isValidConfirmPassword()&&isAgreeTermAndConditions()){
            //put dữ liệu lên DB
            Toast.makeText(this,"Register Success",Toast.LENGTH_LONG ).show()
        }
    }
    private fun isAgreeTermAndConditions(): Boolean{
        val agreeTermAndCondition: CheckBox = findViewById(R.id.cbAgreeTermAndConditions)
        val errorAgreeTermNotification: TextView =findViewById(R.id.errorAgreeTermNotification)
        return if(agreeTermAndCondition.isChecked) {
            errorAgreeTermNotification.visibility = View.INVISIBLE
            true
        }else{
            errorAgreeTermNotification.visibility =View.VISIBLE
            false
        }
    }

    private fun isValidConfirmPassword(): Boolean {
        val edtPassword: EditText = findViewById(R.id.edtUserPassword)
        val edtConfirmPasswordLayout: TextInputLayout =
            findViewById(R.id.inputTextPasswordConfirmLayout)
        val edtPasswordConfirm: EditText = findViewById(R.id.edtUserPasswordConfirm)
        return if (edtPasswordConfirm.text.isEmpty()) {
            edtConfirmPasswordLayout.error ="Confirm password can not empty"
            false
        }else{
            return if(edtPassword.text.toString() != edtPasswordConfirm.text.toString()){
                edtConfirmPasswordLayout.error="Password and Confirm Password must be one"
                false
            } else{
                edtConfirmPasswordLayout.error =null
                true
            }
        }
    }

    private fun isValidPassword(): Boolean {
        val edtPassword: EditText = findViewById(R.id.edtUserPassword)
        val edtPasswordLayout: TextInputLayout = findViewById(R.id.inputTextPasswordLayout)
        return if (edtPassword.text.isEmpty()) {
            edtPasswordLayout.error = "Password can not empty"
            false
        } else {
            if (edtPassword.text.count() < 8) {
                edtPasswordLayout.error = "Password must have more than 8 character"
                false
            } else {
                edtPasswordLayout.error = null
                true
            }
        }
    }

    private fun isValidName(): Boolean {
        val edtName: EditText = findViewById(R.id.edtUserName)
        val edtNameLayout: TextInputLayout = findViewById(R.id.inputTextUserNameLayout)
        return if (edtName.text.isEmpty()) {
            edtNameLayout.error = "Name can not empty"
            false
        } else {
            return if (isNameContainNumberOrSpecialCharacter(edtName.text.toString())) {
                edtNameLayout.error = "Name can not contain number of special character"
                false
            } else {
                edtNameLayout.error = null
                true
            }
        }
    }

    private fun isNameContainNumberOrSpecialCharacter(name: String): Boolean {
        var hasNumber: Boolean = Pattern.compile(
            "[0-9]"
        ).matcher(name).find()
        var hasSpecialCharacter: Boolean = Pattern.compile(
            "[!@#$%&.,\"':;?*()_+=|<>?{}\\[\\]~-]"
        ).matcher(name).find()
        return hasNumber || hasSpecialCharacter
    }

    private fun isEmailRightFormat(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    private fun isValidEmail(): Boolean {
        val edtEmail: EditText = findViewById(R.id.edtUserEmail)
        val edtEmailLayout: TextInputLayout = findViewById(R.id.inputTextEmailLayout)
        return if (edtEmail.text.isEmpty()) {
            edtEmailLayout.error = "Email can not empty"
            false
        } else {
            if (isEmailRightFormat(edtEmail.text.toString().trim())) {
                edtEmailLayout.error = null
                true
            } else {
                edtEmailLayout.error = "Invalid email"
                false
            }
        }
    }
}