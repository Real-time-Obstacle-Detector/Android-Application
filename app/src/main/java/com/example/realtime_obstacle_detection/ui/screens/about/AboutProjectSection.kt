package com.example.realtime_obstacle_detection.ui.screens.about

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.example.realtime_obstacle_detection.ui.theme.RodColors
import com.example.realtime_obstacle_detection.ui.theme.RodType
import com.example.realtime_obstacle_detection.ui.theme.toSp

@Composable
internal fun AboutProjectSection() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "ABOUT PROJECT",
                style = RodType.LabelCaps,
                color = RodColors.Primary
            )
            Text(
                "ROD: Real-time Obstacle Detector",
                style = RodType.DisplayLg,
                color = RodColors.OnSurface
            )
        }
        Text(
            "ROD (Real-time Obstacle Detector) represents a pioneering leap in assistive mobility, integrating advanced YOLOv8 computer vision models with high-efficiency on-device AI. Our work focuses on empowering visually impaired individuals through a robust mobile optimization framework that delivers latency-free obstacle identification directly on standard smartphone hardware.",
            style = RodType.BodyLg,
            color = RodColors.OnSurfaceVariant
        )
        Text(
            "The system employs a multimodal feedback loop, combining spatial audio cues with haptic responses to provide intuitive environmental awareness. By leveraging the comprehensive ROD-31k dataset, we ensure high-precision detection across diverse urban scenarios, bridging the gap between sophisticated laboratory research and practical, life-enhancing accessibility tools.",
            style = RodType.BodyLg,
            color = RodColors.OnSurfaceVariant
        )
    }
}

@Composable
internal fun AboutQuickActions() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(RodColors.SurfaceContainerLowest)
            .border(1.dp, RodColors.OutlineVariant, RoundedCornerShape(16.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        QuickAction(
            label = "GitHub",
            icon = Icons.Filled.Code,
            url = "https://github.com/Real-time-Obstacle-Detector",
            modifier = Modifier.weight(1f)
        )
        QuickAction("Website", Icons.Filled.Language, url = null, Modifier.weight(1f))
        QuickAction(
            label = "LinkedIn",
            icon = Icons.Filled.Share,
            url = "https://www.linkedin.com/in/abtinzandi/",
            modifier = Modifier.weight(1f)
        )
        QuickAction(
            label = "HF",
            icon = Icons.Filled.Storage,
            url = "https://huggingface.co/datasets/Abtinzandi/Obstacle-Detection-Dataset-YOLO",
            modifier = Modifier.weight(1f)
        )
        QuickAction(
            label = "Kaggle",
            icon = Icons.Filled.Terminal,
            url = "https://www.kaggle.com/datasets/abtinzandi/obstacle-detection-dataset",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun QuickAction(label: String, icon: ImageVector, url: String?, modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = modifier.then(
            if (url != null) Modifier.clickable { uriHandler.openUri(url) } else Modifier
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(RodColors.SurfaceContainerHigh),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = label, tint = RodColors.OnSurfaceVariant, modifier = Modifier.size(20.dp))
        }
        Text(label, fontSize = 10.dp.toSp(), style = RodType.LabelCaps, color = RodColors.OnSurfaceVariant)
    }
}
