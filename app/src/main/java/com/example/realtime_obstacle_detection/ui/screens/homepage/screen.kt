package com.example.realtime_obstacle_detection.ui.screens.homepage

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.realtime_obstacle_detection.ui.activities.BlindDetectorActivity
import com.example.realtime_obstacle_detection.ui.activities.OnDetectionActivity
import com.example.realtime_obstacle_detection.ui.activities.WalkAroundActivity
import com.example.realtime_obstacle_detection.ui.theme.RodColors

/**
 * Main menu shell.
 * Section content lives in focused files beside this file.
 */
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
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HomeTopBar(
                onSettingsClick = { navController.navigate("settings") }
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .widthIn(max = 512.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 20.dp)
                    .padding(top = 8.dp, bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HomeIntroSection()

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                ModeSelectionSection(
                    selectedMode = selectedMode,
                    onModeSelected = { selectedMode = it }
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                HomeFeaturePills(
                    selectedMode = selectedMode
                )

                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = 14.dp)
                )

                HomeActions(
                    onStartDetection = { launchMode(context, selectedMode) },
                    onAboutClick = { navController.navigate("about_us") }
                )
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
