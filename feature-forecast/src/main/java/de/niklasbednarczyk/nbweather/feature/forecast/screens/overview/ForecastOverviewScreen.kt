package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.pulltorefresh.NBPullToRefreshView
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithLoadingView
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.core.ui.resource.text
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.NBScaffoldView
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.snackbar.NBSnackbarController.Companion.rememberNBSnackbarController
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.snackbar.NBSnackbarModel
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.topappbar.NBTopAppBarActionModel
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.settings.NBSettings
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.views.ForecastOverviewItemView

const val FORECAST_OVERVIEW_SCREEN_LAZY_COLUMN_TAG = "ForecastOverviewScreenLazyColumn"

@Composable
fun ForecastOverviewRoute(
    viewModel: ForecastOverviewViewModel = hiltViewModel(),
    openDrawer: () -> Unit,
    navigateToForecastAlerts: (coordinates: NBCoordinatesModel) -> Unit,
    navigateToForecastDaily: (forecastTime: Long?, coordinates: NBCoordinatesModel) -> Unit,
    navigateToForecastHourly: (coordinates: NBCoordinatesModel) -> Unit,
    navigateToSearchOverview: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ForecastOverviewScreen(
        uiState = uiState,
        openDrawer = openDrawer,
        navigateToForecastAlerts = navigateToForecastAlerts,
        navigateToForecastDaily = navigateToForecastDaily,
        navigateToForecastHourly = navigateToForecastHourly,
        navigateToSearchOverview = navigateToSearchOverview,
        refreshData = viewModel::refreshData,
    )
}

@Composable
internal fun ForecastOverviewScreen(
    uiState: ForecastOverviewUiState,
    openDrawer: () -> Unit,
    navigateToForecastAlerts: (coordinates: NBCoordinatesModel) -> Unit,
    navigateToForecastDaily: (forecastTime: Long?, coordinates: NBCoordinatesModel) -> Unit,
    navigateToForecastHourly: (coordinates: NBCoordinatesModel) -> Unit,
    navigateToSearchOverview: () -> Unit,
    refreshData: suspend (coordinates: NBCoordinatesModel) -> NBResource<Unit>,
) {
    val snackbarController = rememberNBSnackbarController()

    NBScaffoldView(
        topAppBarItem = NBTopAppBarItem.CenterAligned(
            title = uiState.locationResource.dataOrNull?.title,
            openDrawer = openDrawer,
            action = NBTopAppBarActionModel(
                icon = NBIcons.Search,
                onClick = navigateToSearchOverview
            )
        )
    ) {
        NBResourceWithoutLoadingView(uiState.locationResource) { location ->
            val listState = rememberLazyListState()

            NBPullToRefreshView(
                refreshData = {
                    refreshData(
                        coordinates = location.coordinates,
                        showSnackbar = snackbarController::showSnackbar,
                        refreshData = refreshData
                    )
                }
            ) { isRefreshing ->
                NBResourceWithLoadingView(uiState.itemsResource) { items ->
                    val order = NBSettings.order
                    val sortedItems = items.sortedBy { item ->
                        item.getSortOrder(order)
                    }

                    LazyColumn(
                        modifier = Modifier.testTag(FORECAST_OVERVIEW_SCREEN_LAZY_COLUMN_TAG),
                        state = listState,
                        contentPadding = listContentPaddingValuesVertical,
                        verticalArrangement = columnVerticalArrangementBig
                    ) {
                        items(sortedItems) { item ->
                            ForecastOverviewItemView(
                                item = item,
                                clickableEnabled = !isRefreshing,
                                navigateToForecastAlerts = {
                                    navigateToForecastAlerts(location.coordinates)
                                },
                                navigateToForecastDaily = { forecastTime ->
                                    navigateToForecastDaily(forecastTime, location.coordinates)
                                },
                                navigateToForecastHourly = {
                                    navigateToForecastHourly(location.coordinates)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

private suspend fun refreshData(
    coordinates: NBCoordinatesModel,
    showSnackbar: (snackbar: NBSnackbarModel) -> Unit,
    refreshData: suspend (coordinates: NBCoordinatesModel) -> NBResource<Unit>
) {
    val resource = refreshData(coordinates)

    if (resource is NBResource.Error) {
        val snackbar = NBSnackbarModel(
            message = resource.errorType.text
        )
        showSnackbar(snackbar)
    }
}