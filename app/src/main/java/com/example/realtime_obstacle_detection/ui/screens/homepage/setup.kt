package com.example.realtime_obstacle_detection.ui.screens.homepage

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.realtime_obstacle_detection.ui.model.navigation.HomePageNavGraph


@ExperimentalMaterialApi
@Composable
fun HomePageSetUp(startDestination: String) {
    val navController = rememberNavController()
    HomePageNavGraph(
        navHostController = navController,
        startDestination = startDestination,
        modifier = Modifier.fillMaxSize()
    )
}
