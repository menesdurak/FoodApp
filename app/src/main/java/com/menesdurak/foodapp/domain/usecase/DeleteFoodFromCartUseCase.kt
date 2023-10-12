package com.menesdurak.foodapp.domain.usecase

import com.menesdurak.foodapp.domain.repository.FoodRepository
import javax.inject.Inject

class DeleteFoodFromCartUseCase @Inject constructor(
    private val foodRepository: FoodRepository,
) {
    suspend operator fun invoke(foodId: Int, userName: String) =
        foodRepository.deleteFoodFromCart(foodId, userName)
}