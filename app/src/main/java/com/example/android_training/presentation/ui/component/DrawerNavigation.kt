package com.example.android_training.presentation.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.android_training.R
import com.example.android_training.presentation.navigation.Screens
import com.example.android_training.presentation.ui.theme.Dimen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class NavigationItem(
    val title: String,
    val selectedIcon: Painter,
    val unselectedIcon: Painter,
    val badgeCount: Int? = null,
    val navigateToScreen: Screens,
)

@Composable
fun CustomDrawerNavigation(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    scope: CoroutineScope,
    drawerState: DrawerState,
    navHost: @Composable () -> Unit = { /* sonar - comment */ },
) {
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val items = listOf(
        NavigationItem(
            title = stringResource(R.string.advice),
            selectedIcon = painterResource(id = R.drawable.ic_advice),
            unselectedIcon = painterResource(id = R.drawable.ic_black_advice),
            navigateToScreen = Screens.AdviceHomepageScreen,
        ),
        NavigationItem(
            title = stringResource(R.string.movie),
            selectedIcon = painterResource(id = R.drawable.ic_black_clapperboard),
            unselectedIcon = painterResource(id = R.drawable.ic_clapperboard),
            navigateToScreen = Screens.HomepageScreen,
        ),
        NavigationItem(
            title = stringResource(R.string.chronometer),
            selectedIcon = painterResource(id = R.drawable.ic_chronometer),
            unselectedIcon = painterResource(id = R.drawable.ic_chronometer),
            navigateToScreen = Screens.ChronometerScreen,
        ),
    )


    ModalNavigationDrawer(

        drawerContent = {
            ModalDrawerSheet(drawerContainerColor = Color.DarkGray) {

                Spacer(modifier = modifier)

                items.forEachIndexed { index, item ->

                    Spacer(modifier = Modifier.height(Dimen.spacing_m1))

                    NavigationDrawerItem(
                        label = { Text(text = item.title) },
                        selected = index == selectedItemIndex,
                        onClick = {
                            selectedItemIndex = index
                            scope.launch {
                                navController.navigate(item.navigateToScreen)
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                painter = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                modifier = Modifier.height(Dimen.spacing_xl),
                                contentDescription = item.title,

                            )
                        },
                        badge = {
                            item.badgeCount?.let {
                                Text(text = it.toString())
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    )
                }
            }
        },
        drawerState = drawerState,
    ) {
        navHost()
    }
}
