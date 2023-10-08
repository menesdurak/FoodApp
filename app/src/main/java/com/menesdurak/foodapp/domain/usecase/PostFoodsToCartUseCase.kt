package com.menesdurak.foodapp.domain.usecase

import com.menesdurak.foodapp.domain.repository.FoodRepository
import javax.inject.Inject

class PostFoodsToCartUseCase @Inject constructor(
    private val foodRepository: FoodRepository,
) {
    suspend operator fun invoke(
        foodName: String,
        image: String,
        price: Int,
        count: Int,
        userName: String,
    ) = foodRepository.postFoodsToCart(foodName, image, price, count, userName)
}