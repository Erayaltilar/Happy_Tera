package com.example.android_training.presentation.detail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.android_training.R
import com.example.android_training.domain.model.movie_model.Movie
import com.example.android_training.ui.theme.Dimen

@Composable
fun MovieDetailScreen(
    navController: NavController,
    movieId: Long,
    viewModel: DetailScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    with(uiState) {

        viewModel.getMovieDetail(movieId)

        if (loadingState) {
            Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
        }

        if (isSuccess) {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }

        if (isHaveError) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }

        MovieDetailScreenUI(
            movie = movie,
        )
    }

}

@Composable
fun MovieDetailScreenUI(
    movie: Movie?,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top = Dimen.spacing_xxxl),
    ) {
        val imageUrl = stringResource(R.string.https_image_tmdb_org_t_p_w500, movie?.poster_path ?: "")

        val painter: Painter = rememberAsyncImagePainter(imageUrl)

        Image(
            painter = painter,
            contentDescription = stringResource(R.string.movie_poster),
            modifier = Modifier
                .width(Dimen.spacing_xxxxxl)
                .height(Dimen.spacing_xxxxxxl)
                .align(Alignment.CenterHorizontally),
        )

        Text(
            text = movie?.title ?: "aaa",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(Dimen.spacing_m1))

        Text(
            text = "Description",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = Dimen.font_size_l,
        )

        Spacer(modifier = Modifier.height(Dimen.spacing_m1))

        Text(
            text = movie?.overview ?: "bbb",
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
    }
}