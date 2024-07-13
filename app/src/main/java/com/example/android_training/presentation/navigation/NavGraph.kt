package com.example.android_training.presentation.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.android_training.presentation.advice.homepage.AdviceHomepageScreen
import com.example.android_training.presentation.movie.homepage.MovieHomepageScreen

@Composable
fun NavGraph(navController: NavHostController, goToTheActivity: (activity: Activity) -> Unit) {
    NavHost(
        navController = navController, startDestination = Screens.AdviceHomepageScreen
    ) {
        composable<Screens.AdviceHomepageScreen> {
            AdviceHomepageScreen(navController = navController) {
                goToTheActivity(it)
            }
        }

        composable<Screens.MovieHomepageScreen> {
            MovieHomepageScreen(navController = navController) {
                goToTheActivity(it)
            }
        }
    }
}
