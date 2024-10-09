package com.example.lab7.ui.mealdetail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab7.networking.response.mealdetail.MealDetail
import com.example.lab7.networking.response.mealdetail.MealDetailResponse
import com.example.lab7.ui.mealdetail.repositories.MealDetailRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealDetailViewModel(private val repository: MealDetailRepository = MealDetailRepository()) : ViewModel() {

    private val _mealDetail = MutableLiveData<MealDetail>()
    val mealDetail: LiveData<MealDetail> = _mealDetail

    fun loadMealDetail(mealId: String) {
        viewModelScope.launch {
            try {
                // llamada del repositorio
                val response = repository.getMealDetail(mealId)

                response.enqueue(object : Callback<MealDetailResponse> {
                    override fun onResponse(
                        call: Call<MealDetailResponse>,
                        response: Response<MealDetailResponse>
                    ) {
                        if (response.isSuccessful) {
                            // obtener MealDetail del response
                            _mealDetail.value = response.body()?.meals?.firstOrNull() // en el caso de que este vacio
                        } else {
                            Log.e("MealDetailViewModel", "Error: ${response.errorBody()?.string()}")
                        }
                    }
                    override fun onFailure(call: Call<MealDetailResponse>, t: Throwable) {
                        Log.e("MealDetailViewModel", t.message.toString())
                    }
                })
            } catch (e: Exception) {
                Log.e("MealDetailViewModel", e.message.toString())
            }
        }
    }
}
