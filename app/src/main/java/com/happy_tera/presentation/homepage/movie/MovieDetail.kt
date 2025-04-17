package com.happy_tera.presentation.homepage.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.happy_tera.R
import com.happy_tera.domain.model.movie_model.Movie
import com.happy_tera.presentation.navigation.Screens
import com.happy_tera.presentation.ui.theme.Dimen


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
        ),

    ) {
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
