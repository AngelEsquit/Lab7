package com.example.lab7.ui.supermarket.repositories

import androidx.lifecycle.LiveData
import com.example.lab7.database.supermarket.SupermarketItemDao
import com.example.lab7.database.supermarket.SupermarketItemEntity
import kotlinx.coroutines.flow.Flow

class SupermarketRepository(private val supermarketItemDao: SupermarketItemDao) {

    fun getAllItems(): List<SupermarketItemEntity> = supermarketItemDao.getAllItems()

    suspend fun insertItem(item: SupermarketItemEntity) {
        supermarketItemDao.insert(item)
    }

    suspend fun updateItem(item: SupermarketItemEntity) {
        supermarketItemDao.update(item)
    }

    suspend fun deleteItem(imagePath: String) {
        supermarketItemDao.delete(imagePath)
    }

    fun getItemById(id: String): LiveData<SupermarketItemEntity> = supermarketItemDao.getItemById(id)
}