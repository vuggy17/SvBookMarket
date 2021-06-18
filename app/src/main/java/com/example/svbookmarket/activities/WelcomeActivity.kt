package com.example.svbookmarket.activities

import CurrentUserInfo
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.AppAccount
import com.example.svbookmarket.activities.model.User
import com.example.svbookmarket.activities.viewmodel.LoadDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WelcomeActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    private val dbAccountsReference = db.collection("accounts")
    var loadDialog = LoadDialog(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        auth = Firebase.auth

        var login: Button = findViewById(R.id.btnLogin)
        login.setOnClickListener {
            startActivity(Intent(baseContext, LoginActivity::class.java))
            finish();
        }
        var guestLogin = findViewById<TextView>(R.id.tvContinueAsGuest)
        guestLogin.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish();
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            loadDialog.startLoading()
            loadData(currentUser.email.toString())
        }
    }

    private fun loadData(email: String) {
        dbAccountsReference.document(email).get()
            .addOnSuccessListener { result ->

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
                LoginActivity.recentAccountLogin = AppAccount(
                    result["email"].toString(),
                    result["password"].toString(),
                    recentUser
                )
                CurrentUserInfo.getInstance()
                startActivity(Intent(baseContext, HomeActivity::class.java))
                loadDialog.dismissDialog()
                finish()
            }
    }
}