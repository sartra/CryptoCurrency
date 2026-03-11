package com.plcoding.cryptocurrencyappyt.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val defaultTypography = Typography()

val Typography = defaultTypography.copy(
    displayLarge = defaultTypography.displayLarge.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
    ),
    headlineLarge = defaultTypography.headlineLarge.copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
    ),
    headlineMedium = defaultTypography.headlineMedium.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
    ),
    titleLarge = defaultTypography.titleLarge.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    bodyLarge = defaultTypography.bodyLarge.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    bodyMedium = defaultTypography.bodyMedium.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
)

