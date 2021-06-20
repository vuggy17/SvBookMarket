package com.example.svbookmarket.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.data.FullBookList
import com.example.svbookmarket.activities.model.AppAccount
import com.example.svbookmarket.activities.model.User
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    //init view
    private lateinit var loginEmailLayout: TextInputLayout
    private lateinit var loginPasswordLayout: TextInputLayout
    private lateinit var loginEmail: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginSignUp: TextView
    private lateinit var loginButton: Button

    //init database reference
    private val db = Firebase.firestore
    private val dbAccountsReference = db.collection("accounts")

    // tạo biến account để lưu về thông tin khách hàng đã có
    companion object {
        var recentAccountLogin: AppAccount = AppAccount("", "", User())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginEmailLayout = findViewById(R.id.LoginEmailLayout)
        loginEmail = findViewById(R.id.LoginEmail)
        loginPasswordLayout = findViewById(R.id.LoginPasswordLayout)
        loginPassword = findViewById(R.id.LoginPassword)
        loginSignUp = findViewById(R.id.LoginSignUp)
        loginButton = findViewById(R.id.loginButton)



        loginButton.setOnClickListener {
            onButtonLoginClick()
        }

        val imgBackArrow: AppCompatImageView = findViewById(R.id.imgBackArrow)
        imgBackArrow.setOnClickListener {
            onBackPressed()
        }
        loginSignUp.setOnClickListener {
            startActivity(Intent(baseContext, RegisterActivity::class.java))
            finish() //remove activity from backstack
        }
    }

    private fun onButtonLoginClick() {
        if (isValidEmail() && isValidPassword()) {
            dbAccountsReference.get().addOnSuccessListener { result ->
                var emailExist: Boolean = false
                for (document in result) {
                    if (document.id == loginEmail.text.toString()) {
                        emailExist = true
                    }
                }
                if (emailExist) {
                    dbAccountsReference.document(loginEmail.text.toString()).get()
                        .addOnSuccessListener { result ->
                            if (result["password"] == loginPassword.text.toString()) {
                                Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show()
                                val userMap = result["user"] as HashMap<*, *>
                                val recentUser: User = User(
                                    fullName = userMap["fullName"].toString(),
                                    gender = userMap["gender"].toString(),
                                    birthDay = userMap["birthDay"].toString(),
                                    phoneNumber = userMap["phoneNumber"].toString(),
                                    addressLane = userMap["addressLane"].toString(),
                                    city = userMap["city"].toString(),
                                    district = userMap["district"].toString(),
                                )
                                recentAccountLogin = AppAccount(result["email"].toString(), result["password"].toString(),recentUser)
                                CurrentUserInfo.getInstance()
                                FullBookList.getInstance()
                                startActivity(Intent(baseContext, HomeActivity::class.java))


                            } else {
                                loginPasswordLayout.error = "Password is not true"
                            }
                        }
                } else {
                    loginEmailLayout.error = "Email is not exist, sign up now?"
                }
            }
        }
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

    private fun isValidPassword(): Boolean {

        return if (loginPassword.text.isEmpty()) {
            loginPasswordLayout.error = "Password can not empty"
            false
        } else {
            if (loginPassword.text.count() < 8) {
                loginPasswordLayout.error = "Password must have more than 8 character"
                false
            } else {
                loginPasswordLayout.error = null
                true
            }
        }
    }

    private fun isValidEmail(): Boolean {
        return if (loginEmail.text.isEmpty()) {
            loginEmailLayout.error = "Email can not empty"
            false
        } else {
            if (isEmailRightFormat(loginEmail.text.toString().trim())) {
                loginEmailLayout.error = null
                true
            } else {
                loginEmailLayout.error = "Invalid email"
                false
            }
        }
    }
}