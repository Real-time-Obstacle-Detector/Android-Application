package com.example.realtime_obstacle_detection.ui.screens.about

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.realtime_obstacle_detection.ui.theme.RodColors
import com.example.realtime_obstacle_detection.ui.theme.RodType

@Composable
internal fun AccordionContainer(
    title: String,
    leadingIcon: ImageVector,
    expanded: Boolean,
    onToggle: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(RodColors.SurfaceContainerLowest)
            .border(1.dp, RodColors.OutlineVariant, RoundedCornerShape(24.dp))
            .animateContentSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onToggle() }
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = RodColors.Primary
                )
                Text(
                    text = title,
                    style = RodType.HeadlineMd,
                    color = RodColors.OnSurface
                )
            }
            Icon(
                imageVector = Icons.Filled.ExpandMore,
                contentDescription = null,
                tint = RodColors.OnSurfaceVariant,
                modifier = Modifier.rotate(if (expanded) 180f else 0f)
            )
        }
        if (expanded) {
            Box(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
            ) {
                content()
            }
        }
    }
}
