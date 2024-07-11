package com.example.android_training.presentation.homepage

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_training.domain.model.Food

@Composable
fun HomepageScreen(
    viewModel: HomepageViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    with(uiState) {
        if (loadingState) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            return@with
        }
        if (isSuccess) {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
        if (isHaveError) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }
        if (isSearchLoading) {
            Toast.makeText(context, "Search Loading", Toast.LENGTH_SHORT).show()
        }
        if (isSearchSuccess) {
            Toast.makeText(context, "Search Success", Toast.LENGTH_SHORT).show()
        }
        if (isSearchError) {
            Toast.makeText(context, "Search Error", Toast.LENGTH_SHORT).show()
        }

        HomepageScreenUI(
            foods = foodList,
            searchFood = { viewModel.getFoodByName(it) }
        )
    }
}

@Composable
fun HomepageScreenUI(
    foods: List<Food>,
    searchFood: (String) -> Unit = {},
) {
    var query by remember { mutableStateOf("") }
    val searchedFoods = foods.filter {
        it.description?.contains(query, ignoreCase = true) == true
    }

    Column {

        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                searchFood(it)
            },
            label = { Text("Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, top = 8.dp),
        )

        Text(text = "FOOD LIST: ")

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(searchedFoods.size) { productIndex ->
                val food = searchedFoods[productIndex]
                FoodItem(food = food)
            }
        }
    }
}

@Composable
fun FoodItem(
    food: Food,
) {
    Text(text = food.description ?: "")
}