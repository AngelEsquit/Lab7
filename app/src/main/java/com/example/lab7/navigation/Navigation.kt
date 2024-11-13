package com.example.lab7.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.lab7.ui.camera.view.CameraScreen
import com.example.lab7.ui.categories.view.MealsCategoriesScreen
import com.example.lab7.ui.categories.viewmodel.MealsCategoriesViewModel
import com.example.lab7.ui.meals.view.MealsFilterScreen
import com.example.lab7.ui.supermarket.view.EditSupermarket
import com.example.lab7.ui.supermarket.view.SupermarketScreen
import com.example.lab7.ui.supermarket.viewmodel.SupermarketViewModel

@Composable
fun Navigation(navController: NavHostController, mealViewModel: MealsCategoriesViewModel, supermarketViewModel: SupermarketViewModel, modifier: Modifier = Modifier) {
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
            CameraScreen(navController = navController, viewModel = supermarketViewModel)
        }

        composable(route = NavigationState.CameraEdit.route) {
            CameraScreen(navController = navController, type = "edit", viewModel = supermarketViewModel)
        }

        composable(route = NavigationState.Supermarket.route) {
            SupermarketScreen(navController = navController, viewModel = supermarketViewModel)
        }

        composable(route = NavigationState.AddSupermarket.route, arguments = listOf(navArgument("photoPath") { type = NavType.StringType })
        ) { backStackEntry ->
            val photoPath = backStackEntry.arguments?.getString("photoPath")
            SupermarketScreen(navController = navController, photoPath = photoPath ?: "", viewModel = supermarketViewModel)

        }

        composable(route = NavigationState.EditSupermarket.route) {
            EditSupermarket(navController = navController, viewModel = supermarketViewModel)
        }
    }
}