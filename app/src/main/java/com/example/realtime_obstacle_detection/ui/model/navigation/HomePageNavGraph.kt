package com.example.realtime_obstacle_detection.ui.model.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.realtime_obstacle_detection.ui.model.screens.HomeScreens
import com.example.realtime_obstacle_detection.ui.screens.about.AboutUsPageScreen
import com.example.realtime_obstacle_detection.ui.screens.homepage.HomePageScreen
import com.example.realtime_obstacle_detection.ui.screens.onboarding.OnDeviceAiScreen
import com.example.realtime_obstacle_detection.ui.screens.onboarding.SafetyAgreementScreen
import com.example.realtime_obstacle_detection.ui.screens.onboarding.WhoWeAreScreen
import com.example.realtime_obstacle_detection.ui.screens.settings.ConfigPreferences
import com.example.realtime_obstacle_detection.ui.screens.settings.SettingsScreen


@ExperimentalMaterialApi
@Composable
fun HomePageNavGraph(
    navHostController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // ----- Onboarding flow -----
        composable(route = HomeScreens.OnboardingWhoWeAre.route) {
            WhoWeAreScreen(
                onNext = { navHostController.navigate(HomeScreens.OnboardingOnDeviceAi.route) },
                onSkip = {
                    ConfigPreferences.setOnboardingCompleted(context, true)
                    navHostController.navigate(HomeScreens.MainMenu.route) {
                        popUpTo(HomeScreens.OnboardingWhoWeAre.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = HomeScreens.OnboardingOnDeviceAi.route) {
            OnDeviceAiScreen(
                onNext = { navHostController.navigate(HomeScreens.OnboardingSafety.route) },
                onBack = { navHostController.popBackStack() }
            )
        }

        composable(route = HomeScreens.OnboardingSafety.route) {
            SafetyAgreementScreen(
                onAgree = {
                    ConfigPreferences.setOnboardingCompleted(context, true)
                    navHostController.navigate(HomeScreens.MainMenu.route) {
                        popUpTo(HomeScreens.OnboardingWhoWeAre.route) { inclusive = true }
                    }
                }
            )
        }

        // ----- Main app -----
        composable(route = HomeScreens.MainMenu.route) {
            HomePageScreen(navController = navHostController)
        }

        composable(route = HomeScreens.Settings.route) {
            SettingsScreen(
                onBack = { navHostController.popBackStack() },
                onAbout = { navHostController.navigate(HomeScreens.AboutUs.route) }
            )
        }

        composable(route = HomeScreens.AboutUs.route) {
            AboutUsPageScreen()
        }
    }
}
