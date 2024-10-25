package com.example.lab7.database.supermarket

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
<<<<<<< Updated upstream
import kotlinx.coroutines.flow.Flow
=======
import okio.Path
>>>>>>> Stashed changes

@Dao
interface SupermarketItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: SupermarketItemEntity)

    @Update
    suspend fun update(item: SupermarketItemEntity)

    @Query("DELETE FROM supermarket_items WHERE imagePath = :imagePath")
    suspend fun delete(imagePath: String)

    @Query("SELECT * FROM supermarket_items")
    fun getAllItems(): List<SupermarketItemEntity>

    @Query("SELECT * FROM supermarket_items WHERE id = :id")
    fun getItemById(id: String): LiveData<SupermarketItemEntity>
}
