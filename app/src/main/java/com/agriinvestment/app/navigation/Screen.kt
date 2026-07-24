package com.agriinvestment.app.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Onboarding : Screen("onboarding")
    data object Login : Screen("login")
    data object Signup : Screen("signup")
    data object Otp : Screen("otp")
    data object ProfileSetup : Screen("profile_setup")
    data object Dashboard : Screen("dashboard")

    // Onglets de la navigation basse (stubs au-delà du tableau de bord)
    data object Invest : Screen("invest")
    data object Wallet : Screen("wallet")
    data object Marketplace : Screen("marketplace")
    data object Profile : Screen("profile")
}
