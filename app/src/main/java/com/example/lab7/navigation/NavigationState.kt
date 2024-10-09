package com.example.lab7.navigation

sealed class NavigationState(val route: String) {
    data object MealsCategories: NavigationState("categories")

    data object MealsRecipesList: NavigationState("categories/{category}")  {
        fun createRoute(category: String) = "categories/$category"
    }
    data object Home: NavigationState("home")
    data object Profile: NavigationState("profile")
}