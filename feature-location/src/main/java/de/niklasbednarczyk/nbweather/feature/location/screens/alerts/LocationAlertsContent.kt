package de.niklasbednarczyk.nbweather.feature.location.screens.alerts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.flowlayout.FlowRow
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.border.nbBorder
import de.niklasbednarczyk.nbweather.core.ui.expandable.NBExpandableView
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithLoadingView
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementDefault
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementDefaultDp
import de.niklasbednarczyk.nbweather.core.ui.dimens.flowRowCrossAxisSpacing
import de.niklasbednarczyk.nbweather.core.ui.dimens.flowRowMainAxisSpacing
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.listItemPaddingValuesHorizontal
import de.niklasbednarczyk.nbweather.core.ui.dimens.tagPadding
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.common.time
import de.niklasbednarczyk.nbweather.feature.location.screens.alerts.models.LocationAlertExpandableItem

@Composable
fun LocationAlertsContent(
    uiState: LocationAlertsUiState
) {
    NBResourceWithLoadingView(uiState.alertsResource) { alerts ->
        LazyColumn(
            contentPadding = listContentPaddingValuesVertical
        ) {
            items(alerts) { alert ->
                NBExpandableView(
                    canBeExpanded = alert.expandableItems.isNotEmpty(),
                    header = { icon ->
                        ListItem(
                            headlineContent = {
                                Text(alert.eventName.asString())
                            },
                            supportingContent = {
                                val startAndEndDate = NBString.Resource(
                                    R.string.format_range_2_items,
                                    alert.startDate.time,
                                    alert.endDate.time
                                )
                                Text(startAndEndDate.asString())
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