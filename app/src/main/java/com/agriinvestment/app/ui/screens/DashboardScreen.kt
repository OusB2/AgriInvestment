package com.agriinvestment.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.agriinvestment.app.model.SampleData
import com.agriinvestment.app.ui.components.ProjectCard
import com.agriinvestment.app.ui.components.StatCard
import com.agriinvestment.app.ui.theme.*

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 24.dp)
    ) {
        DashboardHeader()

        Spacer(modifier = Modifier.height(16.dp))

        InvestorPremiumCard(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        StatsGrid(modifier = Modifier.padding(horizontal = 20.dp))

        Spacer(modifier = Modifier.height(28.dp))

        SectionHeader(title = "Projets populaires", modifier = Modifier.padding(horizontal = 20.dp))
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(SampleData.popularProjects) { project ->
                ProjectCard(project = project)
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        SectionHeader(title = "Nouveaux projets", modifier = Modifier.padding(horizontal = 20.dp))
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(SampleData.newProjects) { project ->
                ProjectCard(project = project)
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        SectionHeader(title = "Dernières activités", modifier = Modifier.padding(horizontal = 20.dp))
        Spacer(modifier = Modifier.height(12.dp))
        RecentActivityList(modifier = Modifier.padding(horizontal = 20.dp))

        Spacer(modifier = Modifier.height(28.dp))

        SectionHeader(title = "Conseils IA", modifier = Modifier.padding(horizontal = 20.dp))
        Spacer(modifier = Modifier.height(12.dp))
        AiAdviceCard(modifier = Modifier.padding(horizontal = 20.dp))
    }
}

@Composable
private fun DashboardHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ForestGreen)
            .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Bonjour Ousmane \uD83D\uDC4B",
            style = MaterialTheme.typography.titleLarge,
            color = White,
            fontWeight = FontWeight.SemiBold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(White.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Notifications, contentDescription = "Notifications", tint = White)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Gold),
                contentAlignment = Alignment.Center
            ) {
                Text("OA", color = ForestGreenDark, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun InvestorPremiumCard(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = ForestGreen),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(22.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Montant investi", style = MaterialTheme.typography.bodySmall, color = White.copy(alpha = 0.8f))
                    Text(
                        "125 750 FCFA",
                        style = MaterialTheme.typography.headlineMedium,
                        color = White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(Gold)
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Star, contentDescription = null, tint = ForestGreenDark, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Investisseur Or", style = MaterialTheme.typography.labelSmall, color = ForestGreenDark, fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Rendement : +12 150 FCFA", style = MaterialTheme.typography.bodySmall, color = White.copy(alpha = 0.9f))
                Text("Progression Platine : 68%", style = MaterialTheme.typography.bodySmall, color = White.copy(alpha = 0.9f))
            }

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = { 0.68f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = Gold,
                trackColor = White.copy(alpha = 0.25f)
            )
        }
    }
}

@Composable
private fun StatsGrid(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
            StatCard(icon = Icons.Filled.TrendingUp, label = "Rendement moyen", value = "11,2%", modifier = Modifier.weight(1f))
            StatCard(icon = Icons.Filled.Savings, label = "Gains totaux", value = "12 150 FCFA", modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
            StatCard(icon = Icons.Filled.Groups, label = "Projets financés", value = "6", modifier = Modifier.weight(1f))
            StatCard(icon = Icons.Filled.Star, label = "Score investisseur", value = "820", modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun SectionHeader(title: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, style = MaterialTheme.typography.titleLarge, color = TextPrimary, fontWeight = FontWeight.SemiBold)
        TextButton(onClick = { /* TODO: voir tout */ }) {
            Text("Voir tout", color = ForestGreen)
        }
    }
}

private data class ActivityItem(val label: String, val detail: String)

@Composable
private fun RecentActivityList(modifier: Modifier = Modifier) {
    val activities = listOf(
        ActivityItem("Paiement reçu", "Culture de Maïs · +3 750 FCFA"),
        ActivityItem("Nouvelle vidéo", "Production d'Anacarde · rapport hebdomadaire"),
        ActivityItem("Nouveau projet disponible", "Riziculture Irriguée · Gagnoa")
    )

    Column(modifier = modifier) {
        activities.forEach { activity ->
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(LightGreen.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Filled.TrendingUp, contentDescription = null, tint = ForestGreen, modifier = Modifier.size(18.dp))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(activity.label, style = MaterialTheme.typography.titleSmall, color = TextPrimary)
                        Text(activity.detail, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                    }
                }
            }
        }
    }
}

@Composable
private fun AiAdviceCard(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.AutoAwesome, contentDescription = null, tint = Gold)
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    "Diversifiez vers l'élevage",
                    style = MaterialTheme.typography.titleSmall,
                    color = TextPrimary,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    "Votre portefeuille est concentré sur les céréales. Un projet d'élevage pourrait équilibrer votre risque.",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
        }
    }
}
