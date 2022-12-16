package de.niklasbednarczyk.nbweather.feature.location.screens.alerts

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
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.border.nbBorder
import de.niklasbednarczyk.nbweather.core.ui.expandable.NBExpandableView
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.core.ui.scaffold.NBScaffold
import de.niklasbednarczyk.nbweather.core.ui.scaffold.topappbar.NBTopAppBar
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.text.nbCombinedString
import de.niklasbednarczyk.nbweather.core.ui.theme.*
import de.niklasbednarczyk.nbweather.feature.location.screens.alerts.models.LocationAlertExpandableItem

@Composable
fun LocationAlertsScreen(
    viewModel: LocationAlertsViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    NBScaffold(
        topBar = { scrollBehavior ->
            NBTopAppBar(
                scrollBehavior = scrollBehavior,
                navigationIcon = navigationIcon,
                title = NBString.Resource(R.string.screen_location_alerts_title)
            )
        }
    ) {
        NBResourceWithoutLoadingView(uiState.value.alertsResource) { alerts ->
            LazyColumn(
                contentPadding = listContentPaddingValuesVertical
            ) {
                items(alerts) { alert ->
                    NBExpandableView(
                        canBeExpanded = alert.expandableItems.isNotEmpty(),
                        header = { icon ->
                            ListItem(
                                headlineText = {
                                    Text(alert.eventName.asString())
                                },
                                supportingText = {
                                    val text = nbCombinedString(
                                        alert.startDate,
                                        alert.endDate,
                                        formatResId = alert.startEndDateFormatResId
                                    )
                                    Text(text.asString())
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
                                                            .nbBorder(
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

