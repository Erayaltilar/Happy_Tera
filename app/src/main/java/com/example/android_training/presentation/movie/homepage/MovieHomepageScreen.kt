package com.example.android_training.presentation.movie.homepage

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.android_training.domain.model.movie_model.Movie

@Composable
fun MovieHomepageScreen(
    navController: NavController,
    viewModel: MovieHomepageViewModel = hiltViewModel(),
    goToTheActivity: (activity: Activity) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    with(uiState) {
        if (loadingState) {
            Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
        }
        if (isSuccess) {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
        if (isHaveError) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }

        MovieHomepageUI(
            movies = randomMovies,
        )
    }
}

@Composable
fun MovieHomepageUI(movies: List<Movie>?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
    ) {
        LazyColumn {
            movies?.size?.let {
                items(it) { count ->
                    Text(
                        text = movies[count].name,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.background(color = Color.Black),
                    )
                }
            }
        }
    }
}