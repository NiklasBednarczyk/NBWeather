package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.today

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.LocationOverviewTodayAlertModel

@Composable
fun LocationOverviewTodayAlertView(
    alert: LocationOverviewTodayAlertModel,
    navigateToAlerts: () -> Unit
) {
    ListItem(
        modifier = Modifier.clickable {
            navigateToAlerts()
        },
        leadingContent = {
            OwmIcon(icon = OwmIcons.Warning)
        },
        headlineText = {
            Text(alert.text.asString())
        },
        trailingContent = {
            Text(alert.moreAlerts.asString())
        },
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            headlineColor = MaterialTheme.colorScheme.onSurfaceVariant,
            leadingIconColor = MaterialTheme.colorScheme.error,
            trailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )


}