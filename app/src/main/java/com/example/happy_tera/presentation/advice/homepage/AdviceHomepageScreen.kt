package com.example.happy_tera.presentation.advice.homepage

import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.happy_tera.R
import com.example.happy_tera.domain.model.message_model.Message
import com.example.happy_tera.presentation.ui.theme.Dimen

@Composable
fun AdviceHomepageScreen(
    navController: NavController,
    viewModel: AdviceHomepageViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    with(uiState) {
        if (loadingState) {
            AdviceHomepageScreenUI(loading = true, onNewAdviceButtonClicked = {})
        }

        if (isSuccess) {
            /* sonar - comment */
        }

        if (isHaveError) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }

        AdviceHomepageScreenUI(
            message = randomMessage,
            onNewAdviceButtonClicked = {
                viewModel.getRandomMessage()
            },
        )
    }
}

@Composable
fun AdviceHomepageScreenUI(
    message: Message? = null,
    loading: Boolean = false,
    onNewAdviceButtonClicked: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (loading) {
            Text(
                text = stringResource(R.string.loading),
                color = Color.White,
                fontSize = Dimen.font_size_l,
                fontWeight = FontWeight.Bold,
            )
        } else {
            Text(
                text = message?.slip?.advice ?: "",
                color = Color.White,
                fontSize = Dimen.font_size_l,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.height(Dimen.spacing_xxxl))

        Button(
            onClick = onNewAdviceButtonClicked,
            shape = RoundedCornerShape(Dimen.spacing_xs),
            colors = ButtonDefaults.buttonColors(Color.DarkGray),
            modifier = Modifier.width(Dimen.spacing_xxxxxl),
        ) {
            Text(
                text = stringResource(R.string.get_advice),
                color = Color.White,
                fontSize = Dimen.font_size_l,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}