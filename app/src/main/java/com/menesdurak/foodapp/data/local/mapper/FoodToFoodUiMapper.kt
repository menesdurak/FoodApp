package com.menesdurak.foodapp.data.local.mapper

import com.menesdurak.foodapp.common.mapper.ListMapper
import com.menesdurak.foodapp.data.remote.dto.Food
import com.menesdurak.foodapp.data.remote.dto.FoodUi

class FoodToFoodUiMapper : ListMapper<Food, FoodUi> {
    override fun map(input: List<Food>): List<FoodUi> {
        return input.map {
            FoodUi(
                name = it.name,
                price = it.price,
                id = it.id,
                image = it.image
            )
        }
    }
}