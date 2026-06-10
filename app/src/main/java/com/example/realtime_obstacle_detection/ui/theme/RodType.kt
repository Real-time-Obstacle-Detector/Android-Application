package com.example.realtime_obstacle_detection.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.realtime_obstacle_detection.R

/**
 * Inter font family bundled per the ROD Design System (weights 400/600/700/800).
 */
val InterFontFamily = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold),
    Font(R.font.inter_extrabold, FontWeight.ExtraBold)
)

/**
 * ROD Design System typography tokens (sizes/line-heights/tracking from DESIGN.md).
 */
object RodType {

    val DisplayLg = TextStyle(
        fontFamily = InterFontFamily,
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 41.sp,
        letterSpacing = (-0.02).em
    )

    val HeadlineMd = TextStyle(
        fontFamily = InterFontFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 30.sp,
        letterSpacing = (-0.01).em
    )

    val HeadlineSm = TextStyle(
        fontFamily = InterFontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 25.sp
    )

    val HeadlineLgMobile = TextStyle(
        fontFamily = InterFontFamily,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 34.sp
    )

    val AlertDisplay = TextStyle(
        fontFamily = InterFontFamily,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 34.sp,
        letterSpacing = (-0.01).em
    )

    val BodyLg = TextStyle(
        fontFamily = InterFontFamily,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp
    )

    val BodyMd = TextStyle(
        fontFamily = InterFontFamily,
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp
    )

    val LabelCaps = TextStyle(
        fontFamily = InterFontFamily,
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 16.sp,
        letterSpacing = 0.05.em
    )
}
