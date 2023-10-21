package com.menesdurak.foodapp.domain.usecase

import com.menesdurak.foodapp.domain.repository.RemoteRepository
import javax.inject.Inject

class PostFoodsToCartUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository,
) {
    suspend operator fun invoke(
        foodName: String,
        image: String,
        price: Int,
        count: Int,
        userName: String,
    ) = remoteRepository.postFoodsToCart(foodName, image, price, count, userName)
}