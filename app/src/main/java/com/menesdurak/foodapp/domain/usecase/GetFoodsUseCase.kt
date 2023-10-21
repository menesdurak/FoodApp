package com.menesdurak.foodapp.domain.usecase

import com.menesdurak.foodapp.domain.repository.RemoteRepository
import javax.inject.Inject

class GetFoodsUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
) {
    suspend operator fun invoke() = remoteRepository.getFoods()
}