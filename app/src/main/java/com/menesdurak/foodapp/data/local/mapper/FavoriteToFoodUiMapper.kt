package com.menesdurak.foodapp.data.local.mapper

import com.menesdurak.foodapp.common.mapper.Mapper
import com.menesdurak.foodapp.data.local.entity.FavoriteFood
import com.menesdurak.foodapp.data.remote.dto.FoodUi

class FavoriteToFoodUiMapper: Mapper<FavoriteFood, FoodUi> {
    override fun map(input: FavoriteFood): FoodUi {
        return FoodUi(
            name = input.foodName,
            price = input.price,
            id = input.foodId.toString(),
            image = input.image
        )
    }
}