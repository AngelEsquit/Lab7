package com.example.lab7.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lab7.database.categories.MealCategoryDao
import com.example.lab7.database.categories.MealCategoryEntity
import com.example.lab7.database.supermarket.SupermarketItemDao
import com.example.lab7.database.supermarket.SupermarketItemEntity

@Database(
    entities = [MealCategoryEntity::class,
    SupermarketItemEntity::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mealCategoryDao(): MealCategoryDao
    abstract fun supermarketItemDao(): SupermarketItemDao
}