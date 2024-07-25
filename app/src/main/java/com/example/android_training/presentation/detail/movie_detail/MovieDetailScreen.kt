package com.example.android_training.presentation.detail.movie_detail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.android_training.R
import com.example.android_training.domain.model.movie_model.Movie
import com.example.android_training.presentation.navigation.Screens
import com.example.android_training.ui.theme.Dimen

@Composable
fun MovieDetailScreen(
    navController: NavController = rememberNavController(),
    movieId: Long? = null,
    viewModel: MovieDetailScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    with(uiState) {
        viewModel.getMovieDetail(movieId = movieId ?: -1)

        if (loadingState) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Loading...",
                    color = Color.White,
                )
            }
        }

        if (isSuccess) {
            /* sonar - comment */
        }

        if (isHaveError) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }

        DetailScreenUI(
            navController = navController,
            movie = movie,
        )
    }

}

@Composable
private fun DetailScreenUI(
    movie: Movie? = null,
    navController: NavController,
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
            text = movie?.title ?: "",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            fontSize = Dimen.font_size_l,
        )

        Spacer(modifier = Modifier.height(Dimen.spacing_m1))

        Text(
            text = stringResource(R.string.description),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = Dimen.font_size_l,
        )

        Spacer(modifier = Modifier.height(Dimen.spacing_m1))

        Text(
            text = movie?.overview ?: "",
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(Dimen.spacing_m1))

        Text(
            text = stringResource(R.string.genres),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = Dimen.font_size_l,
        )

        Spacer(modifier = Modifier.height(Dimen.spacing_m1))

        LazyRow {
            items(movie?.genres?.size ?: 0) {
                Card(
                    colors = CardDefaults.cardColors(Color.DarkGray),
                    shape = RoundedCornerShape(Dimen.spacing_l),
                ) {
                    Text(
                        text = movie?.genres?.get(it)?.name ?: "Can not found movie Detail",
                        modifier = Modifier.padding(horizontal = Dimen.spacing_m1, vertical = Dimen.spacing_xxs),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Spacer(modifier = Modifier.width(Dimen.spacing_s2))
            }
        }

        Spacer(modifier = Modifier.height(Dimen.spacing_m1))

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(Dimen.spacing_l),
            colors = ButtonDefaults.buttonColors(Color.DarkGray),
            onClick = {
                navController.navigate(
                    Screens.SimilarMoviesScreen(
                        movieId = movie?.id ?: -1,
                    ),
                )
            }
        ) {
            Text(
                text = stringResource(R.string.see_similar_movies),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = Dimen.font_size_l,
            )
        }
    }
}