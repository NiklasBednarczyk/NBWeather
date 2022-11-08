package de.niklasbednarczyk.openweathermap.feature.location.screens.alerts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.border.owmBorder
import de.niklasbednarczyk.openweathermap.core.ui.expandable.OwmExpandableView
import de.niklasbednarczyk.openweathermap.core.ui.resource.OwmResourceView
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmScaffold
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmTopAppBar
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.theme.listContentPaddingValues
import de.niklasbednarczyk.openweathermap.core.ui.theme.listItemPaddingValuesHorizontal

private val verticalPadding = 16.dp

@Composable
fun LocationAlertsScreen(
    viewModel: LocationAlertsViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    OwmScaffold(
        topBar = { scrollBehavior ->
            OwmTopAppBar(
                scrollBehavior = scrollBehavior,
                navigationIcon = navigationIcon,
                title = OwmString.Resource(R.string.screen_location_alert_overview_title)
            )
        }
    ) {

        OwmResourceView(resource = uiState.value.alertsResource) { alerts ->
            LazyColumn(
                contentPadding = listContentPaddingValues
            ) {
                items(alerts) { alert ->
                    OwmExpandableView(
                        header = { icon ->
                            ListItem(
                                headlineText = {
                                    Text(alert.eventName.asString())
                                },
                                supportingText = {
                                    Text(alert.startEndRange.asString())
                                },
                                trailingContent = icon
                            )
                        },
                        expandableContent = {
                            Column(
                                modifier = Modifier
                                    .padding(listItemPaddingValuesHorizontal)
                                    .padding(vertical = verticalPadding),
                                verticalArrangement = Arrangement.spacedBy(verticalPadding)
                            ) {
                                Text(
                                    text = alert.description.asString(),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = alert.senderName.asString(),
                                    style = MaterialTheme.typography.bodySmall
                                )
                                FlowRow(
                                    mainAxisSpacing = 8.dp,
                                    crossAxisSpacing = 4.dp
                                ) {
                                    alert.tags.forEach { tag ->
                                        Text(
                                            modifier = Modifier
                                                .owmBorder(
                                                    shape = MaterialTheme.shapes.extraSmall
                                                )
                                                .padding(8.dp),
                                            text = tag.asString(),
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }
                                }
                            }
                        }
                    )
                }


            }
        }
    }

}

