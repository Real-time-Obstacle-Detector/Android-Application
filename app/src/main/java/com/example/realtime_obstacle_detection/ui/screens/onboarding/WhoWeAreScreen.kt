package com.example.realtime_obstacle_detection.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realtime_obstacle_detection.R
import com.example.realtime_obstacle_detection.ui.theme.RodColors
import com.example.realtime_obstacle_detection.ui.theme.RodType

/**
 * Onboarding splash 1 — "ROD: Research Built for Safer Walking".
 * Translated from design/onboarding_who_we_are/code.html
 */
@Composable
fun WhoWeAreScreen(
    onNext: () -> Unit,
    onSkip: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RodColors.Background)
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 96.dp)
        ) {
            // Header: wordmark + 3 dots
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "ROD", style = RodType.HeadlineMd, color = RodColors.Primary)
                PageIndicators(pageCount = 3, activeIndex = 0)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Smartphone scanning illustration
                Box(
                    modifier = Modifier
                        .widthIn(max = 320.dp)
                        .fillMaxWidth()
                        .aspectRatio(9f / 16f)
                        .clip(RoundedCornerShape(48.dp))
                        .background(RodColors.SurfaceContainerLow)
                        .border(8.dp, RodColors.TextPrimary, RoundedCornerShape(48.dp))
                ) {
                    Image(
                        painter = painterResource(R.drawable.onboarding_sidewalk),
                        contentDescription = "Pedestrian sidewalk",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    // Bounding box: STAIRS • 2.5m
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(start = 40.dp, top = 90.dp)
                    ) {
                        DetectionBox(label = "STAIRS • 2.5m", width = 128.dp, height = 80.dp)
                    }
                    // Bounding box: BOLLARD • 1.2m
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 36.dp, bottom = 90.dp)
                    ) {
                        DetectionBox(label = "BOLLARD • 1.2m", width = 96.dp, height = 120.dp)
                    }

                    // Glass status pill — ENVIRONMENT SECURE
                    Row(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 24.dp)
                            .fillMaxWidth(0.8f)
                            .height(40.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.45f))
                            .border(1.dp, Color.White.copy(alpha = 0.4f), CircleShape),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = null,
                            tint = RodColors.Primary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "ENVIRONMENT SECURE",
                            fontSize = 12.sp,
                            style = RodType.LabelCaps,
                            color = RodColors.TextPrimary
                        )
                    }
                }

                Spacer(Modifier.height(32.dp))

                // Title + subtitle
                Text(
                    text = "ROD: Research Built for Safer Walking",
                    style = RodType.HeadlineLgMobile,
                    color = RodColors.TextPrimary,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "We are a research-driven computer vision team building smartphone-based obstacle awareness tools for pedestrians.",
                    style = RodType.BodyLg,
                    color = RodColors.TextSecondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(Modifier.height(24.dp))

                // Trust label card with 3 columns
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 384.dp)
                        .height(IntrinsicSize.Min)
                        .clip(RoundedCornerShape(16.dp))
                        .background(RodColors.SurfaceContainerLowest)
                        .border(1.dp, RodColors.BorderLight, RoundedCornerShape(16.dp))
                        .padding(vertical = 16.dp)
                ) {
                    TrustCell(Icons.Filled.School, "Published Research", Modifier.weight(1f))
                    TrustDivider()
                    TrustCell(Icons.Filled.Storage, "Public Dataset", Modifier.weight(1f))
                    TrustDivider()
                    TrustCell(Icons.Filled.Psychology, "Mobile AI", Modifier.weight(1f))
                }

                Spacer(Modifier.height(24.dp))

                Text(
                    text = "ROD uses real-time AI to detect nearby obstacles and provide simple alerts through visual, vibration, and voice feedback.",
                    style = RodType.BodyMd,
                    color = RodColors.TextSecondary.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
            }
        }

        // Bottom navigation: Skip / Next
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(RodColors.SurfaceContainerLowest.copy(alpha = 0.9f))
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onSkip) {
                Text("Skip", style = RodType.LabelCaps, color = RodColors.TextSecondary)
            }
            Button(
                onClick = onNext,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = RodColors.Primary,
                    contentColor = RodColors.OnPrimary
                ),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(
                    horizontal = 32.dp, vertical = 12.dp
                )
            ) {
                Text("Next", style = RodType.LabelCaps)
                Spacer(Modifier.width(8.dp))
                Icon(Icons.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(18.dp))
            }
        }
    }
}

@Composable
private fun TrustCell(icon: ImageVector, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, contentDescription = null, tint = RodColors.Primary, modifier = Modifier.size(24.dp))
        Spacer(Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 10.sp,
            style = RodType.LabelCaps,
            color = RodColors.TextSecondary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun TrustDivider() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(1.dp)
            .background(RodColors.BorderLight)
    )
}

@Composable
private fun DetectionBox(label: String, width: androidx.compose.ui.unit.Dp, height: androidx.compose.ui.unit.Dp) {
    Box {
        Box(
            modifier = Modifier
                .padding(top = 20.dp)
                .size(width = width, height = height)
                .clip(RoundedCornerShape(8.dp))
                .border(2.dp, RodColors.Primary.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .clip(RoundedCornerShape(4.dp))
                .background(RodColors.Primary)
                .padding(horizontal = 8.dp, vertical = 2.dp)
        ) {
            Text(text = label, color = RodColors.OnPrimary, fontSize = 10.sp, style = RodType.LabelCaps)
        }
    }
}
