package de.niklasbednarczyk.nbweather.feature.location.cards.views.card

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridRowThreeLines
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementDefault
import de.niklasbednarczyk.nbweather.feature.location.cards.models.LocationCardSunAndMoonModel


@Composable
fun LocationCardSunAndMoonView(
    sunAndMoon: LocationCardSunAndMoonModel
) {
    Column(
        verticalArrangement = columnVerticalArrangementDefault,
    ) {
        NBGridRowThreeLines(
            items = sunAndMoon.sunItems
        )
        NBGridRowThreeLines(
            items = sunAndMoon.moonItems
        )
    }
}
