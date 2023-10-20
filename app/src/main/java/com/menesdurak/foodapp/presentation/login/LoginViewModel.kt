package com.menesdurak.foodapp.presentation.login

import androidx.lifecycle.ViewModel
import com.menesdurak.foodapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    fun signUp(email: String, password: String): String {
        return authRepository.signUp(email, password)
    }

    fun signIn(email: String, password: String): String {
        return authRepository.signIn(email, password)
    }

    fun signOut() {
        authRepository.signOut()
    }

    fun getUserMail(): String {
        return authRepository.getUserMail()
    }
}