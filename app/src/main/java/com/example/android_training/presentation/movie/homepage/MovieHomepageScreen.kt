package com.example.android_training.presentation.movie.homepage

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
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
import com.example.android_training.presentation.navigation.Screens
import com.example.android_training.ui.theme.Dimen

@Composable
fun MovieHomepageScreen(
    navController: NavController,
    viewModel: MovieHomepageViewModel = hiltViewModel(),
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
            movies = discoverMovies,
            navController = navController,
        )
    }
}

@Composable
fun MovieHomepageUI(
    navController: NavController,
    movies: List<Movie>?,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(top = Dimen.spacing_xxxl),
    ) {

        Text(
            text = stringResource(R.string.discover),
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )

        MainMovieAlbum(
            navController = navController, movies = movies ?: emptyList()
        )

        Spacer(modifier = Modifier.height(Dimen.spacing_m1))

        Text(
            text = "Random Movies",
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )


    }
}

@Composable
fun MovieDetail(
    navController: NavController,
    movie: Movie,
) {
    Box(
        modifier = Modifier.clickable(
            onClick = {
                navController.navigate(
                    Screens.MovieDetailScreen(
                        movieId = movie.id,
                    ),
                )
            }
        )) {
        Column {

            val imageUrl = stringResource(R.string.https_image_tmdb_org_t_p_w500, movie.poster_path ?: "")

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
                text = movie.title ?: "",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color.Magenta,
                fontWeight = FontWeight.Bold,
            )
        }
    }

    Spacer(modifier = Modifier.height(Dimen.spacing_m1))
}

@Composable
fun MainMovieAlbum(
    navController: NavController,
    movies: List<Movie>,
) {
    LazyRow {
        movies.forEach { movie ->
            item {
                MovieDetail(
                    navController = navController,
                    movie = movie,
                )

                Spacer(modifier = Modifier.width(Dimen.spacing_m1))

                VerticalDivider(
                    modifier = Modifier
                        .width(Dimen.spacing_m1)
                        .height(Dimen.spacing_xxxxxxl),
                )
            }
        }
    }
}