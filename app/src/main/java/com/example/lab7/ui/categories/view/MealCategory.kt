package com.example.lab7.ui.categories.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.lab7.database.categories.MealCategoryEntity
import com.example.lab7.navigation.NavigationState
import com.example.lab7.navigation.navigateTo

@Composable
fun MealCategory(meal: MealCategoryEntity, navController: NavController) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(meal.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp).clickable {
                        meal.name?.let {
                            navigateTo(
                                navController,
                                NavigationState.MealsRecipesList.createRoute(it)
                            )
                        }
                    }
            ) {
                Text(
                    text = meal.name ?: "",
                    style = MaterialTheme.typography.bodySmall
                )
                HorizontalDivider()
                Text(
                    text = meal.description ?: "",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}