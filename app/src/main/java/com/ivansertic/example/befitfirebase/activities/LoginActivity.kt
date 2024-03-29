package com.ivansertic.example.befitfirebase.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.ivansertic.example.befitfirebase.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "LoginActivity"

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        login_btn.setOnClickListener(this)
        txt_link.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.login_btn -> {
                val email = edt_username.text.toString()
                val password = edt_password.text.toString()

                if (validate(email, password)) {
                    mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            } else {
                                showToast("Incorrect email or password")
                            }
                        }

                } else {
                    showToast("Please enter email and password")
                }

            }
        }

        when (view.id) {
            R.id.txt_link -> {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
        }

    }

    private fun validate(email: String, password: String) =
        email.isNotEmpty() && password.isNotEmpty()
}
