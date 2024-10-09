package com.example.lab7.ui.categories.repositories

import com.example.lab7.networking.webservices.MealsWebService
import com.example.lab7.networking.response.categories.Categories

class MealsCategoryRepository(private val webService: MealsWebService = MealsWebService()) {

    suspend fun getMealsCategories(): List<Categories> {
        return webService.getMealsCategories().categories
    }
}