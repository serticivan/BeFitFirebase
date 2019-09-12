package com.ivansertic.example.befitfirebase.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.ivansertic.example.befitfirebase.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "LoginActivity"

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        logout_btn.setOnClickListener(this)
        profile_btn.setOnClickListener(this)
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.logout_btn -> {
                mAuth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        when (view.id) {
            R.id.profile_btn -> {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
        }

    }
}
