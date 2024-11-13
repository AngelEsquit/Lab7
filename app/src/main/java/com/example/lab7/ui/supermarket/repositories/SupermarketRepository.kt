package com.example.lab7.ui.supermarket.repositories

import androidx.lifecycle.LiveData
import com.example.lab7.database.supermarket.SupermarketItemDao
import com.example.lab7.database.supermarket.SupermarketItemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class SupermarketRepository(private val supermarketItemDao: SupermarketItemDao) {

    suspend fun getAllItems(): List<SupermarketItemEntity> = withContext(Dispatchers.IO) {
        supermarketItemDao.getAllItems()
    }

    suspend fun insertItem(item: SupermarketItemEntity) {
        supermarketItemDao.insert(item)
    }

    suspend fun updateItem(item: SupermarketItemEntity) {
        supermarketItemDao.update(item)
        getAllItems()
    }

    suspend fun deleteItem(path: String) {
        supermarketItemDao.delete(path)
        getAllItems()
    }

    fun getItemByAtributes(itemName: String, quantity: String, imagePath: String): SupermarketItemEntity {
        return supermarketItemDao.getItemByAtributes(itemName, quantity, imagePath)
    }
}