package com.example.realtime_obstacle_detection.ui.model.screens


sealed class HomeScreens(val route: String) {

    // Onboarding splash flow
    object OnboardingWhoWeAre : HomeScreens(route = "onboarding_who_we_are")
    object OnboardingOnDeviceAi : HomeScreens(route = "onboarding_on_device_ai")
    object OnboardingSafety : HomeScreens(route = "onboarding_safety")

    // Main app
    object MainMenu : HomeScreens(route = "main_menu")
    object Settings : HomeScreens(route = "settings")
    object AboutUs : HomeScreens(route = "about_us")
}
