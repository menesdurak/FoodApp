package com.menesdurak.foodapp.domain.usecase

import com.menesdurak.foodapp.domain.repository.RemoteRepository
import javax.inject.Inject

class DeleteFoodFromCartUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository,
) {
    suspend operator fun invoke(foodId: Int, userName: String) =
        remoteRepository.deleteFoodFromCart(foodId, userName)
}