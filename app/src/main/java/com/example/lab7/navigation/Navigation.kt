package com.example.lab7.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab7.ui.categories.view.MealsCategoriesScreen
import com.example.lab7.ui.mealdetail.view.MealsDetailScreen
import com.example.lab7.ui.meals.view.MealsFilterScreen

@Composable
fun Navigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController,
        startDestination = NavigationState.MealsCategories.route,
        modifier = modifier) {

        composable(route = NavigationState.MealsCategories.route) {
            MealsCategoriesScreen(navController = navController)
        }
        composable(NavigationState.MealsRecipesList.route,
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val categoryName = arguments.getString("category") ?: ""
            Log.d("ARGUMENTNAV", categoryName)
            MealsFilterScreen(navController = navController, category = categoryName)
        }
        composable(route = NavigationState.Home.route) {
            MealsFilterScreen(navController = navController, category = "Chicken")
        }
        composable(route = NavigationState.Profile.route) {
            //Screen of Profile
        }

        composable(
            route = NavigationState.MealDetail.route,
            arguments = listOf(navArgument("mealId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId") ?: ""
            MealsDetailScreen(navController = navController, mealId = mealId)
        }
    }
}