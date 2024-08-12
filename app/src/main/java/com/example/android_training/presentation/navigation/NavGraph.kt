package com.example.android_training.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.android_training.presentation.advice.homepage.AdviceHomepageScreen
import com.example.android_training.presentation.chronometer.screen.ChronometerScreen
import com.example.android_training.presentation.detail.movie_detail.MovieDetailScreen
import com.example.android_training.presentation.detail.series_detail.SeriesDetailScreen
import com.example.android_training.presentation.homepage.HomepageScreen
import com.example.android_training.presentation.similar.movies.SimilarMoviesScreen
import com.example.android_training.presentation.similar.series.SimilarSeriesScreen

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

        composable<Screens.HomepageScreen> {
            HomepageScreen(navController = navController)
        }

        composable<Screens.MovieDetailScreen> {
            val movieId = it.arguments?.getLong("movieId")
            MovieDetailScreen(
                movieId = movieId ?: -1,
                navController = navController,
            )
        }

        composable<Screens.SimilarMoviesScreen> {
            val movieId = it.arguments?.getLong("movieId")
            SimilarMoviesScreen(
                movieId = movieId ?: -1,
                navController = navController,
            )
        }

        composable<Screens.SimilarSeriesScreen> {
            val seriesId = it.arguments?.getLong("seriesId")
            SimilarSeriesScreen(
                seriesId = seriesId ?: -1,
                navController = navController,
            )
        }

        composable<Screens.SeriesDetailScreen> {
            val seriesId = it.arguments?.getLong("seriesId")
            SeriesDetailScreen(
                seriesId = seriesId ?: -1,
                navController = navController,
            )
        }

        composable<Screens.ChronometerScreen> {
            ChronometerScreen()
        }

    }
}
