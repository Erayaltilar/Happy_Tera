package com.example.android_training.presentation.movie.homepage

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun MovieHomepageScreen(
    navController: NavController,
    goToTheActivity: (activity: Activity) -> Unit,
) {
    Column {
        Text(text = "MOVIEHOMEPAGE")
    }
}