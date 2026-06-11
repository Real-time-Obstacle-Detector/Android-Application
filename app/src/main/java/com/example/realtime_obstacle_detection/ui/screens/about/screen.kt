package com.example.realtime_obstacle_detection.ui.screens.about

import androidx.compose.animation.animateContentSize
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
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Stairs
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.Traffic
import androidx.compose.material.icons.filled.TrendingUp
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realtime_obstacle_detection.R
import com.example.realtime_obstacle_detection.ui.theme.RodColors
import com.example.realtime_obstacle_detection.ui.theme.RodType
import com.example.realtime_obstacle_detection.ui.theme.toSp

/**
 * About page — translated from the about_rod design (provided HTML).
 * Intro, quick-action row, Dataset accordion, Research Papers accordion.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AboutUsPageScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RodColors.Background)
            .systemBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top app bar (glass)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.8f))
                    .border(
                        width = 1.dp,
                        color = RodColors.OutlineVariant.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(0.dp)
                    )
                    .height(44.dp)
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("ROD", style = RodType.HeadlineMd, color = RodColors.Primary)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .widthIn(max = 640.dp)
                    .padding(horizontal = 20.dp)
                    .padding(top = 16.dp, bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                // About header & intro
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("ABOUT PROJECT", style = RodType.LabelCaps, color = RodColors.Primary)
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

                // Quick actions
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(RodColors.SurfaceContainerLowest)
                        .border(1.dp, RodColors.OutlineVariant, RoundedCornerShape(16.dp))
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    QuickAction("GitHub", Icons.Filled.Code, Modifier.weight(1f))
                    QuickAction("Website", Icons.Filled.Language, Modifier.weight(1f))
                    QuickAction("LinkedIn", Icons.Filled.Share, Modifier.weight(1f))
                    QuickAction("HF", Icons.Filled.Storage, Modifier.weight(1f))
                    QuickAction("Kaggle", Icons.Filled.Terminal, Modifier.weight(1f))
                }

                // Dataset accordion
                DatasetAccordion()

                // Research Papers accordion
                ResearchPapersAccordion()
            }
        }
    }
}

@Composable
private fun QuickAction(label: String, icon: ImageVector, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DatasetAccordion() {
    var expanded by remember { mutableStateOf(false) }
    AccordionContainer(
        title = "Dataset",
        leadingIcon = Icons.Filled.Storage,
        expanded = expanded,
        onToggle = { expanded = !expanded }
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            // Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(192.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, RodColors.OutlineVariant, RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(R.drawable.about_dataset_banner),
                    contentDescription = "Dataset visualization",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, Color.Transparent, Color.Black.copy(alpha = 0.8f))
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text("Paper-level Instances", style = RodType.HeadlineSm, color = Color.White)
                    Spacer(Modifier.height(4.dp))
                    Text("31,331 verified high-fidelity annotations.", fontSize = 12.dp.toSp(), color = Color.White.copy(alpha = 0.8f))
                }
            }

            // Stats list
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                StatRow(Icons.Filled.Download, "32k+ downloads on Hugging Face")
                StatRow(Icons.Filled.TrendingUp, "500+ on Kaggle (May 2026 Trending)")
            }

            // Metrics
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MetricCard("24,326", "Annotated Images", Modifier.weight(1f))
                MetricCard("40,195", "Bounding Boxes", Modifier.weight(1f))
            }

            // Classes
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("CLASS ONTOLOGY (25 CLASSES)", style = RodType.LabelCaps, color = RodColors.OnSurface)
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    ClassChip("Person", Icons.Filled.Person, highlighted = true)
                    ClassChip("Car", Icons.Filled.DirectionsCar)
                    ClassChip("Stairs", Icons.Filled.Stairs)
                    ClassChip("Crosswalk", Icons.Filled.Traffic)
                    ClassChip("Manhole", Icons.Filled.RadioButtonChecked)
                    ClassChip("Tree", Icons.Filled.Park)
                    ClassChip("Bus", Icons.Filled.DirectionsBus)
                    PlainChip("+18 more")
                }
            }
        }
    }
}

@Composable
private fun ResearchPapersAccordion() {
    var expanded by remember { mutableStateOf(true) }
    AccordionContainer(
        title = "Research Papers",
        leadingIcon = Icons.Filled.Description,
        expanded = expanded,
        onToggle = { expanded = !expanded }
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            PaperRow("ROD: Real-time Obstacle Detector", "App & System Architecture")
            PaperRow("ROD-31k: A Large-scale Obstacle Dataset", "Dataset Analysis & Benchmarks")
        }
    }
}

@Composable
private fun AccordionContainer(
    title: String,
    leadingIcon: ImageVector,
    expanded: Boolean,
    onToggle: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(RodColors.SurfaceContainerLowest)
            .border(1.dp, RodColors.OutlineVariant, RoundedCornerShape(24.dp))
            .animateContentSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onToggle() }
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Icon(leadingIcon, contentDescription = null, tint = RodColors.Primary)
                Text(title, style = RodType.HeadlineMd, color = RodColors.OnSurface)
            }
            Icon(
                Icons.Filled.ExpandMore,
                contentDescription = null,
                tint = RodColors.OnSurfaceVariant,
                modifier = Modifier.rotate(if (expanded) 180f else 0f)
            )
        }
        if (expanded) {
            Box(modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp)) {
                content()
            }
        }
    }
}

@Composable
private fun StatRow(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(RodColors.SurfaceContainerLow)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(icon, contentDescription = null, tint = RodColors.Primary, modifier = Modifier.size(20.dp))
        Text(text, style = RodType.BodyMd, color = RodColors.OnSurface)
    }
}

@Composable
private fun MetricCard(value: String, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(RodColors.SurfaceContainerHigh)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(value, fontSize = 24.dp.toSp(), fontWeight = FontWeight.Bold, color = RodColors.Primary)
        Spacer(Modifier.height(2.dp))
        Text(label.uppercase(), fontSize = 10.dp.toSp(), style = RodType.LabelCaps, color = RodColors.OnSurfaceVariant)
    }
}

@Composable
private fun ClassChip(label: String, icon: ImageVector, highlighted: Boolean = false) {
    val bg = if (highlighted) RodColors.SecondaryContainer.copy(alpha = 0.2f) else RodColors.SurfaceContainerHigh
    val border = if (highlighted) RodColors.Secondary else RodColors.OutlineVariant
    val fg = if (highlighted) RodColors.OnSecondaryContainer else RodColors.OnSurfaceVariant
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(bg)
            .border(1.dp, border, CircleShape)
            .padding(horizontal = 12.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(icon, contentDescription = null, tint = fg, modifier = Modifier.size(14.dp))
        Text(label, fontSize = 12.dp.toSp(), color = fg)
    }
}

@Composable
private fun PlainChip(label: String) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(RodColors.SurfaceContainerHigh)
            .border(1.dp, RodColors.OutlineVariant, CircleShape)
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(label, fontSize = 12.dp.toSp(), color = RodColors.OnSurfaceVariant)
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
            Text(title, style = RodType.BodyMd, fontWeight = FontWeight.SemiBold, color = RodColors.OnSurface)
            Text(subtitle, fontSize = 12.dp.toSp(), color = RodColors.OnSurfaceVariant)
        }
        Spacer(Modifier.width(12.dp))
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(RodColors.Primary),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Filled.Download, contentDescription = "Download", tint = RodColors.OnPrimary, modifier = Modifier.size(20.dp))
        }
    }
}
