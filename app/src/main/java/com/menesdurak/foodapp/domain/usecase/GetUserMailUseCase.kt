package com.menesdurak.foodapp.domain.usecase

import com.menesdurak.foodapp.domain.repository.AuthRepository
import javax.inject.Inject

class GetUserMailUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke() = authRepository.getUserMail()
}