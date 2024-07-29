package com.example.android_training.presentation.homepage.series

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.android_training.domain.model.series_model.Series
import com.example.android_training.ui.component.ScrollToTopButton
import com.example.android_training.ui.theme.Dimen
import kotlinx.coroutines.launch

@Composable
fun SeriesAlbum(
    navController: NavController,
    series: List<Series>,
) {

    val state = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val showScrollToTopButton by remember {
        derivedStateOf {
            state.firstVisibleItemIndex > 0
        }
    }

    Box {
        LazyRow(
            state = state
        ) {
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
        if (showScrollToTopButton)
            ScrollToTopButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = {
                    coroutineScope.launch {
                        state.scrollToItem(
                            index = 0
                        )
                    }
                }
            )
    }
}