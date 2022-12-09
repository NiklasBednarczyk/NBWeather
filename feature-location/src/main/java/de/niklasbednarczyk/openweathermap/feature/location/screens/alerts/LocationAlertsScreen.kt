package de.niklasbednarczyk.openweathermap.feature.location.screens.alerts

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.border.owmBorder
import de.niklasbednarczyk.openweathermap.core.ui.expandable.OwmExpandableView
import de.niklasbednarczyk.openweathermap.core.ui.resource.OwmResourceView
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmScaffold
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.topappbar.OwmTopAppBar
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.theme.*
import de.niklasbednarczyk.openweathermap.feature.location.screens.alerts.models.LocationAlertExpandableItem

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
        OwmResourceView(uiState.value) {
            LazyColumn(
                contentPadding = listContentPaddingValues
            ) {
                items(uiState.value.alerts) { alert ->
                    OwmExpandableView(
                        canBeExpanded = alert.expandableItems.isNotEmpty(),
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
                                    .padding(vertical = columnVerticalArrangementDefaultDp),
                                verticalArrangement = columnVerticalArrangementDefault
                            ) {
                                alert.expandableItems.forEach { expandableItem ->
                                    when (expandableItem) {
                                        is LocationAlertExpandableItem.Description -> {
                                            Text(
                                                text = expandableItem.text.asString(),
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                        is LocationAlertExpandableItem.Sender -> {
                                            Text(
                                                text = expandableItem.text.asString(),
                                                style = MaterialTheme.typography.bodySmall
                                            )
                                        }
                                        is LocationAlertExpandableItem.Tags -> {
                                            FlowRow(
                                                mainAxisSpacing = flowRowMainAxisSpacing,
                                                crossAxisSpacing = flowRowCrossAxisSpacing
                                            ) {
                                                expandableItem.tags.forEach { tag ->
                                                    Text(
                                                        modifier = Modifier
                                                            .owmBorder(
                                                                shape = MaterialTheme.shapes.extraSmall
                                                            )
                                                            .padding(tagPadding),
                                                        text = tag.asString(),
                                                        style = MaterialTheme.typography.bodySmall
                                                    )
                                                }
                                            }
                                        }
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

