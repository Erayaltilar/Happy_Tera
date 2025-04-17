package com.happy_tera.presentation.homepage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.happy_tera.domain.model.movie_model.Movie
import com.happy_tera.domain.model.series_model.Series
import com.happy_tera.presentation.homepage.movie.MovieAlbum
import com.happy_tera.presentation.homepage.series.SeriesAlbum
import com.happy_tera.presentation.ui.theme.Dimen
import com.happy_tera.R


@Composable
fun HomepageScreen(
    navController: NavController,
    viewModel: HomepageScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    with(uiState) {
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

        MovieHomepageUI(
            movies = discoverMovies,
            series = discoverSeries,
            navController = navController,
//            search = {
//                if (it.isEmpty()) {
//                    viewModel.getDiscoverMovies()
//                    viewModel.getDiscoverSeries()
//                } else {
//                    viewModel.getSearchSeries(it)
//                    viewModel.getSearchMovie(it)
//                }
//            },
        )
    }
}

@Composable
fun MovieHomepageUI(
    navController: NavController,
    movies: List<Movie>?,
    series: List<Series>?,
//    search: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .verticalScroll(rememberScrollState()),
    ) {
//        var query by remember { mutableStateOf("") }

//
//        OutlinedTextField(
//            value = query,
//            onValueChange = {
//                query = it
//                search(it)
//            },
//            label = { Text(text = "Search") },
//            modifier = Modifier.align(Alignment.CenterHorizontally),
//            singleLine = true,
//            maxLines = 1,
//            shape = RoundedCornerShape(Dimen.spacing_m1),
//            enabled = true,
//            textStyle = TextStyle( color = Color.White )
//        )

        Spacer(modifier = Modifier.height(Dimen.spacing_m1))

        Text(
            text = stringResource(R.string.movies),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = Dimen.font_size_22,
        )

        Spacer(modifier = Modifier.height(Dimen.spacing_m1))

        MovieAlbum(
            navController = navController,
            movies = movies ?: emptyList(),
        )

        Spacer(modifier = Modifier.height(Dimen.spacing_m1))

        Text(
            text = stringResource(R.string.tv_series),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = Dimen.font_size_22,
        )

        Spacer(modifier = Modifier.height(Dimen.spacing_m1))

        SeriesAlbum(
            navController = navController,
            series = series ?: emptyList(),
        )
    }
}
