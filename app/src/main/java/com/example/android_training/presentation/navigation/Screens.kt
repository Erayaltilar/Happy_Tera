package com.example.android_training.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {

    @Serializable
    data object AdviceHomepageScreen : Screens()

    @Serializable
    data object MovieHomepageScreen : Screens()
}