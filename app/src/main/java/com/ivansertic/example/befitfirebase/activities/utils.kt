package com.ivansertic.example.befitfirebase.activities

import android.content.Context
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.ivansertic.example.befitfirebase.models.User

fun Context.showToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this,text,duration).show()
}

fun DataSnapshot.asUser(): User? =
    getValue(User::class.java)?.copy(uid = key!!)