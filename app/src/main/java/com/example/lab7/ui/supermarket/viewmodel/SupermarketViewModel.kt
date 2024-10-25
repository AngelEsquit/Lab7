package com.example.lab7.ui.supermarket.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lab7.database.supermarket.SupermarketItemEntity
import com.example.lab7.ui.supermarket.repositories.SupermarketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SupermarketViewModel(private val supermarketRepository: SupermarketRepository) : ViewModel() {

    private val _supermarketItems = MutableLiveData<List<SupermarketItemEntity>>()
    val supermarketItems: LiveData<List<SupermarketItemEntity>> = _supermarketItems


    fun insertItem(item: SupermarketItemEntity) {
        viewModelScope.launch {
            supermarketRepository.insertItem(item)
        }
    }

    fun updateItem(item: SupermarketItemEntity) {
        viewModelScope.launch {
            supermarketRepository.updateItem(item)
        }
    }

    fun deleteItem(imagePath: String) {
        viewModelScope.launch {
            supermarketRepository.deleteItem(imagePath)
        }
    }

    fun getItemById(itemId: String): LiveData<SupermarketItemEntity> {
        return supermarketRepository.getItemById(itemId)
    }

    fun getAllItems() {
        viewModelScope.launch {
            supermarketRepository.getAllItems()
            val items = supermarketRepository.getAllItems()
            _supermarketItems.postValue(items)
        }
    }
}

class SupermarketViewModelFactory(private val repository: SupermarketRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SupermarketViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SupermarketViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}