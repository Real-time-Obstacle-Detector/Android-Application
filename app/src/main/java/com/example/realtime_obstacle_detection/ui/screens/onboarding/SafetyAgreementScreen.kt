package com.example.realtime_obstacle_detection.ui.screens.onboarding

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.realtime_obstacle_detection.R
import com.example.realtime_obstacle_detection.ui.theme.RodColors
import com.example.realtime_obstacle_detection.ui.theme.RodType

/**
 * Onboarding splash 3 — Safety agreement / disclaimer.
 * Translated from design/onboarding_safety_agreement/code.html
 *
 * The CTA stays disabled until the agreement checkbox is checked.
 */
@Composable
fun SafetyAgreementScreen(
    onAgree: () -> Unit
) {
    var agreed by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RodColors.Surface)
            .systemBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top app bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(RodColors.Surface)
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RodWordmark()
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 164.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Calm caution illustration
                Box(
                    modifier = Modifier
                        .widthIn(max = 340.dp)
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(24.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = androidx.compose.ui.res.painterResource(R.drawable.onboarding_third_slider),
                        contentDescription = null,
                        contentScale = androidx.compose.ui.layout.ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                // Title + subtitle
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "We’re Here to Decrease Collision Risk — Not Guarantee Safety",
                        style = RodType.HeadlineLgMobile,
                        color = RodColors.TextPrimary,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "ROD is a research project and assistive awareness tool. It helps detect obstacles but cannot guarantee complete avoidance or perfect accuracy.",
                        style = RodType.BodyLg,
                        color = RodColors.TextSecondary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.widthIn(max = 384.dp)
                    )
                }

                // Legal card
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(24.dp))
                        .background(RodColors.SurfaceContainerLow)
                        .border(1.dp, RodColors.BorderLight, RoundedCornerShape(24.dp))
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(Icons.Filled.Info, contentDescription = null, tint = RodColors.Tertiary, modifier = Modifier.size(20.dp))
                        Text("IMPORTANT NOTICE", style = RodType.LabelCaps, color = RodColors.Tertiary)
                    }
                    Text(
                        text = "ROD is not a medical device. Results may be inaccurate due to lighting, angle, or motion blur. Users should always maintain secondary environmental awareness.",
                        style = RodType.BodyMd,
                        color = RodColors.OnSurfaceVariant
                    )
                }

                PageIndicators(pageCount = 3, activeIndex = 2)
            }
        }

        // Fixed bottom sheet: checkbox + CTA
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .background(Color.White.copy(alpha = 0.92f))
                .padding(horizontal = 20.dp)
                .padding(top = 14.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Agreement checkbox row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { agreed = !agreed },
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(if (agreed) RodColors.Primary else RodColors.SurfaceContainerHighest)
                        .border(
                            2.dp,
                            if (agreed) RodColors.Primary else RodColors.OutlineVariant,
                            RoundedCornerShape(6.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (agreed) {
                        Icon(Icons.Filled.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                    }
                }
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(color = RodColors.OnSurfaceVariant)) {
                            append("I have read and agree to the ")
                        }
                        withStyle(SpanStyle(color = RodColors.Primary, fontWeight = FontWeight.SemiBold)) { append("Terms") }
                        withStyle(SpanStyle(color = RodColors.OnSurfaceVariant)) { append(", ") }
                        withStyle(SpanStyle(color = RodColors.Primary, fontWeight = FontWeight.SemiBold)) { append("Privacy Policy") }
                        withStyle(SpanStyle(color = RodColors.OnSurfaceVariant)) { append(", ") }
                        withStyle(SpanStyle(color = RodColors.Primary, fontWeight = FontWeight.SemiBold)) { append("Safety Disclaimer") }
                        withStyle(SpanStyle(color = RodColors.OnSurfaceVariant)) { append(", and ") }
                        withStyle(SpanStyle(color = RodColors.Primary, fontWeight = FontWeight.SemiBold)) { append("Limitation of Liability") }
                        withStyle(SpanStyle(color = RodColors.OnSurfaceVariant)) { append(".") }
                    },
                    style = RodType.BodyMd
                )
            }

            // CTA
            Button(
                onClick = { if (agreed) onAgree() },
                enabled = agreed,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = RodColors.Primary,
                    contentColor = RodColors.OnPrimary,
                    disabledContainerColor = RodColors.Primary.copy(alpha = 0.3f),
                    disabledContentColor = RodColors.OnPrimary.copy(alpha = 0.6f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("I Agree & Continue", style = RodType.HeadlineSm)
                Spacer(Modifier.width(8.dp))
                Icon(Icons.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(24.dp))
            }
        }
    }
}
