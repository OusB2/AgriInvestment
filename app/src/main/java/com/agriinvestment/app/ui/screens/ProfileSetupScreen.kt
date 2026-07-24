package com.agriinvestment.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.agriinvestment.app.ui.components.PrimaryButton
import com.agriinvestment.app.ui.theme.*

private val riskLevels = listOf("Prudent", "Modéré", "Dynamique")
private val crops = listOf("Maïs", "Anacarde", "Cacao", "Riz", "Ananas", "Volaille")

@Composable
fun ProfileSetupScreen(onFinished: () -> Unit) {
    var profession by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var monthlyBudget by remember { mutableStateOf("") }
    var goal by remember { mutableStateOf("") }
    var selectedRisk by remember { mutableStateOf(riskLevels[1]) }
    var selectedCrop by remember { mutableStateOf(crops[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Créez votre profil investisseur",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
                .background(LightGray),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Filled.AddAPhoto, contentDescription = "Ajouter une photo", tint = TextSecondary)
        }

        Spacer(modifier = Modifier.height(24.dp))

        val fieldShape = RoundedCornerShape(14.dp)

        OutlinedTextField(
            value = profession, onValueChange = { profession = it },
            label = { Text("Profession") }, singleLine = true, shape = fieldShape,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(14.dp))

        OutlinedTextField(
            value = city, onValueChange = { city = it },
            label = { Text("Ville") }, singleLine = true, shape = fieldShape,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(14.dp))

        OutlinedTextField(
            value = monthlyBudget, onValueChange = { monthlyBudget = it },
            label = { Text("Budget mensuel (FCFA)") }, singleLine = true, shape = fieldShape,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(14.dp))

        OutlinedTextField(
            value = goal, onValueChange = { goal = it },
            label = { Text("Objectif d'investissement") }, singleLine = true, shape = fieldShape,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Niveau de risque",
            style = MaterialTheme.typography.titleSmall,
            color = TextPrimary,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            riskLevels.forEach { level ->
                FilterChip(
                    selected = selectedRisk == level,
                    onClick = { selectedRisk = level },
                    label = { Text(level) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = ForestGreen,
                        selectedLabelColor = White
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Culture préférée",
            style = MaterialTheme.typography.titleSmall,
            color = TextPrimary,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))
        FlowRowCrops(crops = crops, selected = selectedCrop, onSelect = { selectedCrop = it })

        Spacer(modifier = Modifier.height(32.dp))

        PrimaryButton(text = "Terminer", onClick = onFinished)

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun FlowRowCrops(crops: List<String>, selected: String, onSelect: (String) -> Unit) {
    // Simple grid en lignes de 3 pour éviter une dépendance FlowRow supplémentaire.
    crops.chunked(3).forEach { rowCrops ->
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            rowCrops.forEach { crop ->
                FilterChip(
                    selected = selected == crop,
                    onClick = { onSelect(crop) },
                    label = { Text(crop) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = ForestGreen,
                        selectedLabelColor = White
                    )
                )
            }
        }
    }
}
