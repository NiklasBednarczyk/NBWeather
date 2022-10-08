package de.niklasbednarczyk.openweathermap.feature.search.screens.overview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconButtonEmpty
import de.niklasbednarczyk.openweathermap.core.ui.resource.ResourceView
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmScaffold
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmSmallTopAppBar

@Composable
fun SearchOverviewScreen(
    viewModel: SearchOverviewViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit,
    navigateToLocation: (Double?, Double?) -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    ResourceView(
        resource = uiState.value.currentLocationResource,
        loadingContent = {}
    ) { currentLocation ->
        val emptyIcon = @Composable { OwmIconButtonEmpty() }

        val navIcon = if (currentLocation != null) navigationIcon else emptyIcon

        OwmScaffold(
            topBar = { scrollBehavior ->
                //TODO (#10) Do with search top app bar
                OwmSmallTopAppBar(
                    scrollBehavior = scrollBehavior,
                    navigationIcon = navIcon,
                    title = "Search"
                )
            }
        ) {

        }

    }


}