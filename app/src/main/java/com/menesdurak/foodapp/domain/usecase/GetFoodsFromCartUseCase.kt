package com.menesdurak.foodapp.domain.usecase

import com.menesdurak.foodapp.domain.repository.RemoteRepository
import javax.inject.Inject

class GetFoodsFromCartUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository,
) {
    suspend operator fun invoke(userName: String) = remoteRepository.getFoodsFromCart(userName)
}