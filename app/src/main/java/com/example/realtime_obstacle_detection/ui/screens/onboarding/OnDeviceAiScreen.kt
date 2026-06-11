package com.example.realtime_obstacle_detection.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.VpnLock
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
                    .padding(top = 8.dp, bottom = 108.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Real-Time. Accurate. Private.",
                    style = RodType.HeadlineLgMobile,
                    color = RodColors.TextPrimary,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "ROD detects up to 25 common obstacle categories and runs directly on your phone. The model is trained on 24,326 images and optimized for mobile inference. No internet connection or cloud processing required.",
                    style = RodType.BodyMd,
                    color = RodColors.TextSecondary,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(16.dp))

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

                Spacer(Modifier.height(16.dp))

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
                    .padding(top = 12.dp, bottom = 4.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .wrapContentWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 6.dp, bottom = 10.dp),
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
