package com.example.realtime_obstacle_detection.ui.screens.homepage

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.realtime_obstacle_detection.ui.theme.RodColors
import com.example.realtime_obstacle_detection.ui.theme.RodType

@Composable
internal fun HomeActions(
    onStartDetection: () -> Unit,
    onAboutClick: () -> Unit
) {
    Button(
        onClick = onStartDetection,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = RodColors.Primary,
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        Text(
            text = "Start Detection",
            style = RodType.HeadlineSm
        )

        Spacer(
            modifier = Modifier.width(8.dp)
        )

        Icon(
            imageVector = Icons.Filled.ArrowForward,
            contentDescription = null
        )
    }

    TextButton(
        onClick = onAboutClick
    ) {
        Text(
            text = "Learn how it works",
            style = RodType.LabelCaps,
            color = RodColors.Primary
        )
    }
}
