package com.menesdurak.foodapp.domain.usecase

import com.menesdurak.foodapp.domain.repository.LocalRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllFavoriteFoodsIdUseCase @Inject constructor(private val localRepository: LocalRepository) {
    suspend operator fun invoke() = localRepository.getAllFavoritesId()
}