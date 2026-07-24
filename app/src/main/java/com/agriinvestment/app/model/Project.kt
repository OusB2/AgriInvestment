package com.agriinvestment.app.model

/**
 * Représente un projet agricole proposé aux investisseurs.
 */
data class Project(
    val id: String,
    val name: String,
    val farmerName: String,
    val isVerified: Boolean,
    val location: String,
    val roiPercent: Double,
    val durationMonths: Int,
    val progressPercent: Int,
    val investorsCount: Int,
    val minAmountFcfa: Long,
    val accentColorHex: Long
)

/** Jeu de données de démonstration pour peupler le tableau de bord. */
object SampleData {
    val popularProjects = listOf(
        Project(
            id = "p1",
            name = "Culture de Maïs",
            farmerName = "Kouadio Yao",
            isVerified = true,
            location = "Korhogo, Côte d'Ivoire",
            roiPercent = 12.0,
            durationMonths = 6,
            progressPercent = 75,
            investorsCount = 128,
            minAmountFcfa = 5_000,
            accentColorHex = 0xFF43A047
        ),
        Project(
            id = "p2",
            name = "Production d'Anacarde",
            farmerName = "Adjoua Marie",
            isVerified = true,
            location = "Bouaké, Côte d'Ivoire",
            roiPercent = 15.0,
            durationMonths = 8,
            progressPercent = 52,
            investorsCount = 94,
            minAmountFcfa = 5_000,
            accentColorHex = 0xFFD4AF37
        ),
        Project(
            id = "p3",
            name = "Élevage de Poulets",
            farmerName = "Traoré Ibrahim",
            isVerified = true,
            location = "Yamoussoukro, Côte d'Ivoire",
            roiPercent = 10.0,
            durationMonths = 4,
            progressPercent = 30,
            investorsCount = 61,
            minAmountFcfa = 10_000,
            accentColorHex = 0xFF1B5E20
        )
    )

    val newProjects = listOf(
        Project(
            id = "p4",
            name = "Riziculture Irriguée",
            farmerName = "N'Guessan Paul",
            isVerified = true,
            location = "Gagnoa, Côte d'Ivoire",
            roiPercent = 11.5,
            durationMonths = 5,
            progressPercent = 8,
            investorsCount = 6,
            minAmountFcfa = 5_000,
            accentColorHex = 0xFF43A047
        ),
        Project(
            id = "p5",
            name = "Plantation d'Ananas",
            farmerName = "Coulibaly Aminata",
            isVerified = false,
            location = "Aboisso, Côte d'Ivoire",
            roiPercent = 13.0,
            durationMonths = 9,
            progressPercent = 3,
            investorsCount = 2,
            minAmountFcfa = 5_000,
            accentColorHex = 0xFFD4AF37
        )
    )
}
