package com.example.svbookmarket.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.AppAccount
import com.example.svbookmarket.activities.model.User
import com.example.svbookmarket.activities.model.UserDeliverAddress
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    //Init view
    private lateinit var agreeTermAndCondition: CheckBox;
    private lateinit var errorAgreeTermNotification: TextView
    private lateinit var edtPassword: EditText
    private lateinit var edtConfirmPasswordLayout: TextInputLayout
    private lateinit var edtPasswordConfirm: EditText
    private lateinit var edtPasswordLayout: TextInputLayout
    private lateinit var edtName: EditText
    private lateinit var edtNameLayout: TextInputLayout
    private lateinit var edtEmail: EditText
    private lateinit var edtEmailLayout: TextInputLayout
    private lateinit var tvSignIn: TextView

    //Init Data
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var user: User
    private lateinit var appAccount: AppAccount

    //Init FireBase
    private val db = Firebase.firestore
    private val dbReference = db.collection("accounts")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        agreeTermAndCondition = findViewById(R.id.cbAgreeTermAndConditions)
        errorAgreeTermNotification = findViewById(R.id.errorAgreeTermNotification)
        edtPassword = findViewById(R.id.edtUserPassword)
        edtConfirmPasswordLayout = findViewById(R.id.inputTextPasswordConfirmLayout)
        edtPasswordConfirm = findViewById(R.id.edtUserPasswordConfirm)
        edtPasswordLayout = findViewById(R.id.inputTextPasswordLayout)
        edtName = findViewById(R.id.edtUserName)
        edtNameLayout = findViewById(R.id.inputTextUserNameLayout)
        edtEmail = findViewById(R.id.edtUserEmail)
        edtEmailLayout = findViewById(R.id.inputTextEmailLayout)
        tvSignIn = findViewById(R.id.tvSignIn)

        tvSignIn.setOnClickListener{
            startActivity(Intent(baseContext,LoginActivity::class.java))
        }

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
        if (isValidName() && isValidEmail() && isValidPassword() && isValidConfirmPassword() && isAgreeTermAndConditions()) {
            dbReference.get().addOnSuccessListener() { result ->
                var emailExist: Boolean=false
                for (document in result) {
                    if (document.id == edtEmail.text.toString()) {
                        emailExist = true
                    }
                }
                if(emailExist){
                    edtEmailLayout.error = "Email is exists"
                }else {
                    email = edtEmail.text.toString()
                    password = edtPassword.text.toString()
                    user = User(fullName = edtName.text.toString())
                    appAccount = AppAccount(email, password, user)
                    dbReference.document(appAccount.email).set(appAccount).addOnSuccessListener {
                        Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener { e ->
                        Toast.makeText(this, "Register Fail$e", Toast.LENGTH_SHORT).show()
                    }
                    val dbReferenceRecentAccountUserAddressAccount = dbReference.document(appAccount.email).collection("userDeliverAddresses")
                    dbReferenceRecentAccountUserAddressAccount.document().set(UserDeliverAddress(user.fullName, user.phoneNumber, user.addressLane, user.district,user.city, true))
                    startActivity(Intent(baseContext,LoginActivity::class.java))
                }
            }
        }
    }


    private fun isAgreeTermAndConditions(): Boolean {

        return if (agreeTermAndCondition.isChecked) {
            errorAgreeTermNotification.visibility = View.INVISIBLE
            true
        } else {
            errorAgreeTermNotification.visibility = View.VISIBLE
            false
        }
    }

    private fun isValidConfirmPassword(): Boolean {

        return if (edtPasswordConfirm.text.isEmpty()) {
            edtConfirmPasswordLayout.error = "Confirm password can not empty"
            false
        } else {
            return if (edtPassword.text.toString() != edtPasswordConfirm.text.toString()) {
                edtConfirmPasswordLayout.error = "Password and Confirm Password must be one"
                false
            } else {
                edtConfirmPasswordLayout.error = null
                true
            }
        }
    }

    private fun isValidPassword(): Boolean {

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