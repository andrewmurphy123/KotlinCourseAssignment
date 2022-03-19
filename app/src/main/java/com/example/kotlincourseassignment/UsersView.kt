package com.example.kotlincourseassignment

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UsersView: ViewModel() {
    var username = mutableStateOf("TEST")

    fun loginUser( email: String, password: String ){
        Firebase.auth
            .signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                username.value = email
            }
    }

    fun logoutUser(){
        Firebase.auth.signOut()
        username.value = ""
    }
}