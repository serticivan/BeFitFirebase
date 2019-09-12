package com.ivansertic.example.befitfirebase.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.ivansertic.example.befitfirebase.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "LoginActivity"

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()
        register_btn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.register_btn -> {
                onRegister()
            }
        }

    }

    private fun onRegister() {
        val fullname = edt_fullname.text.toString()
        val email = edt_email.text.toString()
        val password = edt_password.text.toString()
        val password2 = edt_password2.text.toString()

        if (fullname.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {

            showToast("All fields are required")

        } else {

            if (password == password2) {
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showToast("User successfully created")
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {
                            showToast("Something went wrong, please try again later")
                        }

                    }

            } else {
                showToast("Password doesn't match")
            }
        }


    }
}
