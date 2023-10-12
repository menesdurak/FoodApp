package com.menesdurak.foodapp.presentation.login

import androidx.lifecycle.ViewModel
import com.menesdurak.foodapp.domain.usecase.GetUserMailUseCase
import com.menesdurak.foodapp.domain.usecase.SignInUseCase
import com.menesdurak.foodapp.domain.usecase.SignOutUseCase
import com.menesdurak.foodapp.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val getUserMailUseCase: GetUserMailUseCase
): ViewModel() {

    fun signUp(email: String, password: String): String {
        return signUpUseCase.invoke(email, password)
    }

    fun signIn(email: String, password: String): String {
        return signInUseCase.invoke(email, password)
    }

    fun signOut() {
        signOutUseCase.invoke()
    }

    fun getUserMail(): String {
        return getUserMailUseCase.invoke()
    }
}