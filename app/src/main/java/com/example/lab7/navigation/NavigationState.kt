package com.example.lab7.navigation

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class NavigationState(val route: String) {
    data object MealsCategories: NavigationState("categories")

    data object MealsRecipesList: NavigationState("categories/{category}")  {
        fun createRoute(category: String) = "categories/$category"
    }

    data object MealDetail: NavigationState("mealDetail/{mealId}") {
        fun createRoute(mealId: String) = "mealDetail/$mealId"
    }
    data object Home: NavigationState("home")

    data object Camera: NavigationState("camera")

    data object CameraEdit : NavigationState("camera/edit")

    data object Supermarket: NavigationState("supermarket")

    data object AddSupermarket: NavigationState("supermarket/{photoPath}") {
        fun createRoute(photoPath: String): String {
            val encodedPath = URLEncoder.encode(photoPath, StandardCharsets.UTF_8.toString())
            return "supermarket/$encodedPath"
        }


    }

    data object EditSupermarket: NavigationState("supermarket/edit")
}