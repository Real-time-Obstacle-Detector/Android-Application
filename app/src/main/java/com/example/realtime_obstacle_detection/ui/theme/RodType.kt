package com.example.realtime_obstacle_detection.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
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
 * Converts a [Dp] value into a text size ([TextUnit]/sp) using the current display
 * density. Because the result is derived from dp rather than raw sp, text keeps a
 * fixed physical size regardless of the user's system font-scale — which keeps the
 * compact, single-screen layouts from overflowing.
 *
 * Usage: `fontSize = 12.dp.toSp()`
 */
@Composable
fun Dp.toSp(): TextUnit = with(LocalDensity.current) { this@toSp.toSp() }

/**
 * ROD Design System typography tokens. Sizes/line-heights are density-derived via
 * [toSp] so every text using these styles is font-scale independent.
 */
object RodType {

    val DisplayLg: TextStyle
        @Composable get() = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 34.dp.toSp(),
            fontWeight = FontWeight.Bold,
            lineHeight = 41.dp.toSp(),
            letterSpacing = (-0.02).em
        )

    val HeadlineMd: TextStyle
        @Composable get() = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 24.dp.toSp(),
            fontWeight = FontWeight.SemiBold,
            lineHeight = 30.dp.toSp(),
            letterSpacing = (-0.01).em
        )

    val HeadlineSm: TextStyle
        @Composable get() = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 20.dp.toSp(),
            fontWeight = FontWeight.SemiBold,
            lineHeight = 25.dp.toSp()
        )

    val HeadlineLgMobile: TextStyle
        @Composable get() = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 28.dp.toSp(),
            fontWeight = FontWeight.Bold,
            lineHeight = 34.dp.toSp()
        )

    val AlertDisplay: TextStyle
        @Composable get() = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 28.dp.toSp(),
            fontWeight = FontWeight.Bold,
            lineHeight = 34.dp.toSp(),
            letterSpacing = (-0.01).em
        )

    val BodyLg: TextStyle
        @Composable get() = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 17.dp.toSp(),
            fontWeight = FontWeight.Normal,
            lineHeight = 24.dp.toSp()
        )

    val BodyMd: TextStyle
        @Composable get() = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 15.dp.toSp(),
            fontWeight = FontWeight.Normal,
            lineHeight = 20.dp.toSp()
        )

    val LabelCaps: TextStyle
        @Composable get() = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 13.dp.toSp(),
            fontWeight = FontWeight.SemiBold,
            lineHeight = 16.dp.toSp(),
            letterSpacing = 0.05.em
        )
}
