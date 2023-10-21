package com.menesdurak.foodapp.domain.usecase

import com.menesdurak.foodapp.data.local.entity.FavoriteFood
import com.menesdurak.foodapp.domain.repository.LocalRepository
import javax.inject.Inject

class GetAllFavoriteFoodsUseCase @Inject constructor(private val localRepository: LocalRepository) {
    suspend operator fun invoke() = localRepository.getAllFavorites()
}