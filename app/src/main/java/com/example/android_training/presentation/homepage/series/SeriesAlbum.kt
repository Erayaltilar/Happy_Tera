package com.example.android_training.presentation.homepage.series

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.android_training.domain.model.series_model.Series
import com.example.android_training.ui.theme.Dimen

@Composable
fun SeriesAlbum(
    navController: NavController,
    series: List<Series>,
) {
    LazyRow {
        series.forEach { series ->
            item {
                SeriesDetail(
                    navController = navController,
                    series = series,
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