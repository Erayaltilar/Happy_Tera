package com.example.android_training.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.android_training.presentation.advice.homepage.AdviceHomepageScreen
import com.example.android_training.presentation.detail.MovieDetailScreen
import com.example.android_training.presentation.movie.homepage.MovieHomepageScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.AdviceHomepageScreen,
    ) {
        composable<Screens.AdviceHomepageScreen> {
            AdviceHomepageScreen(navController = navController)
        }

        composable<Screens.MovieHomepageScreen> {
            MovieHomepageScreen(navController = navController)
        }

        composable<Screens.MovieDetailScreen> {
            val movieId = it.arguments?.getLong("movieId")
            MovieDetailScreen(
                movieId = movieId ?: -1,
                navController = navController
            )
        }
    }
}
