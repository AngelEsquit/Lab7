package com.example.lab7.navigation

sealed class NavigationState(val route: String) {
    data object MealsCategories: NavigationState("categories")

    data object MealsRecipesList: NavigationState("categories/{category}")  {
        fun createRoute(category: String) = "categories/$category"
    }

    data object MealDetail: NavigationState("mealDetail/{mealId}") {
        fun createRoute(mealId: String) = "mealDetail/$mealId"
    }
    data object Home: NavigationState("home")

    data object Profile: NavigationState("profile")

    data object ViewPhoto: NavigationState("viewPhoto")

    data object Camera: NavigationState("camera")

    data object Supermarket: NavigationState("supermarket")

    data object SupermarketCamera: NavigationState("supermarket/{photoPath}") {
        fun createRoute(photoPath: String) = "supermarket/$photoPath"
    }
}