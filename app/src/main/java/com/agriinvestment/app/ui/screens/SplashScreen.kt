package com.agriinvestment.app.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.agriinvestment.app.R
import com.agriinvestment.app.ui.theme.ForestGreen
import com.agriinvestment.app.ui.theme.White
import kotlinx.coroutines.delay

// Couleurs spécifiques au splash (à déplacer dans Color.kt si tu préfères)
private val AgriGreen = Color(0xFF1E7A34)
private val AgriGold = Color(0xFFF6A821)
private val SubtitleGray = Color(0xFF4A4A4A)

/**
 * Écran de lancement : fond blanc, logo animé (pièce dollar + feuilles),
 * nom "AGRI" (vert) + "INVESTMENT" (orange), slogan, petit trait de séparation.
 * Navigue automatiquement après ~3s.
 */
@Composable
fun SplashScreen(onFinished: () -> Unit) {
    val scale = remember { Animatable(0.6f) }

    LaunchedEffect(Unit) {
        scale.animateTo(1f, animationSpec = tween(durationMillis = 900))
        delay(2100)
        onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logo_agriinvestment),
                contentDescription = "Logo AgriInvestment",
                modifier = Modifier
                    .size(160.dp)
                    .scale(scale.value)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = AgriGreen, fontWeight = FontWeight.ExtraBold)) {
                        append("AGRI")
                    }
                    withStyle(style = SpanStyle(color = AgriGold, fontWeight = FontWeight.ExtraBold)) {
                        append("INVESTMENT")
                    }
                },
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = stringResource(id = R.string.slogan),
                style = MaterialTheme.typography.bodyMedium,
                color = SubtitleGray,
                modifier = Modifier.padding(horizontal = 40.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Petit trait de séparation sous le slogan
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(3.dp)
                    .background(ForestGreen)
            )
        }
    }
}