package com.example.realtime_obstacle_detection.ui.screens.about

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.realtime_obstacle_detection.ui.theme.RodColors
import com.example.realtime_obstacle_detection.ui.theme.RodType
import com.example.realtime_obstacle_detection.ui.theme.toSp

@Composable
internal fun ResearchPapersAccordion() {
    var expanded by remember { mutableStateOf(true) }
    AccordionContainer(
        title = "Research Papers",
        leadingIcon = Icons.Filled.Description,
        expanded = expanded,
        onToggle = { expanded = !expanded }
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            PaperRow(
                title = "ROD: Real-time Obstacle Detector",
                subtitle = "App & System Architecture"
            )
            PaperRow(
                title = "ROD-31k: A Large-scale Obstacle Dataset",
                subtitle = "Dataset Analysis & Benchmarks"
            )
        }
    }
}

@Composable
private fun PaperRow(title: String, subtitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(RodColors.SurfaceContainerLow)
            .border(1.dp, RodColors.OutlineVariant.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = RodType.BodyMd,
                fontWeight = FontWeight.SemiBold,
                color = RodColors.OnSurface
            )
            Text(
                text = subtitle,
                fontSize = 12.dp.toSp(),
                color = RodColors.OnSurfaceVariant
            )
        }
        Spacer(Modifier.width(12.dp))
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(RodColors.Primary),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Download,
                contentDescription = "Download",
                tint = RodColors.OnPrimary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
