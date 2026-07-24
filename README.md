# AgriInvestment — Dossier de travail (Android · Kotlin · Jetpack Compose)

Ce dossier contient le **socle du projet** AgriInvestment, développé du lancement de
l'application jusqu'au **Tableau de bord (Dashboard)** inclus, conformément au cahier
des charges fourni.

## ✅ Ce qui est implémenté dans cette itération

| Écran | Statut | Fichier |
|---|---|---|
| Splash Screen | ✅ | `ui/screens/SplashScreen.kt` |
| Onboarding (3 écrans) | ✅ | `ui/screens/OnboardingScreen.kt` |
| Connexion | ✅ | `ui/screens/LoginScreen.kt` |
| Inscription | ✅ | `ui/screens/SignupScreen.kt` |
| OTP (6 cases, compte à rebours) | ✅ | `ui/screens/OtpScreen.kt` |
| Création du profil | ✅ | `ui/screens/ProfileSetupScreen.kt` |
| **Tableau de bord (Dashboard)** | ✅ | `ui/screens/DashboardScreen.kt` |
| Navigation basse (5 onglets) | ✅ (Accueil actif, autres en stub) | `ui/screens/MainHostScreen.kt` |

Les écrans suivants du cahier des charges (Recherche, Détail projet, Investissement,
Paiement, Portefeuille détaillé, Historique, Marketplace, Notifications, Profil,
Paramètres, Parrainage, Formation, back-office admin) **ne sont pas encore développés** :
ce sont les prochaines étapes logiques, une fois le socle validé.

## 🎨 Respect de la charte

- **Couleurs** : palette exacte (`ui/theme/Color.kt`) — vert forêt `#1B5E20`, vert clair
  `#43A047`, or `#D4AF37`, gris clair `#F5F7FA`, succès `#00C853`, erreur `#E53935`.
- **Typographie** : `ui/theme/Type.kt` prépare la police **Poppins** (Bold / SemiBold /
  Regular / Medium). Les fichiers `.ttf` ne sont pas inclus (contraintes réseau de cet
  environnement) — voir la section « Activer la police Poppins » ci-dessous.
- **Cartes à grands coins arrondis**, **boutons avec effet d'élévation léger**, espaces
  généreux : voir `ui/theme/Theme.kt` et `ui/components/`.

## 📂 Structure du projet

```
AgriInvestment/
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/agriinvestment/app/
│       │   ├── MainActivity.kt
│       │   ├── model/Project.kt
│       │   ├── navigation/ (Screen.kt, AppNavigation.kt)
│       │   └── ui/
│       │       ├── theme/ (Color.kt, Type.kt, Theme.kt)
│       │       ├── components/ (PrimaryButton, ProjectCard, StatCard)
│       │       └── screens/ (Splash, Onboarding, Login, Signup, Otp,
│       │                     ProfileSetup, Dashboard, MainHost, Placeholder)
│       └── res/
│           ├── drawable/logo_agriinvestment.png
│           ├── mipmap-anydpi-v26/ic_launcher.xml
│           └── values/ (strings.xml, themes.xml, colors_launcher.xml)
├── gradle/libs.versions.toml   ← catalogue de versions (fourni par vous, complété)
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```

## 🚀 Ouvrir le projet

1. Ouvrez **Android Studio** (Ladybug ou plus récent recommandé pour AGP 8.10 / Kotlin 2.0).
2. `File > Open` → sélectionnez le dossier `AgriInvestment/`.
3. Android Studio régénère automatiquement le wrapper Gradle
   (`gradlew`, `gradle-wrapper.jar`) à la première synchronisation — ces fichiers
   binaires n'ont pas pu être générés dans cet environnement sans accès réseau à
   `services.gradle.org`.
4. Lancez l'app sur un émulateur ou un appareil (minSdk 26 / Android 8.0+).

Le flux de navigation par défaut est :
**Splash → Onboarding → Connexion → (Inscription → OTP → Profil) → Dashboard**

## 🖋️ Activer la police Poppins

Le projet est prêt à recevoir la vraie police Poppins :
1. Téléchargez les fichiers `.ttf` (Regular, Medium, SemiBold, Bold) depuis Google Fonts.
2. Placez-les dans `app/src/main/res/font/` sous les noms :
   `poppins_regular.ttf`, `poppins_medium.ttf`, `poppins_semibold.ttf`, `poppins_bold.ttf`.
3. Dans `ui/theme/Type.kt`, passez `hasCustomFont` à `true`.

En attendant, l'app utilise la police système (`FontFamily.SansSerif`) afin de
compiler sans dépendance externe.

## 🖼️ Images des projets

Les cartes de projets (`ProjectCard.kt`) utilisent des blocs de couleur en attendant
les vraies photos des agriculteurs/parcelles. Remplacez la `Box` colorée par un
`AsyncImage` (Coil, déjà inclus dans les dépendances) pointant vers vos URLs d'images
réelles dès qu'elles seront disponibles.

## 🔜 Prochaines étapes suggérées

1. Écran **Recherche** + filtres (culture, montant, ROI, durée, ville, risque).
2. Écran **Détail d'un projet** (galerie, carte GPS, calendrier, documents).
3. Flux **Investissement → Paiement (Orange Money / MTN Money / Wave) → Confirmation**.
4. **Portefeuille** avec graphique interactif (ex. bibliothèque Vico ou MPAndroidChart).
5. **Marketplace**, **Notifications**, **Paramètres**, **Parrainage**, **Formation**.
6. **Back-office administrateur** (probablement un module web Next.js séparé,
   connecté à la même base Firebase/Supabase).
7. Brancher une vraie base de données (Firebase ou Supabase) à la place des
   `SampleData` statiques dans `model/Project.kt`.
