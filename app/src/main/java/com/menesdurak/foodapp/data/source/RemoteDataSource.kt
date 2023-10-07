package com.menesdurak.foodapp.data.source

import com.menesdurak.foodapp.data.NetworkResponseState
import com.menesdurak.foodapp.data.remote.dto.FoodsResponse

interface RemoteDataSource {

    suspend fun getAllFoods(): NetworkResponseState<FoodsResponse>
}