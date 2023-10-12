package com.menesdurak.foodapp.domain.repository

import com.menesdurak.foodapp.data.NetworkResponseState

interface AuthRepository {

    fun signUp(email: String, password: String): String

    fun signIn(email: String, password: String): String

    fun signOut()

    fun getUserMail(): String
}