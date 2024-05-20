package de.niklasbednarczyk.nbweather.feature.about.screens.overview

import de.niklasbednarczyk.nbweather.feature.about.screens.overview.models.AboutOverviewItem

data class AboutOverviewUiState(
    val items: List<AboutOverviewItem> = emptyList()
)