package com.agriinvestment.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Police officielle : Poppins.
 *
 * NOTE : pour activer la vraie police Poppins :
 * 1. Ajoute les fichiers suivants dans res/font/ :
 *    poppins_regular.ttf, poppins_medium.ttf, poppins_semibold.ttf, poppins_bold.ttf
 * 2. Remplace la ligne ci-dessous par :
 *
 *    import androidx.compose.ui.text.font.Font
 *    import com.agriinvestment.app.R
 *
 *    val PoppinsFontFamily = FontFamily(
 *        Font(R.font.poppins_regular, FontWeight.Normal),
 *        Font(R.font.poppins_medium, FontWeight.Medium),
 *        Font(R.font.poppins_semibold, FontWeight.SemiBold),
 *        Font(R.font.poppins_bold, FontWeight.Bold)
 *    )
 *
 * Tant qu'aucun fichier .ttf n'est présent, R.font n'existe pas et toute référence à
 * R.font.xxx provoque une erreur de compilation "Unresolved reference 'font'" — même
 * dans une branche de code jamais exécutée. On utilise donc la police système en
 * attendant, afin que le projet compile sans assets externes.
 */
val PoppinsFontFamily: FontFamily = FontFamily.SansSerif

val AppTypography = Typography(
    displayLarge = TextStyle(fontFamily = PoppinsFontFamily, fontWeight = FontWeight.Bold, fontSize = 32.sp, lineHeight = 40.sp),
    headlineLarge = TextStyle(fontFamily = PoppinsFontFamily, fontWeight = FontWeight.Bold, fontSize = 28.sp, lineHeight = 34.sp),
    headlineMedium = TextStyle(fontFamily = PoppinsFontFamily, fontWeight = FontWeight.Bold, fontSize = 24.sp, lineHeight = 30.sp),
    headlineSmall = TextStyle(fontFamily = PoppinsFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 20.sp, lineHeight = 26.sp),
    titleLarge = TextStyle(fontFamily = PoppinsFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 18.sp, lineHeight = 24.sp),
    titleMedium = TextStyle(fontFamily = PoppinsFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, lineHeight = 22.sp),
    titleSmall = TextStyle(fontFamily = PoppinsFontFamily, fontWeight = FontWeight.Medium, fontSize = 14.sp, lineHeight = 20.sp),
    bodyLarge = TextStyle(fontFamily = PoppinsFontFamily, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 24.sp),
    bodyMedium = TextStyle(fontFamily = PoppinsFontFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp),
    bodySmall = TextStyle(fontFamily = PoppinsFontFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = 18.sp),
    labelLarge = TextStyle(fontFamily = PoppinsFontFamily, fontWeight = FontWeight.Medium, fontSize = 14.sp, lineHeight = 20.sp),
    labelMedium = TextStyle(fontFamily = PoppinsFontFamily, fontWeight = FontWeight.Medium, fontSize = 12.sp, lineHeight = 16.sp),
    labelSmall = TextStyle(fontFamily = PoppinsFontFamily, fontWeight = FontWeight.Medium, fontSize = 10.sp, lineHeight = 14.sp)
)