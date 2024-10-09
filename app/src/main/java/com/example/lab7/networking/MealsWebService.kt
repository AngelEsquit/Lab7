package com.example.lab7.networking

import com.example.lab7.networking.response.categories.MealsCategoriesResponse
import com.example.lab7.networking.response.mealdetail.MealDetailResponse
import com.example.lab7.networking.response.meals.MealsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealsWebService {

    private var api: MealsApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MealsApi::class.java)
    }

    suspend fun getMealsCategories(): MealsCategoriesResponse {
        return api.getMealsCategories()
    }

    suspend fun filterMealsByCategory(category: String): MealsResponse {
        return api.filterByCategory(category)
    }

    fun getMealDetail(mealId: String): Call<MealDetailResponse> {
        return api.getMealDetail(mealId)
    }

}
