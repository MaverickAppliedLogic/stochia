package com.example.stochia.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.stochia.R

val interFonts = FontFamily(
    Font(R.font.inter_bold),
    Font(R.font.inter_semibold)
)

val spaceGroteskFonts = FontFamily(
    Font(R.font.space_grotesk_bold),
    Font(R.font.space_grotesk_semibold)
)


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = interFonts,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = spaceGroteskFonts,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 3.sp
    ),
    labelSmall = TextStyle(
        fontFamily = interFonts,
        fontWeight = FontWeight.SemiBold,
        color = Color.White,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
