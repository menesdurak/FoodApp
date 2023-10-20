package com.menesdurak.foodapp.common

import android.text.TextUtils
import android.util.Patterns
import com.menesdurak.foodapp.data.remote.dto.FoodIdCount
import com.menesdurak.foodapp.data.remote.dto.CartFood
import com.menesdurak.foodapp.data.remote.dto.CartFoodUi

fun String.isValidEmail(): Boolean =
    !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun List<CartFood>.convertToCartFoodUi(): List<CartFoodUi> {
    val foodNames = mutableSetOf<String>()
    val foodMap = mutableMapOf<String, FoodIdCount>()
    val cartFoods = mutableListOf<CartFoodUi>()

    this.forEach {
        foodNames.add(it.foodName)
    }

    foodNames.forEach { foodName ->
        val filteredFoodList = this.filter {
            it.foodName == foodName
        }
        val foodIdList = mutableListOf<String>()
        var count = 0
        filteredFoodList.forEach {
            foodIdList.add(it.foodId)
            count += it.count.toInt()
            foodMap[foodName] = FoodIdCount(foodIdList, count, it.image, it.userName, it.price)
        }
    }

    foodMap.forEach {
        cartFoods.add(
            CartFoodUi(
                ids = it.value.ids,
                foodName = it.key,
                image = it.value.image,
                price = it.value.price,
                count = it.value.count,
                totalPrice = it.value.totalPrice,
                userName = it.value.userName
            )
        )
    }

    return cartFoods
}