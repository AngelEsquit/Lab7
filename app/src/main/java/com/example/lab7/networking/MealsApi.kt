package com.example.lab7.networking

import com.example.lab7.networking.response.categories.MealsCategoriesResponse
import com.example.lab7.networking.response.mealdetail.MealDetailResponse
import com.example.lab7.networking.response.meals.MealsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {

    @GET("categories.php")
    suspend fun getMealsCategories(): MealsCategoriesResponse

    @GET("filter.php")
    suspend fun filterByCategory(
        @Query("c") category: String
    ): MealsResponse

    @GET("lookup.php")
    fun getMealDetail(
        @Query("i") mealId: String
    ): Call<MealDetailResponse>
}