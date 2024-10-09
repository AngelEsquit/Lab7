package com.example.lab7.ui.mealdetail.repositories

import com.example.lab7.networking.MealsWebService
import com.example.lab7.networking.response.mealdetail.MealDetailResponse
import retrofit2.Call


class MealDetailRepository(private val webService: MealsWebService = MealsWebService()) {

    suspend fun getMealDetail(mealId: String): Call<MealDetailResponse> {
        return webService.getMealDetail(mealId)
    }
}
