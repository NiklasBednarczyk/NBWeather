package de.niklasbednarczyk.openweathermap.feature.location.cards.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.core.ui.card.OwmCard
import de.niklasbednarczyk.openweathermap.core.ui.theme.columnVerticalArrangementDefault
import de.niklasbednarczyk.openweathermap.core.ui.theme.listContentPaddingValuesVertical
import de.niklasbednarczyk.openweathermap.feature.location.cards.models.*
import de.niklasbednarczyk.openweathermap.feature.location.cards.views.card.LocationCardWeatherView
import de.niklasbednarczyk.openweathermap.feature.location.cards.views.card.LocationCardOverviewView
import de.niklasbednarczyk.openweathermap.feature.location.cards.views.card.LocationCardSunAndMoonView
import de.niklasbednarczyk.openweathermap.feature.location.cards.views.card.LocationCardTemperaturesView

@Composable
fun LocationCardsView(
    cardItems: List<LocationCardItem>,
    navigateToAlerts: (() -> Unit)?,
) {
    LazyColumn(
        contentPadding = listContentPaddingValuesVertical,
        verticalArrangement = columnVerticalArrangementDefault
    ) {
        items(cardItems) { cardItem ->
            OwmCard(item = cardItem) { item ->
                when (item) {
                    is LocationCardWeatherModel -> {
                        LocationCardWeatherView(
                            weather = item
                        )
                    }
                    is LocationCardOverviewItem -> {
                        LocationCardOverviewView(
                            overviewItem = item,
                            navigateToAlerts = navigateToAlerts,
                        )
                    }
                    is LocationCardSunAndMoonModel -> {
                        LocationCardSunAndMoonView(
                            sunAndMoon = item
                        )
                    }
                    is LocationCardTemperaturesModel -> {
                        LocationCardTemperaturesView(
                            temperatures = item
                        )
                    }
                }
            }
        }
    }
}