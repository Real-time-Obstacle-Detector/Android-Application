package com.example.realtime_obstacle_detection.ui.screens.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.realtime_obstacle_detection.R
import com.example.realtime_obstacle_detection.ui.theme.RodColors
import com.example.realtime_obstacle_detection.ui.theme.RodType

@Composable
internal fun HomeIntroSection() {
    Box(
        modifier = Modifier
            .size(92.dp)
            .clip(RoundedCornerShape(26.dp))
            .background(Color.White.copy(alpha = 0.7f))
            .border(1.dp, RodColors.OutlineVariant.copy(alpha = 0.3f), RoundedCornerShape(26.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.onboarding_hero_sensor),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(20.dp))
        )
    }

    Spacer(
        modifier = Modifier.height(12.dp)
    )

    Text(
        text = "Real-time obstacle awareness.",
        style = RodType.HeadlineMd,
        color = RodColors.TextPrimary,
        textAlign = TextAlign.Center
    )

    Spacer(
        modifier = Modifier.height(4.dp)
    )

    Text(
        text = "Detect hazards, estimate distance, and receive voice or haptic alerts using on-device AI.",
        style = RodType.BodyMd,
        color = RodColors.OnSurfaceVariant,
        textAlign = TextAlign.Center,
        modifier = Modifier.widthIn(max = 300.dp)
    )
}
