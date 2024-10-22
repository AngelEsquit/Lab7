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
import com.example.lab7.ui.camera.view.CameraScreen
import com.example.lab7.ui.categories.view.MealsCategoriesScreen
import com.example.lab7.ui.categories.viewmodel.MealsCategoriesViewModel
import com.example.lab7.ui.mealdetail.view.MealsDetailScreen
import com.example.lab7.ui.meals.view.MealsFilterScreen
import com.example.lab7.ui.supermarket.view.SupermarketScreen

@Composable
fun Navigation(navController: NavHostController, mealViewModel: MealsCategoriesViewModel, modifier: Modifier = Modifier) {
    NavHost(navController = navController,
        startDestination = NavigationState.MealsCategories.route,
        modifier = modifier) {

        composable(route = NavigationState.MealsCategories.route) {
            MealsCategoriesScreen(navController = navController,
                viewModel = mealViewModel)
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

        composable(route = NavigationState.Camera.route) {
            CameraScreen(navController = navController)
        }

        composable(route = NavigationState.Supermarket.route) {
            SupermarketScreen(navController = navController)
        }

        composable(route = NavigationState.SupermarketCamera.route) {backStackEntry ->
            val photoPath = backStackEntry.arguments?.getString("photoPath")
            if (photoPath != null) {
                SupermarketScreen(navController = navController, photoPath = photoPath)
            }
        }
    }
}