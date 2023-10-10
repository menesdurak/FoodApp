package com.menesdurak.foodapp.domain.usecase

import com.menesdurak.foodapp.domain.repository.FoodRepository
import javax.inject.Inject

class GetFoodsFromCartUseCase @Inject constructor(
    private val foodRepository: FoodRepository,
) {
    suspend operator fun invoke(userName: String) = foodRepository.getFoodsFromCart(userName)
}