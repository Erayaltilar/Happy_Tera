@file:JvmName("SimilarSeriesScreenViewModelKt")

package com.example.happy_tera.presentation.similar.series

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
import com.example.happy_tera.domain.model.series_model.Series
import com.example.happy_tera.presentation.navigation.Screens
import com.example.happy_tera.presentation.ui.theme.Dimen

@Composable
fun SimilarSeriesScreen(
    navController: NavController = rememberNavController(),
    seriesId: Long? = null,
    viewModel: SimilarSeriesViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    with(uiState) {

        viewModel.getSimilarSeries(seriesId = seriesId ?: -1)

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
            series = series,
        )
    }
}

@Composable
fun SimilarMoviesScreenUI(
    navController: NavController,
    series: List<Series> = emptyList(),
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {

        Text(
            text = stringResource(R.string.similar_series),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.LightGray,
            fontWeight = FontWeight.Bold,
            fontSize = Dimen.font_size_xxl,
        )

        Spacer(modifier = Modifier.height(Dimen.spacing_l))

        LazyColumn {
            if (series.isEmpty()) {
                item {
                    Text(
                        text = "No similar Series in these genres",
                        modifier = Modifier.padding(horizontal = Dimen.spacing_l),
                        color = Color.LightGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = Dimen.font_size_22,
                    )
                }
            }

            series.forEach { series ->
                item {
                    Box(
                        modifier = Modifier.clickable(
                            onClick = {
                                navController.navigate(
                                    Screens.SeriesDetailScreen(
                                        seriesId = series.id,
                                    ),
                                )
                            }
                        ),
                    ) {
                        Column {

                            val imageUrl = stringResource(R.string.https_image_tmdb_org_t_p_w500, series.poster_path ?: "")

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
                                    text = series.name ?: "",
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = Dimen.font_size_l,
                                )

                                Spacer(modifier = Modifier.height(Dimen.spacing_s1))

                                Text(
                                    text = series.overview ?: "",
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