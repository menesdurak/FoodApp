package com.menesdurak.foodapp.common.mapper

interface Mapper<I,O>{
    fun map(input:I):O
}