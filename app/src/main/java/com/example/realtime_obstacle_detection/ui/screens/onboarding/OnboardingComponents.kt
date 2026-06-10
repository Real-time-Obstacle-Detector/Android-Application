package com.example.realtime_obstacle_detection.ui.screens.onboarding

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.realtime_obstacle_detection.ui.theme.RodColors
import com.example.realtime_obstacle_detection.ui.theme.RodType

/**
 * ROD brand wordmark used in onboarding top bars.
 */
@Composable
fun RodWordmark(modifier: Modifier = Modifier, showIcon: Boolean = true) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (showIcon) {
            Icon(
                imageVector = Icons.Filled.Visibility,
                contentDescription = null,
                tint = RodColors.Primary,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = "ROD",
            style = RodType.HeadlineMd,
            color = RodColors.Primary
        )
    }
}

/**
 * Page indicator dots. The active dot stretches into a pill, matching the
 * Stitch onboarding indicators.
 */
@Composable
fun PageIndicators(
    pageCount: Int,
    activeIndex: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val active = index == activeIndex
            val width by animateDpAsState(if (active) 24.dp else 8.dp, label = "dotWidth")
            val color by animateColorAsState(
                if (active) RodColors.Primary else RodColors.SurfaceContainerHighest,
                label = "dotColor"
            )
            Box(
                modifier = Modifier
                    .height(8.dp)
                    .width(width)
                    .clip(if (active) RoundedCornerShape(4.dp) else CircleShape)
                    .background(color)
            )
        }
    }
}

/**
 * Small status chip used in the onboarding top bars (e.g. "PRIVATE ENGINE").
 */
@Composable
fun StatusChip(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: androidx.compose.ui.graphics.Color = RodColors.Secondary,
    textColor: androidx.compose.ui.graphics.Color = RodColors.OnSecondaryFixedVariant,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(RodColors.SurfaceContainerLow)
            .height(28.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(modifier = Modifier.width(12.dp))
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = text,
            style = RodType.LabelCaps,
            color = textColor
        )
        Box(modifier = Modifier.width(12.dp))
    }
}

/** Spacer helper to keep a row's content centered with optional weight. */
@Composable
fun RowScope.FlexSpacer() {
    Box(modifier = Modifier.weight(1f))
}
