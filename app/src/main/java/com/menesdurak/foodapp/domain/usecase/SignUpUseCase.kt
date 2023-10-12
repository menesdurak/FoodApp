package com.menesdurak.foodapp.domain.usecase

import com.menesdurak.foodapp.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String, password: String) = authRepository.signUp(email, password)
}