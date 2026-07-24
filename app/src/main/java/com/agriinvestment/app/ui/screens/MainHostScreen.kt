package com.agriinvestment.app.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.agriinvestment.app.navigation.Screen
import com.agriinvestment.app.ui.theme.ForestGreen
import com.agriinvestment.app.ui.theme.TextSecondary
import com.agriinvestment.app.ui.theme.White

private data class BottomTab(val screen: Screen, val label: String, val icon: ImageVector)

private val bottomTabs = listOf(
    BottomTab(Screen.Dashboard, "Accueil", Icons.Filled.Home),
    BottomTab(Screen.Invest, "Investir", Icons.Filled.TrendingUp),
    BottomTab(Screen.Wallet, "Portefeuille", Icons.Filled.Wallet),
    BottomTab(Screen.Marketplace, "Marketplace", Icons.Filled.Storefront),
    BottomTab(Screen.Profile, "Profil", Icons.Filled.Person)
)

/**
 * Conteneur principal après connexion : Dashboard + navigation basse à 5 onglets,
 * conformément à la structure de navigation du cahier des charges.
 */
@Composable
fun MainHostScreen() {
    var selectedTab by remember { mutableStateOf(Screen.Dashboard.route) }

    Scaffold(
        containerColor = White,
        bottomBar = {
            NavigationBar(containerColor = White, tonalElevation = 8.dp) {
                bottomTabs.forEach { tab ->
                    NavigationBarItem(
                        selected = selectedTab == tab.screen.route,
                        onClick = { selectedTab = tab.screen.route },
                        icon = { Icon(tab.icon, contentDescription = tab.label) },
                        label = { Text(tab.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = ForestGreen,
                            selectedTextColor = ForestGreen,
                            unselectedIconColor = TextSecondary,
                            unselectedTextColor = TextSecondary,
                            indicatorColor = White
                        )
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedTab) {
                Screen.Dashboard.route -> DashboardScreen()
                Screen.Invest.route -> PlaceholderScreen("Investir")
                Screen.Wallet.route -> PlaceholderScreen("Portefeuille")
                Screen.Marketplace.route -> PlaceholderScreen("Marketplace")
                Screen.Profile.route -> PlaceholderScreen("Profil")
            }
        }
    }
}
