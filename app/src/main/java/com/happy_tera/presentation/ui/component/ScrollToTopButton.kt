package com.happy_tera.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.happy_tera.R
import com.happy_tera.presentation.ui.theme.Dimen


@Composable
fun ScrollToTopButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Image(
        modifier = modifier
            .clickable { onClick() }
            .background(shape = RoundedCornerShape(Dimen.spacing_m1), color = Color.White),
        painter = painterResource(id = R.drawable.ic_left_arrow),
        contentDescription = stringResource(R.string.scroll_to_top),
    )
}