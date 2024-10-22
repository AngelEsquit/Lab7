package com.example.lab7

import android.app.Application
import androidx.room.Room
import com.example.lab7.database.AppDatabase
import com.example.lab7.networking.webservices.MealsWebService
import com.example.lab7.ui.categories.repositories.MealsCategoryRepository
import com.example.lab7.ui.supermarket.repositories.SupermarketRepository

class MyApp : Application() {

    // Singleton instance of the Room database
    private lateinit var database: AppDatabase
        private set

    lateinit var categoryRepository: MealsCategoryRepository
        private set

    lateinit var categoryWebService: MealsWebService
        private set

    lateinit var supermarketRepository: SupermarketRepository
        private set

    override fun onCreate() {
        super.onCreate()

        categoryWebService = MealsWebService()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "lab7-database"
        ).build()

        categoryRepository = MealsCategoryRepository(
            categoryWebService,
            database.mealCategoryDao()
        )

        supermarketRepository = SupermarketRepository(
            database.supermarketItemDao()
        )
    }
}