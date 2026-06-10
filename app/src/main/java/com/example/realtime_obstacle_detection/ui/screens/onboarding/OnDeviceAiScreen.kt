package com.example.realtime_obstacle_detection.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Stairs
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.VpnLock
import androidx.compose.material.icons.filled.Warning
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realtime_obstacle_detection.ui.theme.RodColors
import com.example.realtime_obstacle_detection.ui.theme.RodType

/**
 * Onboarding splash 2 — "Real-Time. Accurate. Private.".
 * Translated from design/onboarding_on_device_ai/code.html
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OnDeviceAiScreen(
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RodColors.Background)
            .systemBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top app bar: ROD wordmark + PRIVATE ENGINE chip
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(RodColors.Surface)
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RodWordmark()
                StatusChip(text = "PRIVATE ENGINE", icon = Icons.Filled.Security)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
                    .padding(top = 16.dp, bottom = 140.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Visual composition: phone shell with AI chip core
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(4f / 3f),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .width(192.dp)
                            .height(320.dp)
                            .clip(RoundedCornerShape(40.dp))
                            .background(RodColors.SurfaceContainerLowest)
                            .border(4.dp, RodColors.SurfaceContainerHighest, RoundedCornerShape(40.dp))
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(top = 0.dp)
                                    .width(48.dp)
                                    .height(4.dp)
                                    .clip(CircleShape)
                                    .background(RodColors.SurfaceContainerHighest)
                            )
                            Spacer(Modifier.height(24.dp))
                            Icon(
                                imageVector = Icons.Filled.Memory,
                                contentDescription = null,
                                tint = RodColors.Primary,
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(Modifier.height(24.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                MiniDetectionTile("STAIRS", Icons.Filled.Stairs, Modifier.weight(1f))
                                MiniDetectionTile("PERSON", Icons.Filled.Person, Modifier.weight(1f))
                            }
                        }
                        // Privacy lock overlay
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .size(48.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(RodColors.Secondary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Filled.Lock,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    // Floating decoration icons
                    FloatingIcon(Icons.Filled.Warning, RodColors.UrgentRed, Modifier.align(Alignment.TopStart).padding(start = 8.dp, top = 24.dp))
                    FloatingIcon(Icons.Filled.VerifiedUser, RodColors.Secondary, Modifier.align(Alignment.TopEnd).padding(end = 8.dp, top = 48.dp))
                }

                Spacer(Modifier.height(32.dp))

                Text(
                    text = "Real-Time. Accurate. Private.",
                    style = RodType.HeadlineLgMobile,
                    color = RodColors.TextPrimary,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "ROD detects up to 25 common obstacle categories and runs directly on your phone. The model is trained on 24,326 images and optimized for mobile inference. No internet connection or cloud processing required.",
                    style = RodType.BodyMd,
                    color = RodColors.TextSecondary,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(24.dp))

                // Feature chips
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FeatureChip("25 Object Classes", Icons.Filled.Category)
                    FeatureChip("On-Device Detection", Icons.Filled.Bolt, highlighted = true)
                    FeatureChip("No Server Processing", Icons.Filled.CloudOff)
                    FeatureChip("100% Local Analysis", Icons.Filled.VpnLock)
                    FeatureChip("Voice + Vibration Alerts", Icons.Filled.VolumeUp)
                }

                Spacer(Modifier.height(24.dp))

                // Privacy note card
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(RodColors.SurfaceContainerLowest)
                        .border(1.dp, RodColors.BorderLight, RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(RodColors.Secondary.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Filled.VerifiedUser, contentDescription = null, tint = RodColors.Secondary, modifier = Modifier.size(22.dp))
                    }
                    Text(
                        text = "Privacy First: Your camera frames stay on your device.",
                        style = RodType.BodyMd,
                        color = RodColors.OnSurfaceVariant
                    )
                }
            }
        }

        // Bottom navigation shell
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .background(RodColors.SurfaceContainerLowest.copy(alpha = 0.92f))
        ) {
            PageIndicators(
                pageCount = 3,
                activeIndex = 1,
                modifier = Modifier
                    .padding(top = 24.dp, bottom = 8.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .wrapContentWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 8.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = onBack) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(18.dp), tint = RodColors.TextSecondary)
                    Spacer(Modifier.width(8.dp))
                    Text("Back", style = RodType.LabelCaps, color = RodColors.TextSecondary)
                }
                Button(
                    onClick = onNext,
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RodColors.Primary,
                        contentColor = RodColors.OnPrimary
                    ),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 32.dp, vertical = 12.dp)
                ) {
                    Text("Next", style = RodType.LabelCaps)
                    Spacer(Modifier.width(8.dp))
                    Icon(Icons.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(18.dp))
                }
            }
        }
    }
}

@Composable
private fun MiniDetectionTile(label: String, icon: ImageVector, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(RodColors.SurfaceContainer)
            .border(2.dp, RodColors.Primary, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Icon(icon, contentDescription = label, tint = RodColors.Primary.copy(alpha = 0.6f), modifier = Modifier.size(24.dp))
    }
}

@Composable
private fun FloatingIcon(icon: ImageVector, tint: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, RodColors.BorderLight, RoundedCornerShape(16.dp))
            .padding(12.dp)
    ) {
        Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.size(28.dp))
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FeatureChip(text: String, icon: ImageVector, highlighted: Boolean = false) {
    val bg = if (highlighted) RodColors.Primary.copy(alpha = 0.1f) else RodColors.SurfaceContainerHigh
    val border = if (highlighted) RodColors.Primary.copy(alpha = 0.2f) else RodColors.OutlineVariant.copy(alpha = 0.3f)
    val fg = if (highlighted) RodColors.Primary else RodColors.OnSurfaceVariant
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(bg)
            .border(1.dp, border, CircleShape)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(icon, contentDescription = null, tint = fg, modifier = Modifier.size(16.dp))
        Text(text = text, style = RodType.LabelCaps, color = fg, fontWeight = FontWeight.SemiBold)
    }
}
