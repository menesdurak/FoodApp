package com.menesdurak.foodapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.menesdurak.foodapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : AuthRepository {
    override fun signUp(email: String, password: String): String {
        var userEMail = ""
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                userEMail = auth.currentUser!!.email!!
            }
        }
        return userEMail
    }

    override fun signIn(email: String, password: String): String {
        var userEMail = ""
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    userEMail = auth.currentUser!!.email!!
                }
            }
        return userEMail
    }

    override fun signOut() {
        auth.signOut()
    }

    override fun getUserMail(): String {
        return auth.currentUser?.email ?: ""
    }
}