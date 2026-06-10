package com.example.realtime_obstacle_detection.ui.screens.homepage

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessible
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CropFree
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.realtime_obstacle_detection.R
import com.example.realtime_obstacle_detection.ui.activities.BlindDetectorActivity
import com.example.realtime_obstacle_detection.ui.activities.OnDetectionActivity
import com.example.realtime_obstacle_detection.ui.activities.WalkAroundActivity
import com.example.realtime_obstacle_detection.ui.theme.RodColors
import com.example.realtime_obstacle_detection.ui.theme.RodType

private enum class DetectionMode { BLIND, WALK, BOUNDING }

/**
 * Main menu — translated from design/onboarding_updated/code.html
 * (hero, operating-mode selection, feature pills, Start Detection).
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomePageScreen(navController: NavController) {
    val context = LocalContext.current
    var selectedMode by remember { mutableStateOf(DetectionMode.BLIND) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RodColors.Background)
            .systemBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top app bar: ROD brand + settings
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp)
                    .height(44.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(RodColors.Primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Filled.Visibility, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                    }
                    Text("ROD", style = RodType.HeadlineMd, color = RodColors.Primary)
                }
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .clickable { navController.navigate("settings") },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Settings, contentDescription = "Settings", tint = RodColors.OnSurfaceVariant)
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .widthIn(max = 512.dp)
                    .padding(horizontal = 20.dp)
                    .padding(top = 16.dp, bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Hero
                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .background(Color.White.copy(alpha = 0.7f))
                        .border(1.dp, RodColors.OutlineVariant.copy(alpha = 0.3f), RoundedCornerShape(40.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.onboarding_hero_sensor),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(128.dp)
                            .clip(RoundedCornerShape(32.dp))
                    )
                }

                Spacer(Modifier.height(32.dp))

                Text(
                    text = "Real-time obstacle awareness.",
                    style = RodType.HeadlineLgMobile,
                    color = RodColors.TextPrimary,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "Detect hazards, estimate distance, and receive voice or haptic alerts using on-device AI.",
                    style = RodType.BodyMd,
                    color = RodColors.OnSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.widthIn(max = 280.dp)
                )

                Spacer(Modifier.height(40.dp))

                // Mode selection
                Text(
                    text = "SELECT OPERATING MODE",
                    style = RodType.LabelCaps,
                    color = RodColors.Outline,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(16.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        ModeCard(
                            title = "Blind-Protector",
                            description = "High-fidelity voice & haptic guidance",
                            icon = Icons.Filled.Accessible,
                            iconBg = RodColors.PrimaryFixed,
                            iconTint = RodColors.OnPrimaryFixed,
                            selected = selectedMode == DetectionMode.BLIND,
                            onClick = { selectedMode = DetectionMode.BLIND },
                            modifier = Modifier.weight(1f)
                        )
                        ModeCard(
                            title = "Walk-Around",
                            description = "Environmental visual overlays",
                            icon = Icons.Filled.DirectionsWalk,
                            iconBg = RodColors.SecondaryFixed,
                            iconTint = RodColors.OnSecondaryFixed,
                            selected = selectedMode == DetectionMode.WALK,
                            onClick = { selectedMode = DetectionMode.WALK },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Spacer(Modifier.weight(0.5f))
                        ModeCard(
                            title = "Bounding Box",
                            description = "Raw detection visualization for research and debugging.",
                            icon = Icons.Filled.CropFree,
                            iconBg = RodColors.SurfaceContainerHighest,
                            iconTint = RodColors.OnSurfaceVariant,
                            selected = selectedMode == DetectionMode.BOUNDING,
                            onClick = { selectedMode = DetectionMode.BOUNDING },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(Modifier.weight(0.5f))
                    }
                }

                Spacer(Modifier.height(40.dp))

                // Feature pills
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    InfoPill("Private On-Device AI", Icons.Filled.VerifiedUser, RodColors.Secondary)
                    InfoPill("Voice Alerts", Icons.Filled.RecordVoiceOver, RodColors.Primary)
                }

                Spacer(Modifier.height(40.dp))

                // Actions
                Button(
                    onClick = { launchMode(context, selectedMode) },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RodColors.Primary,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Start Detection", style = RodType.HeadlineSm)
                    Spacer(Modifier.width(8.dp))
                    Icon(Icons.Filled.ArrowForward, contentDescription = null)
                }
                Spacer(Modifier.height(8.dp))
                TextButton(onClick = { navController.navigate("about_us") }) {
                    Text("Learn how it works", style = RodType.LabelCaps, color = RodColors.Primary)
                }
            }
        }
    }
}

private fun launchMode(context: Context, mode: DetectionMode) {
    val target = when (mode) {
        DetectionMode.BLIND -> BlindDetectorActivity::class.java
        DetectionMode.WALK -> WalkAroundActivity::class.java
        DetectionMode.BOUNDING -> OnDetectionActivity::class.java
    }
    context.startActivity(Intent(context, target))
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
            .clip(RoundedCornerShape(24.dp))
            .background(if (selected) RodColors.SurfaceContainerLow else Color.White.copy(alpha = 0.7f))
            .border(
                width = if (selected) 2.5.dp else 1.dp,
                color = if (selected) RodColors.Primary else RodColors.BorderLight.copy(alpha = 0.5f),
                shape = RoundedCornerShape(24.dp)
            )
            .clickable { onClick() }
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(iconBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(32.dp))
        }
        Spacer(Modifier.height(12.dp))
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = RodColors.TextPrimary,
            textAlign = TextAlign.Center,
            style = RodType.HeadlineSm.copy(fontSize = 16.sp)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = description,
            fontSize = 12.sp,
            color = RodColors.OnSurfaceVariant,
            textAlign = TextAlign.Center,
            lineHeight = 15.sp
        )
    }
}

@Composable
private fun InfoPill(text: String, icon: ImageVector, iconTint: Color) {
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(RodColors.SurfaceContainerLow)
            .border(1.dp, RodColors.OutlineVariant.copy(alpha = 0.2f), CircleShape)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(16.dp))
        Text(text = text, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = RodColors.OnSurfaceVariant)
    }
}
