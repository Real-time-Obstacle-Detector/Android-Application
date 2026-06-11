package com.example.realtime_obstacle_detection.ui.screens.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessible
import androidx.compose.material.icons.filled.CropFree
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.realtime_obstacle_detection.ui.theme.RodColors
import com.example.realtime_obstacle_detection.ui.theme.RodType
import com.example.realtime_obstacle_detection.ui.theme.toSp

@Composable
internal fun ModeSelectionSection(
    selectedMode: DetectionMode,
    onModeSelected: (DetectionMode) -> Unit
) {
    Text(
        text = "SELECT OPERATING MODE",
        style = RodType.LabelCaps,
        color = RodColors.Outline,
        textAlign = TextAlign.Center
    )

    Spacer(
        modifier = Modifier.height(10.dp)
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ModeCard(
                title = "Blind-Protector",
                description = "Voice & haptic guidance",
                icon = Icons.Filled.Accessible,
                iconBg = RodColors.PrimaryFixed,
                iconTint = RodColors.OnPrimaryFixed,
                selected = selectedMode == DetectionMode.BLIND,
                onClick = { onModeSelected(DetectionMode.BLIND) },
                modifier = Modifier.weight(1f)
            )

            ModeCard(
                title = "Walk-Around",
                description = "Visual overlays",
                icon = Icons.Filled.DirectionsWalk,
                iconBg = RodColors.SecondaryFixed,
                iconTint = RodColors.OnSecondaryFixed,
                selected = selectedMode == DetectionMode.WALK,
                onClick = { onModeSelected(DetectionMode.WALK) },
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier.weight(0.5f)
            )

            ModeCard(
                title = "Bounding Box",
                description = "Raw detection view",
                icon = Icons.Filled.CropFree,
                iconBg = RodColors.SurfaceContainerHighest,
                iconTint = RodColors.OnSurfaceVariant,
                selected = selectedMode == DetectionMode.BOUNDING,
                onClick = { onModeSelected(DetectionMode.BOUNDING) },
                modifier = Modifier.weight(1f)
            )

            Spacer(
                modifier = Modifier.weight(0.5f)
            )
        }
    }
}

@Composable
private fun ModeCard(
    title: String,
    description: String,
    icon: ImageVector,
    iconBg: Color,
    iconTint: Color,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (selected) RodColors.SurfaceContainerLow else Color.White.copy(alpha = 0.7f))
            .border(
                width = if (selected) 2.5.dp else 1.dp,
                color = if (selected) RodColors.Primary else RodColors.BorderLight.copy(alpha = 0.5f),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(iconBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = title,
            fontSize = 14.dp.toSp(),
            fontWeight = FontWeight.SemiBold,
            color = RodColors.TextPrimary,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(2.dp)
        )

        Text(
            text = description,
            fontSize = 11.dp.toSp(),
            color = RodColors.OnSurfaceVariant,
            textAlign = TextAlign.Center,
            lineHeight = 13.dp.toSp()
        )
    }
}
