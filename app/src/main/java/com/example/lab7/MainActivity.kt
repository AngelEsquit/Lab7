package com.example.lab7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.lab7.navigation.Navigation
import com.example.lab7.ui.categories.viewmodel.MealViewModelFactory
import com.example.lab7.ui.categories.viewmodel.MealsCategoriesViewModel
import com.example.lab7.ui.theme.MealsWithRoomTheme
import com.example.lab7.ui.supermarket.viewmodel.SupermarketViewModel
import com.example.lab7.ui.supermarket.viewmodel.SupermarketViewModelFactory
import com.example.lab7.ui.supermarket.repositories.SupermarketRepository

class MainActivity : ComponentActivity() {

    private lateinit var mealViewModel: MealsCategoriesViewModel

    private lateinit var supermarketViewModel: SupermarketViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = (applicationContext as MyApp).categoryRepository
        mealViewModel = ViewModelProvider(
            this,
            MealViewModelFactory(repository)
        )[MealsCategoriesViewModel::class.java]

        val supermarketRepository = (applicationContext as MyApp).supermarketRepository
        supermarketViewModel = ViewModelProvider(
            this,
            SupermarketViewModelFactory(supermarketRepository)
        )[SupermarketViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            MealsWithRoomTheme {
                Navigation(
                    navController = rememberNavController(),
                    mealViewModel = mealViewModel,
                    supermarketViewModel = supermarketViewModel)
            }
        }
    }
}