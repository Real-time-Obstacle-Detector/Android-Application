package com.example.realtime_obstacle_detection.ui.screens.settings

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realtime_obstacle_detection.ui.screens.initialConfigurations.ModelConfig
import com.example.realtime_obstacle_detection.ui.screens.initialConfigurations.Models
import com.example.realtime_obstacle_detection.ui.theme.RodColors
import com.example.realtime_obstacle_detection.ui.theme.RodType
import kotlin.math.roundToInt

/**
 * Settings page — translated from the initial_configurations design (provided HTML).
 * Replaces the old configuration dialog. Choices are persisted via
 * [ConfigPreferences] and reused by every detection action.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onAbout: () -> Unit
) {
    val context = LocalContext.current
    val saved = remember { ConfigPreferences.loadModelConfig(context) }

    var selectedModel by remember { mutableStateOf(saved.selectedModel) }
    var configThreshold by remember { mutableFloatStateOf(saved.configThreshold) }
    var iouThreshold by remember { mutableFloatStateOf(saved.iouThreshold) }
    var threadCount by remember { mutableIntStateOf(saved.threadCount) }
    var useNNAPI by remember { mutableStateOf(saved.useNNAPI) }
    var dropdownExpanded by remember { mutableStateOf(false) }

    fun persist() {
        ConfigPreferences.saveModelConfig(
            context,
            ModelConfig(selectedModel, configThreshold, iouThreshold, threadCount, useNNAPI)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RodColors.Surface)
            .systemBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top app bar with back button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.8f))
                    .height(44.dp)
                    .padding(horizontal = 12.dp)
                    .clickable { persist(); onBack() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.ArrowBackIos, contentDescription = "Back", tint = RodColors.Primary, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(4.dp))
                Text("ROD", style = RodType.HeadlineSm, color = RodColors.Primary)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .widthIn(max = 576.dp)
                    .padding(horizontal = 20.dp)
                    .padding(top = 12.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Configuration banner
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(RodColors.SurfaceContainerHighest),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Configuration", style = RodType.HeadlineMd, color = Color.White)
                }

                // Section: Model selection
                SettingsSection("MODEL SELECTION") {
                    GlassCard {
                        Text(
                            "Active Neural Architecture",
                            style = RodType.BodyMd,
                            color = RodColors.OnSurfaceVariant
                        )
                        Spacer(Modifier.height(8.dp))
                        ExposedDropdownMenuBox(
                            expanded = dropdownExpanded,
                            onExpandedChange = { dropdownExpanded = it }
                        ) {
                            Row(
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth()
                                    .height(44.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.White)
                                    .border(1.dp, RodColors.BorderLight, RoundedCornerShape(12.dp))
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    selectedModel.displayName,
                                    style = RodType.BodyLg,
                                    color = RodColors.Primary,
                                    modifier = Modifier.weight(1f)
                                )
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownExpanded)
                            }
                            ExposedDropdownMenu(
                                expanded = dropdownExpanded,
                                onDismissRequest = { dropdownExpanded = false },
                                modifier = Modifier.background(Color.White)
                            ) {
                                Models.entries.forEach { model ->
                                    DropdownMenuItem(
                                        text = { Text(model.displayName, color = RodColors.OnSurface) },
                                        onClick = {
                                            selectedModel = model
                                            dropdownExpanded = false
                                            persist()
                                        },
                                        colors = MenuDefaults.itemColors(
                                            textColor = RodColors.OnSurface
                                        )
                                    )
                                }
                            }
                        }
                    }
                }

                // Section: Thresholds
                SettingsSection("THRESHOLDS") {
                    GlassCard {
                        SettingsSlider(
                            label = "Config Threshold",
                            value = configThreshold,
                            valueText = "%.2f".format(configThreshold),
                            valueRange = 0f..1f,
                            steps = 19,
                            onValueChange = { configThreshold = it },
                            onValueChangeFinished = { persist() },
                            helper = null
                        )
                        Spacer(Modifier.height(10.dp))
                        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(RodColors.OutlineVariant.copy(alpha = 0.3f)))
                        Spacer(Modifier.height(10.dp))
                        SettingsSlider(
                            label = "IoU Threshold",
                            value = iouThreshold,
                            valueText = "%.2f".format(iouThreshold),
                            valueRange = 0f..1f,
                            steps = 19,
                            onValueChange = { iouThreshold = it },
                            onValueChangeFinished = { persist() },
                            helper = null
                        )
                    }
                }

                // Section: Performance
                SettingsSection("PERFORMANCE") {
                    GlassCard {
                        SettingsSlider(
                            label = "Thread Count",
                            value = threadCount.toFloat(),
                            valueText = threadCount.toString(),
                            valueRange = 1f..8f,
                            steps = 6,
                            onValueChange = { threadCount = it.roundToInt() },
                            onValueChangeFinished = { persist() },
                            helper = null
                        )
                        Spacer(Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text("Use NNAPI?", style = RodType.BodyLg, color = RodColors.OnSurface)
                                Text("Hardware acceleration", style = RodType.BodyMd, color = RodColors.TextSecondary)
                            }
                            Switch(
                                checked = useNNAPI,
                                onCheckedChange = { useNNAPI = it; persist() },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = RodColors.PrimaryContainer,
                                    uncheckedThumbColor = Color.White,
                                    uncheckedTrackColor = RodColors.OutlineVariant,
                                    uncheckedBorderColor = RodColors.OutlineVariant
                                )
                            )
                        }
                    }
                }

            }
        }
    }
}

@Composable
private fun SettingsSection(title: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(title, style = RodType.LabelCaps, color = RodColors.OnSurfaceVariant, modifier = Modifier.padding(horizontal = 8.dp))
        content()
    }
}

@Composable
private fun GlassCard(content: @Composable androidx.compose.foundation.layout.ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .border(1.dp, RodColors.BorderLight, RoundedCornerShape(20.dp))
            .padding(14.dp),
        content = content
    )
}

@Composable
private fun SettingsSlider(
    label: String,
    value: Float,
    valueText: String,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: () -> Unit,
    helper: String?
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(label, style = RodType.BodyLg, color = RodColors.OnSurface)
            Text(valueText, style = RodType.HeadlineSm, color = RodColors.Primary)
        }
        Slider(
            value = value,
            onValueChange = onValueChange,
            onValueChangeFinished = onValueChangeFinished,
            valueRange = valueRange,
            steps = steps,
            colors = SliderDefaults.colors(
                thumbColor = RodColors.PrimaryContainer,
                activeTrackColor = RodColors.PrimaryContainer,
                inactiveTrackColor = RodColors.BorderLight
            )
        )
        if (helper != null) {
            Text(helper, style = RodType.BodyMd, color = RodColors.TextSecondary)
        }
    }
}
