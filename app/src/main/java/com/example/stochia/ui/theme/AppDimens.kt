package com.example.stochia.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalWindowInfo

val LocalDimens = staticCompositionLocalOf { AppDimens() }

data class AppDimens(
    val commitButton: Dp = 50.dp,
    val editTextHeight: Dp = 60.dp
)


@Composable
fun responsiveDimens(): AppDimens {
    val widthDp = LocalWindowInfo.current.containerSize.width

    return when {
        widthDp < 360 -> AppDimens(
           commitButton = 40.dp,
            editTextHeight = 40.dp
        )

        widthDp < 600 -> AppDimens(
            commitButton = 50.dp,
            editTextHeight = 50.dp
        )

        else -> AppDimens(
            commitButton = 60.dp,
            editTextHeight = 60.dp
        )
    }
}
