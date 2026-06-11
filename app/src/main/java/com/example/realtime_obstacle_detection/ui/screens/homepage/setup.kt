package com.example.realtime_obstacle_detection.ui.screens.homepage

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.realtime_obstacle_detection.ui.model.navigation.HomePageNavGraph


/**
 * Sets up the navigation host and controller for the home page.
 *
 * This composable initializes a [rememberNavController] and provides it to the
 * [HomePageNavGraph] to manage navigation within the home section of the app.
 *
 * @param startDestination The initial route to be displayed within the home navigation graph.
 */
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
