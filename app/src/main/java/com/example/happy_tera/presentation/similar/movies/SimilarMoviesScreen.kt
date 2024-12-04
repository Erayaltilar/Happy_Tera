package com.example.happy_tera.presentation.similar.movies

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
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
import com.example.happy_tera.R
import com.example.happy_tera.domain.model.movie_model.Movie
import com.example.happy_tera.presentation.navigation.Screens
import com.example.happy_tera.presentation.ui.theme.Dimen

@Composable
fun SimilarMoviesScreen(
    navController: NavController = rememberNavController(),
    movieId: Long? = null,
    viewModel: SimilarMoviesViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    with(uiState) {

        viewModel.getSimilarMovies(movieId = movieId ?: -1)

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
        if (isHaveError) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }
        if (isSuccess) {
            /* sonar - comment */
        }
        SimilarMoviesScreenUI(
            navController = navController,
            movies = movies,
        )
    }
}

@Composable
fun SimilarMoviesScreenUI(
    navController: NavController,
    movies: List<Movie> = emptyList(),
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {

        Text(
            text = stringResource(R.string.similar_movies),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.LightGray,
            fontWeight = FontWeight.Bold,
            fontSize = Dimen.font_size_xxl,
        )

        Spacer(modifier = Modifier.height(Dimen.spacing_l))

        LazyColumn {
            movies.forEach { movie ->
                item {
                    Spacer(modifier = Modifier.height(Dimen.spacing_l))

                    Box(
                        modifier = Modifier.clickable(
                            onClick = {
                                navController.navigate(
                                    Screens.MovieDetailScreen(
                                        movieId = movie.id,
                                    ),
                                )
                            }
                        ),
                    ) {
                        Column {

                            val imageUrl = stringResource(R.string.https_image_tmdb_org_t_p_w500, movie.poster_path ?: "")

                            val painter: Painter = rememberAsyncImagePainter(imageUrl)


                            Image(
                                painter = painter,
                                contentDescription = stringResource(R.string.movie_poster),
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(horizontal = Dimen.spacing_m1)
                                    .height(Dimen.spacing_xxxxxl),
                            )

                            Column {

                                Text(
                                    text = movie.title ?: "",
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = Dimen.font_size_l,
                                )

                                Spacer(modifier = Modifier.height(Dimen.spacing_s1))

                                Text(
                                    text = movie.overview ?: "",
                                    color = Color.White,
                                    fontSize = Dimen.font_size_15,
                                )

                                Spacer(modifier = Modifier.height(Dimen.spacing_s1))
                            }
                        }
                    }

                    HorizontalDivider()
                }
            }
        }
    }
}