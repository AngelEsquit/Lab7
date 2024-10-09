package com.example.lab7.ui.mealdetail.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lab7.navigation.AppBar
import com.example.lab7.ui.mealdetail.viewmodel.MealDetailViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MealsDetailScreen(navController: NavController, mealId: String) {
    val viewModel: MealDetailViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.loadMealDetail(mealId) // Usa mealId desde navigation
    }

    val mealDetail = viewModel.mealDetail.value

    Scaffold(topBar = {
        AppBar(title = "Meal", navController = navController)
    }) {
        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            mealDetail?.let { meal ->
                item {
                    MealCategory(meal, navController)
                }
            }
        }
    }
}
