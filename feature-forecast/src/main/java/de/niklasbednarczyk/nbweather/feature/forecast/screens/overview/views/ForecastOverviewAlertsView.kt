package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.views

import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcon
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewAlertsModel

@Composable
fun ForecastOverviewAlertsView(
    alerts: ForecastOverviewAlertsModel
) {
    ListItem(
        leadingContent = {
            NBIcon(icon = NBIcons.Warning)
        },
        headlineContent = {
            Text(text = alerts.text.asString())
        },
        trailingContent = {
            Text(text = alerts.otherAlertsText.asString())
        },
        colors = ListItemDefaults.colors(
            leadingIconColor = MaterialTheme.colorScheme.onErrorContainer,
            containerColor = MaterialTheme.colorScheme.errorContainer,
            headlineColor = MaterialTheme.colorScheme.onErrorContainer,
            trailingIconColor = MaterialTheme.colorScheme.onErrorContainer
        )
    )
}