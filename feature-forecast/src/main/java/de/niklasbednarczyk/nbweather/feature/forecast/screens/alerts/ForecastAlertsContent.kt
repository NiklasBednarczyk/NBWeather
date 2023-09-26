package de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.ui.border.nbBorder
import de.niklasbednarczyk.nbweather.core.ui.common.time
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementSmall
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenHorizontalPadding
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPager
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.text.NBClickableText
import de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models.ForecastAlertsAlertInfoItem

@Composable
fun ForecastAlertsContent(
    uiState: ForecastAlertsUiState,
    startIntent: (Intent?) -> Unit
) {
    NBResourceWithoutLoadingView(uiState.viewDataResource) { viewData ->
        NBPager(viewData) { pagerItem ->
            LazyColumn(
                modifier = Modifier.padding(
                    horizontal = screenHorizontalPadding
                ),
                contentPadding = listContentPaddingValuesVertical,
                verticalArrangement = columnVerticalArrangementBig
            ) {
                items(pagerItem.infoItems) { infoItem ->
                    when (infoItem) {
                        is ForecastAlertsAlertInfoItem.Dates -> {
                            Dates(
                                dates = infoItem
                            )
                        }

                        is ForecastAlertsAlertInfoItem.Description -> {
                            Description(
                                description = infoItem,
                                startIntent = startIntent
                            )
                        }

                        is ForecastAlertsAlertInfoItem.EventName -> {
                            EventName(
                                eventName = infoItem
                            )
                        }

                        is ForecastAlertsAlertInfoItem.SenderName -> {
                            SenderName(
                                senderName = infoItem,
                                startIntent = startIntent
                            )
                        }

                        is ForecastAlertsAlertInfoItem.Tags -> {
                            Tags(
                                tags = infoItem
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Dates(
    dates: ForecastAlertsAlertInfoItem.Dates
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Date(
            date = dates.startDate,
            horizontalAlignment = Alignment.Start
        )
        Date(
            date = dates.endDate,
            horizontalAlignment = Alignment.End
        )
    }
}

@Composable
private fun Date(
    date: NBDateTimeDisplayModel,
    horizontalAlignment: Alignment.Horizontal
) {
    Column(
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = columnVerticalArrangementSmall
    ) {
        Text(
            text = date.time.asString(),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = date.dateFull.asString(),
            style = MaterialTheme.typography.titleSmall
        )
    }
}


@Composable
private fun Description(
    description: ForecastAlertsAlertInfoItem.Description,
    startIntent: (Intent?) -> Unit
) {
    NBClickableText(
        text = description.text.asString(),
        style = MaterialTheme.typography.bodyLarge,
        startIntent = startIntent
    )
}

@Composable
private fun EventName(
    eventName: ForecastAlertsAlertInfoItem.EventName
) {
    Text(
        text = eventName.text.asString(),
        style = MaterialTheme.typography.headlineSmall,
    )
}

@Composable
private fun SenderName(
    senderName: ForecastAlertsAlertInfoItem.SenderName,
    startIntent: (Intent?) -> Unit
) {
    NBClickableText(
        text = senderName.text.asString(),
        style = MaterialTheme.typography.bodySmall,
        startIntent = startIntent
    )
}

@Composable
private fun Tags(
    tags: ForecastAlertsAlertInfoItem.Tags,
    horizontalSpacing: Dp = 8.dp,
    verticalSpacing: Dp = 4.dp,
    padding: Dp = 8.dp,
    shape: Shape = MaterialTheme.shapes.extraSmall
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
        verticalArrangement = Arrangement.spacedBy(verticalSpacing)
    ) {
        tags.tags.forEach { tag ->
            Text(
                modifier = Modifier
                    .nbBorder(
                        shape = shape
                    )
                    .padding(padding),
                text = tag.asString(),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}