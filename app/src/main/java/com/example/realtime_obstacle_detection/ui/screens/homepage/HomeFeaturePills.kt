package com.example.realtime_obstacle_detection.ui.screens.homepage

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.realtime_obstacle_detection.ui.theme.RodColors
import com.example.realtime_obstacle_detection.ui.theme.toSp

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun HomeFeaturePills(selectedMode: DetectionMode) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        InfoPill(
            text = "Private On-Device AI",
            icon = Icons.Filled.VerifiedUser,
            iconTint = RodColors.Secondary
        )

        Crossfade(
            targetState = selectedMode,
            label = "Mode feature pill"
        ) { mode ->
            val feature = modeFeaturePill(mode)
            InfoPill(
                text = feature.text,
                icon = feature.icon,
                iconTint = feature.iconTint
            )
        }
    }
}

private data class ModeFeaturePill(
    val text: String,
    val icon: ImageVector,
    val iconTint: Color
)

private fun modeFeaturePill(mode: DetectionMode): ModeFeaturePill {
    return when (mode) {
        DetectionMode.BLIND -> ModeFeaturePill(
            text = "Voice Alerts",
            icon = Icons.Filled.RecordVoiceOver,
            iconTint = RodColors.Primary
        )

        DetectionMode.WALK -> ModeFeaturePill(
            text = "Vibration Alerts",
            icon = Icons.Filled.Vibration,
            iconTint = RodColors.Secondary
        )

        DetectionMode.BOUNDING -> ModeFeaturePill(
            text = "Test & Save",
            icon = Icons.Filled.Save,
            iconTint = RodColors.Tertiary
        )
    }
}

@Composable
private fun InfoPill(
    text: String,
    icon: ImageVector,
    iconTint: Color
) {
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(RodColors.SurfaceContainerLow)
            .border(1.dp, RodColors.OutlineVariant.copy(alpha = 0.2f), CircleShape)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(16.dp)
        )

        Text(
            text = text,
            fontSize = 12.dp.toSp(),
            fontWeight = FontWeight.SemiBold,
            color = RodColors.OnSurfaceVariant
        )
    }
}
