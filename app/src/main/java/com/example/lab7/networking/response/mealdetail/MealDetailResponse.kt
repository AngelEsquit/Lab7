package com.example.lab7.networking.response.mealdetail

import com.google.gson.annotations.SerializedName

// Respuesta para los detalles de una receta específica
data class MealDetailResponse(
    @SerializedName("meals") val meals: List<MealDetail>
)