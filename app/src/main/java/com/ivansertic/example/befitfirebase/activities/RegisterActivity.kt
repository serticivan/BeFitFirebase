package com.ivansertic.example.befitfirebase.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ivansertic.example.befitfirebase.R
import com.ivansertic.example.befitfirebase.models.User
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "LoginActivity"

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference
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
        val fullName = edt_fullname.text.toString()
        val email = edt_email.text.toString()
        val password = edt_password.text.toString()
        val password2 = edt_password2.text.toString()

        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {

            showToast("All fields are required")

        } else {

            if (password == password2) {
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val user = makeUser(fullName, email)
                            val reference = mDatabase.child("users").child(it.result!!.user.uid)
                            reference.setValue(user)
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
                            showToast("Something went wrong, please try again later")
                        }

                    }

            } else {
                showToast("Password doesn't match")
            }
        }


    }

    private fun makeUser(fullName: String, email: String): User {
        val username = makeUsername(fullName)
        return User(name = fullName, username = username, email = email)
    }

    private fun makeUsername(fullName: String) =
        fullName.toLowerCase().replace(" ", ".")
}
