package com.ivansertic.example.befitfirebase.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ivansertic.example.befitfirebase.R
import com.ivansertic.example.befitfirebase.models.User
import com.ivansertic.example.befitfirebase.utils.ValueListenerAdapter
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private val TAG = "ProfileActivity"

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabase: DatabaseReference
    private lateinit var mUser: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference

        fun currentUserReference(): DatabaseReference =
            mDatabase.child("users").child(mAuth.currentUser!!.uid)

        currentUserReference().addListenerForSingleValueEvent(
            ValueListenerAdapter {
                mUser = it.asUser()!!
                name.text = mUser.name
            }
        )
    }
}
