package com.example.lab7.ui.categories.repositories

import com.example.lab7.database.categories.MealCategoryDao
import com.example.lab7.database.categories.MealCategoryEntity
import com.example.lab7.networking.response.categories.toEntity
import com.example.lab7.networking.webservices.IMealsWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealsCategoryRepository(private val webService: IMealsWebService,
                              private val mealCategoryDao: MealCategoryDao
) {
    suspend fun getMealsCategories(): List<MealCategoryEntity> {
        return try {
            val entities = webService.getMealsCategories().categories
            val content = entities.map { it.toEntity() }
            mealCategoryDao.insertAll(content)
            mealCategoryDao.getAllMealCategories()
        } catch (e: Exception) {
            withContext(Dispatchers.Main) { // Cambiar al hilo principal para actualizar LiveData
                // Actualizar LiveData con un mensaje de error o una lista vacía
            }
            emptyList() // Devolver una lista vacía en caso de error
        }
    }
}