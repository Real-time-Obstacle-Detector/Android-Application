package com.example.realtime_obstacle_detection.ui.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realtime_obstacle_detection.ui.theme.RodColors

/**
 * Full-screen, accessible error state shown when the detector/model cannot be
 * initialised (for example a missing .tflite file in assets). This only changes
 * what is displayed on failure — the detector logic itself is untouched.
 */
@Composable
fun DetectorErrorMessage(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ErrorOutline,
                contentDescription = null,
                tint = RodColors.UrgentRed,
                modifier = Modifier.size(56.dp)
            )
            Text(
                text = "Detection unavailable",
                color = RodColors.OnSurface,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = message,
                color = RodColors.OnSurfaceVariant,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
