package com.example.lab7.navigation

import androidx.navigation.NavController

fun navigateTo(navController: NavController, route: String, popUpToRoute: String? = null) {
    navController.navigate(route) {
        launchSingleTop = true // Evita que se creen múltiples instancias de la misma pantalla
        popUpToRoute?.let {
            popUpTo(it) { inclusive = true } // Elimina todas las pantallas hasta la ruta especificada, incluyendo esa pantalla
        }
    }
}