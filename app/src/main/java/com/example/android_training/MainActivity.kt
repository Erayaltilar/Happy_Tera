package com.example.android_training

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.android_training.presentation.advice.homepage.AdviceHomepageScreen
import com.example.android_training.presentation.movie.homepage.MovieHomepageScreen
import com.example.android_training.ui.theme.AndroidTrainingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTrainingTheme {
                val navController = rememberNavController()

                MovieHomepageScreen(navController = navController)
            }
        }
    }
}
