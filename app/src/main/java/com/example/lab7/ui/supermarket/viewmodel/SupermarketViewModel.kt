package com.example.lab7.ui.supermarket.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lab7.database.supermarket.SupermarketItemEntity
import com.example.lab7.ui.supermarket.repositories.SupermarketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.IOException

class SupermarketViewModel(private val supermarketRepository: SupermarketRepository) : ViewModel() {

    private val _supermarketItems = MutableLiveData<List<SupermarketItemEntity>>()
    val supermarketItems: LiveData<List<SupermarketItemEntity>> = _supermarketItems

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage


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

    fun deleteItem(path: String) {
        viewModelScope.launch {
            supermarketRepository.deleteItem(path)
        }
    }

    fun getItemById(itemId: String): List<SupermarketItemEntity> {
        return supermarketRepository.getItemById(itemId)
    }

    fun getAllItems() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val items = supermarketRepository.getAllItems()
                _supermarketItems.postValue(items)
            } catch (e: Exception) {
                handleException(e)
            } finally {
                _isLoading.postValue(false)
            }

        }
    }

    private fun handleException(exception: Exception) {
        when (exception) {
            is IOException -> _errorMessage.value = "Network error: Check your internet connection."
            else -> _errorMessage.value = "An unexpected error occurred."
        }
        // Optionally log the exception (e.g., using a logger or crash reporting tool)
        exception.printStackTrace()
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