package com.manuelnunez.apps.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.manuelnunez.apps.core.ui.R

val Typography =
    Typography(
        displaySmall =
            TextStyle(
                fontFamily = FontFamily(Font(R.font.nunito_sans_light)),
                fontWeight = FontWeight.Normal,
                fontSize = 36.sp),
        headlineSmall =
            TextStyle(
                fontFamily = FontFamily(Font(R.font.nunito_sans_light)),
                fontWeight = FontWeight.Normal,
                fontSize = 30.sp),
        labelSmall =
            TextStyle(
                fontFamily = FontFamily(Font(R.font.nunito_sans_light)),
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp),
        titleSmall =
            TextStyle(
                fontFamily = FontFamily(Font(R.font.nunito_sans_bold)),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp),
        titleMedium =
            TextStyle(
                fontFamily = FontFamily(Font(R.font.nunito_sans)),
                fontWeight = FontWeight.SemiBold,
                letterSpacing = (.5).sp,
                fontSize = 18.sp),
        titleLarge =
            TextStyle(
                fontFamily = FontFamily(Font(R.font.nunito_sans_light)),
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp),
    )
