package com.agriinvestment.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agriinvestment.app.R
import com.agriinvestment.app.ui.components.PrimaryButton
import com.agriinvestment.app.ui.theme.*
import kotlinx.coroutines.launch

private data class OnboardingPage(
    val imageRes: Int,
    val title: String,
    val description: String
)

private val pages = listOf(
    OnboardingPage(
        imageRes = R.drawable.illustration_paysant,
        title = "Investir dans l'agriculture n'a jamais été si simple !",
        description = "Choisissez un projet, investissez dès 5 000 FCFA et suivez vos gains en temps réel."
    ),
    OnboardingPage(
        imageRes = R.drawable.illustration_suivi,
        title = "Suivez vos cultures en direct",
        description = "Photos, vidéos, GPS et rapports en temps réel sur chaque exploitation."
    ),
    OnboardingPage(
        imageRes = R.drawable.illustration_argent,
        title = "Recevez vos bénéfices facilement",
        description = "Retirez vos gains via Orange Money, MTN Money ou Wave."
    )
)

@Composable
fun OnboardingScreen(onFinished: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            val item = pages[page]

            // Image en plein écran (dans le pager) avec texte superposé en bas
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            ) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = item.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Dégradé sombre en bas pour que le texte blanc reste lisible
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.55f)
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.75f))
                            )
                        )
                )

                // Texte superposé en bas de l'image
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 28.dp)
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        lineHeight = 34.sp,
                        color = White,
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = White.copy(alpha = 0.9f),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }

        // Pagination + bouton, sous l'image
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pages.size) { index ->
                    val selected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(if (selected) 22.dp else 8.dp, 8.dp)
                            .clip(CircleShape)
                            .background(if (selected) ForestGreen else Divider)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            PrimaryButton(
                text = if (pagerState.currentPage == pages.lastIndex) "Commencer" else "Suivant",
                onClick = {
                    if (pagerState.currentPage == pages.lastIndex) {
                        onFinished()
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }
            )
        }
    }
}