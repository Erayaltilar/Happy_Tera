package com.example.android_training.presentation.chronometer.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_training.R
import com.example.android_training.presentation.ui.theme.Dimen

@SuppressLint("DefaultLocale")
@Composable
fun ChronometerScreen(
    viewModel: ChronometerViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        ) {
        Text(
            text = String.format(uiState.formattedTime),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.background,
        )

        Spacer(modifier = Modifier.height(Dimen.spacing_m1))

        Button(
            onClick = { viewModel.startChronometer() },
            shape = RoundedCornerShape(Dimen.spacing_xs),
            colors = ButtonDefaults.buttonColors(Color.DarkGray),
            modifier = Modifier.width(Dimen.spacing_xxxxxl),
        ) {
            Text(
                text = stringResource(R.string.start),
                color = Color.White,
                fontSize = Dimen.font_size_l,
                fontWeight = FontWeight.Bold,
            )
        }

        Spacer(modifier = Modifier.height(Dimen.spacing_xxs))

        Button(
            onClick = { viewModel.pauseChronometer() },
            shape = RoundedCornerShape(Dimen.spacing_xs),
            colors = ButtonDefaults.buttonColors(Color.DarkGray),
            modifier = Modifier.width(Dimen.spacing_xxxxxl),
        ) {
            Text(
                text = stringResource(R.string.pause),
                color = Color.White,
                fontSize = Dimen.font_size_l,
                fontWeight = FontWeight.Bold,
            )
        }

        Spacer(modifier = Modifier.height(Dimen.spacing_xxs))

        Button(
            onClick = { viewModel.resetChronometer() },
            shape = RoundedCornerShape(Dimen.spacing_xs),
            colors = ButtonDefaults.buttonColors(Color.DarkGray),
            modifier = Modifier.width(Dimen.spacing_xxxxxl),
        ) {
            Text(
                text = stringResource(R.string.reset),
                color = Color.White,
                fontSize = Dimen.font_size_l,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}