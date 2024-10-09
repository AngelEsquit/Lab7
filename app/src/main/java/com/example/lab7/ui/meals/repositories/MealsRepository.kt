package com.example.lab7.ui.meals.repositories

import com.example.lab7.networking.webservices.MealsWebService
import com.example.lab7.networking.response.meals.Meal

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {

    suspend fun filterByCategory(category: String): List<Meal> {
        return webService.filterMealsByCategory(category).meals
    }
}