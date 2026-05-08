package com.example.stochia.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val LocalDimens = staticCompositionLocalOf { AppDimens() }

data class AppDimens(

    // ════════════════════════════════════════════════════
    // TIPOGRAFÍA — tamaños de fuente
    // ════════════════════════════════════════════════════
    val fontSizeHeadlineLarge: TextUnit = 20.sp,
    val fontSizeBodyLarge: TextUnit = 16.sp,
    val fontSizeLabelSmall: TextUnit = 11.sp,

    // ════════════════════════════════════════════════════
    // TIPOGRAFÍA — alturas de línea
    // ════════════════════════════════════════════════════
    val lineHeightHeadlineLarge: TextUnit = 28.sp,
    val lineHeightBodyLarge: TextUnit = 20.sp,
    val lineHeightLabelSmall: TextUnit = 16.sp,

    // ════════════════════════════════════════════════════
    // GLOBAL — esquinas y elevación
    // ════════════════════════════════════════════════════
    val cornerRadiusSmall: Dp = 5.dp,
    val cornerRadiusMedium: Dp = 10.dp,
    val cardElevation: Dp = 6.dp,

    // ════════════════════════════════════════════════════
    // GLOBAL — sombras internas
    // ════════════════════════════════════════════════════
    val resultCardShadowRadius: Dp = 8.dp,
    val resultCardShadowSpread: Dp = 1.dp,
    val editTextShadowRadius: Dp = 10.dp,
    val editTextShadowSpread: Dp = 2.dp,
    val dropdownMenuShadowRadius: Dp = 10.dp,
    val dropdownMenuShadowSpread: Dp = 5.dp,

    // ════════════════════════════════════════════════════
    // GLOBAL — padding interno de cards y pantalla
    // ════════════════════════════════════════════════════
    val cardInnerPadding: Dp = 10.dp,
    val screenPadding: Dp = 16.dp,

    // ════════════════════════════════════════════════════
    // GLOBAL — espaciadores
    // ════════════════════════════════════════════════════
    val spacerXSmall: Dp = 16.dp,
    val spacerSmall: Dp = 20.dp,
    val spacerMedium: Dp = 30.dp,
    val spacerLarge: Dp = 40.dp,
    val spacerXLarge: Dp = 50.dp,

    // ════════════════════════════════════════════════════
    // MAIN SCREEN — botón de commit y edit text (ya existentes)
    // ════════════════════════════════════════════════════
    val commitButton: Dp = 50.dp,
    val editTextHeight: Dp = 60.dp,

    // ════════════════════════════════════════════════════
    // NAVIGATION — BottomBarButton y SettingsButton
    // ════════════════════════════════════════════════════
    val navBarHeight: Dp = 80.dp,
    val navBarHeightLandscape: Dp = 56.dp,
    val navButtonHeight: Dp = 80.dp,
    val navButtonWidth: Dp = 100.dp,
    val navButtonHeightLandscape: Dp = 48.dp,
    val navButtonWidthLandscape: Dp = 80.dp,
    val navButtonsMaxWidth: Dp = 480.dp,
    val navButtonIconBlur: Dp = 4.dp,
    val navButtonShadowTarget: Dp = 7.dp,
    val settingsButtonIconBlur: Dp = 3.dp,
    val settingsButtonShadowTarget: Dp = 5.dp,

    // ════════════════════════════════════════════════════
    // RESULT SCREEN — carrusel de estudios previos
    // ════════════════════════════════════════════════════
    val resultScreenStudyCarouselHeight: Dp = 200.dp,

    // ════════════════════════════════════════════════════
    // DISTRIBUTION FORM
    // ════════════════════════════════════════════════════
    val distributionFormDataInputHeight: Dp = 200.dp,

    // ════════════════════════════════════════════════════
    // DISTRIBUTION RESULT SCREEN
    // ════════════════════════════════════════════════════
    val distributionResultStatCardHeight: Dp = 80.dp,

    // ════════════════════════════════════════════════════
    // MARKOV FORM
    // ════════════════════════════════════════════════════
    val markovFormMatrixRowVerticalPadding: Dp = 3.dp,

    // ════════════════════════════════════════════════════
    // MARKOV RESULT SCREEN
    // ════════════════════════════════════════════════════
    val markovResultSummaryCardHeight: Dp = 350.dp,
    val markovResultPathListHeight: Dp = 400.dp,
    val markovResultPathItemHeight: Dp = 50.dp,

    // ════════════════════════════════════════════════════
    // MONTECARLO RESULT SCREEN — compartidos
    // ════════════════════════════════════════════════════
    val montecarloResultCardHeight: Dp = 700.dp,
    val montecarloResultListHeight: Dp = 400.dp,
    val montecarloResultSimulationItemHeight: Dp = 50.dp,

    // ════════════════════════════════════════════════════
    // GEOMETRICAL MONTECARLO RESULT
    // ════════════════════════════════════════════════════
    val geometricalMontecarloResultCardHeight: Dp = 400.dp,

    // ════════════════════════════════════════════════════
    // MULTINOMIAL MONTECARLO RESULT
    // ════════════════════════════════════════════════════
    val multinomialMontecarloResultCardHeight: Dp = 650.dp,
    val multinomialMontecarloResultSliderRowHeight: Dp = 100.dp,
    val multinomialMontecarloResultParamLabelPaddingStart: Dp = 10.dp,

    // ════════════════════════════════════════════════════
    // SLIDERS — CustomSlider, CustomTrack, CustomStaticTrack, GlowCustomStaticTrack
    // ════════════════════════════════════════════════════
    val sliderTrackHeight: Dp = 10.dp,
    val sliderTrackPadding: Dp = 10.dp,
    val sliderCustomTrackHeight: Dp = 2.dp,
    val sliderStaticTrackHeight: Dp = 14.dp,
    val sliderStaticTrackPaddingTop: Dp = 10.dp,
    val sliderStaticTrackPaddingHorizontal: Dp = 5.dp,
    val sliderGlowBlurRadius: Dp = 5.dp,
)


@Composable
fun responsiveDimens(): AppDimens {
    val widthPx = LocalWindowInfo.current.containerSize.width

    return when {

        // ── Pantallas pequeñas / dispositivos compactos ──
        widthPx < 360 -> AppDimens(
            // Tipografía
            fontSizeHeadlineLarge = 17.sp,
            fontSizeBodyLarge = 14.sp,
            fontSizeLabelSmall = 10.sp,
            lineHeightHeadlineLarge = 24.sp,
            lineHeightBodyLarge = 18.sp,
            lineHeightLabelSmall = 14.sp,
            // Global
            cornerRadiusSmall = 5.dp,
            cornerRadiusMedium = 10.dp,
            cardElevation = 6.dp,
            resultCardShadowRadius = 8.dp,
            resultCardShadowSpread = 1.dp,
            editTextShadowRadius = 10.dp,
            editTextShadowSpread = 2.dp,
            dropdownMenuShadowRadius = 10.dp,
            dropdownMenuShadowSpread = 5.dp,
            cardInnerPadding = 8.dp,
            screenPadding = 12.dp,
            spacerXSmall = 12.dp,
            spacerSmall = 16.dp,
            spacerMedium = 24.dp,
            spacerLarge = 32.dp,
            spacerXLarge = 40.dp,
            // Main Screen
            commitButton = 40.dp,
            editTextHeight = 40.dp,
            // Navigation
            navBarHeight = 70.dp,
            navBarHeightLandscape = 48.dp,
            navButtonHeight = 65.dp,
            navButtonWidth = 80.dp,
            navButtonHeightLandscape = 40.dp,
            navButtonWidthLandscape = 65.dp,
            navButtonsMaxWidth = 360.dp,
            navButtonIconBlur = 4.dp,
            navButtonShadowTarget = 7.dp,
            settingsButtonIconBlur = 3.dp,
            settingsButtonShadowTarget = 5.dp,
            // Result Screen
            resultScreenStudyCarouselHeight = 160.dp,
            // Distribution Form
            distributionFormDataInputHeight = 160.dp,
            // Distribution Result
            distributionResultStatCardHeight = 65.dp,
            // Markov Form
            markovFormMatrixRowVerticalPadding = 3.dp,
            // Markov Result
            markovResultSummaryCardHeight = 280.dp,
            markovResultPathListHeight = 320.dp,
            markovResultPathItemHeight = 45.dp,
            // Montecarlo Result
            montecarloResultCardHeight = 560.dp,
            montecarloResultListHeight = 320.dp,
            montecarloResultSimulationItemHeight = 45.dp,
            // Geometrical Montecarlo Result
            geometricalMontecarloResultCardHeight = 320.dp,
            // Multinomial Montecarlo Result
            multinomialMontecarloResultCardHeight = 520.dp,
            multinomialMontecarloResultSliderRowHeight = 80.dp,
            multinomialMontecarloResultParamLabelPaddingStart = 8.dp,
            // Sliders
            sliderTrackHeight = 10.dp,
            sliderTrackPadding = 10.dp,
            sliderCustomTrackHeight = 2.dp,
            sliderStaticTrackHeight = 14.dp,
            sliderStaticTrackPaddingTop = 10.dp,
            sliderStaticTrackPaddingHorizontal = 5.dp,
            sliderGlowBlurRadius = 5.dp,
        )

        // ── Teléfonos estándar ───────────────────────────
        widthPx < 600 -> AppDimens(
            // Tipografía
            fontSizeHeadlineLarge = 20.sp,
            fontSizeBodyLarge = 16.sp,
            fontSizeLabelSmall = 11.sp,
            lineHeightHeadlineLarge = 28.sp,
            lineHeightBodyLarge = 20.sp,
            lineHeightLabelSmall = 16.sp,
            // Global
            cornerRadiusSmall = 5.dp,
            cornerRadiusMedium = 10.dp,
            cardElevation = 6.dp,
            resultCardShadowRadius = 8.dp,
            resultCardShadowSpread = 1.dp,
            editTextShadowRadius = 10.dp,
            editTextShadowSpread = 2.dp,
            dropdownMenuShadowRadius = 10.dp,
            dropdownMenuShadowSpread = 5.dp,
            cardInnerPadding = 10.dp,
            screenPadding = 16.dp,
            spacerXSmall = 16.dp,
            spacerSmall = 20.dp,
            spacerMedium = 30.dp,
            spacerLarge = 40.dp,
            spacerXLarge = 50.dp,
            // Main Screen
            commitButton = 50.dp,
            editTextHeight = 50.dp,
            // Navigation
            navBarHeight = 80.dp,
            navBarHeightLandscape = 56.dp,
            navButtonHeight = 80.dp,
            navButtonWidth = 100.dp,
            navButtonHeightLandscape = 48.dp,
            navButtonWidthLandscape = 80.dp,
            navButtonsMaxWidth = 480.dp,
            navButtonIconBlur = 4.dp,
            navButtonShadowTarget = 7.dp,
            settingsButtonIconBlur = 3.dp,
            settingsButtonShadowTarget = 5.dp,
            // Result Screen
            resultScreenStudyCarouselHeight = 200.dp,
            // Distribution Form
            distributionFormDataInputHeight = 200.dp,
            // Distribution Result
            distributionResultStatCardHeight = 80.dp,
            // Markov Form
            markovFormMatrixRowVerticalPadding = 3.dp,
            // Markov Result
            markovResultSummaryCardHeight = 350.dp,
            markovResultPathListHeight = 400.dp,
            markovResultPathItemHeight = 50.dp,
            // Montecarlo Result
            montecarloResultCardHeight = 700.dp,
            montecarloResultListHeight = 400.dp,
            montecarloResultSimulationItemHeight = 50.dp,
            // Geometrical Montecarlo Result
            geometricalMontecarloResultCardHeight = 400.dp,
            // Multinomial Montecarlo Result
            multinomialMontecarloResultCardHeight = 650.dp,
            multinomialMontecarloResultSliderRowHeight = 100.dp,
            multinomialMontecarloResultParamLabelPaddingStart = 10.dp,
            // Sliders
            sliderTrackHeight = 10.dp,
            sliderTrackPadding = 10.dp,
            sliderCustomTrackHeight = 2.dp,
            sliderStaticTrackHeight = 14.dp,
            sliderStaticTrackPaddingTop = 10.dp,
            sliderStaticTrackPaddingHorizontal = 5.dp,
            sliderGlowBlurRadius = 5.dp,
        )

        // ── Tablets y pantallas grandes ──────────────────
        else -> AppDimens(
            // Tipografía
            fontSizeHeadlineLarge = 24.sp,
            fontSizeBodyLarge = 18.sp,
            fontSizeLabelSmall = 13.sp,
            lineHeightHeadlineLarge = 32.sp,
            lineHeightBodyLarge = 24.sp,
            lineHeightLabelSmall = 18.sp,
            // Global
            cornerRadiusSmall = 5.dp,
            cornerRadiusMedium = 10.dp,
            cardElevation = 6.dp,
            resultCardShadowRadius = 8.dp,
            resultCardShadowSpread = 1.dp,
            editTextShadowRadius = 10.dp,
            editTextShadowSpread = 2.dp,
            dropdownMenuShadowRadius = 10.dp,
            dropdownMenuShadowSpread = 5.dp,
            cardInnerPadding = 12.dp,
            screenPadding = 24.dp,
            spacerXSmall = 20.dp,
            spacerSmall = 28.dp,
            spacerMedium = 40.dp,
            spacerLarge = 56.dp,
            spacerXLarge = 70.dp,
            // Main Screen
            commitButton = 60.dp,
            editTextHeight = 70.dp,
            // Navigation
            navBarHeight = 96.dp,
            navBarHeightLandscape = 64.dp,
            navButtonHeight = 100.dp,
            navButtonWidth = 130.dp,
            navButtonHeightLandscape = 56.dp,
            navButtonWidthLandscape = 100.dp,
            navButtonsMaxWidth = 600.dp,
            navButtonIconBlur = 4.dp,
            navButtonShadowTarget = 7.dp,
            settingsButtonIconBlur = 3.dp,
            settingsButtonShadowTarget = 5.dp,
            // Result Screen
            resultScreenStudyCarouselHeight = 280.dp,
            // Distribution Form
            distributionFormDataInputHeight = 280.dp,
            // Distribution Result
            distributionResultStatCardHeight = 100.dp,
            // Markov Form
            markovFormMatrixRowVerticalPadding = 3.dp,
            // Markov Result
            markovResultSummaryCardHeight = 450.dp,
            markovResultPathListHeight = 520.dp,
            markovResultPathItemHeight = 65.dp,
            // Montecarlo Result
            montecarloResultCardHeight = 900.dp,
            montecarloResultListHeight = 520.dp,
            montecarloResultSimulationItemHeight = 65.dp,
            // Geometrical Montecarlo Result
            geometricalMontecarloResultCardHeight = 520.dp,
            // Multinomial Montecarlo Result
            multinomialMontecarloResultCardHeight = 850.dp,
            multinomialMontecarloResultSliderRowHeight = 130.dp,
            multinomialMontecarloResultParamLabelPaddingStart = 12.dp,
            // Sliders
            sliderTrackHeight = 10.dp,
            sliderTrackPadding = 10.dp,
            sliderCustomTrackHeight = 2.dp,
            sliderStaticTrackHeight = 14.dp,
            sliderStaticTrackPaddingTop = 10.dp,
            sliderStaticTrackPaddingHorizontal = 5.dp,
            sliderGlowBlurRadius = 5.dp,
        )
    }
}
