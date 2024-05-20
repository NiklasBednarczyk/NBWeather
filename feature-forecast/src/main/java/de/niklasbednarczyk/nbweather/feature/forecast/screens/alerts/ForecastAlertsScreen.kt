package de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.border.nbBorder
import de.niklasbednarczyk.nbweather.core.ui.common.time
import de.niklasbednarczyk.nbweather.core.ui.context.startIntent
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementSmall
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenHorizontalPadding
import de.niklasbednarczyk.nbweather.core.ui.layout.NBFlowRow
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerView
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.NBScaffoldView
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.snackbar.NBSnackbarController.Companion.rememberNBSnackbarController
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.text.NBClickableText
import de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models.ForecastAlertsAlertInfoItem

@Composable
fun ForecastAlertsRoute(
    viewModel: ForecastAlertsViewModel = hiltViewModel(),
    popBackStack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ForecastAlertsScreen(
        uiState = uiState,
        popBackStack = popBackStack
    )
}

@Composable
internal fun ForecastAlertsScreen(
    uiState: ForecastAlertsUiState,
    popBackStack: () -> Unit
) {
    val context = LocalContext.current

    val snackbarController = rememberNBSnackbarController()

    NBScaffoldView(
        topAppBarItem = NBTopAppBarItem.Small(
            title = NBString.ResString(R.string.screen_forecast_alerts_title),
            popBackStack = popBackStack
        ),
        snackbarController = snackbarController
    ) {
        NBResourceWithoutLoadingView(uiState.viewDataResource) { viewData ->
            NBPagerView(viewData) { pagerItem ->
                LazyColumn(
                    modifier = Modifier.padding(
                        horizontal = screenHorizontalPadding
                    ),
                    contentPadding = listContentPaddingValuesVertical,
                    verticalArrangement = columnVerticalArrangementBig
                ) {
                    items(pagerItem.infoItems) { infoItem ->
                        InfoItem(
                            infoItem = infoItem,
                            startIntent = { intent ->
                                context.startIntent(
                                    intent = intent,
                                    showSnackbar = snackbarController::showSnackbar
                                )
                            }
                        )
                    }
                }
            }
        }
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
private fun InfoItem(
    infoItem: ForecastAlertsAlertInfoItem,
    startIntent: (Intent?) -> Unit
) {
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
    padding: Dp = 8.dp,
    shape: Shape = MaterialTheme.shapes.extraSmall
) {
    NBFlowRow {
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