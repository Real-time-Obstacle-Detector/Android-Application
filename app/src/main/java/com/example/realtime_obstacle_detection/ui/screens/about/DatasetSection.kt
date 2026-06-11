package com.example.realtime_obstacle_detection.ui.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.Stairs
import androidx.compose.material.icons.filled.Storage
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.realtime_obstacle_detection.R
import com.example.realtime_obstacle_detection.ui.theme.RodColors
import com.example.realtime_obstacle_detection.ui.theme.RodType
import com.example.realtime_obstacle_detection.ui.theme.toSp

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun DatasetAccordion() {
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
