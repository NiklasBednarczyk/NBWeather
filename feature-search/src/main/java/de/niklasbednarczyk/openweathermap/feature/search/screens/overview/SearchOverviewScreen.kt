package de.niklasbednarczyk.openweathermap.feature.search.screens.overview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.icons.emptyIcon
import de.niklasbednarczyk.openweathermap.core.ui.resource.ResourceView
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmScaffold
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmSearchTopAppBar

@Composable
fun SearchOverviewScreen(
    viewModel: SearchOverviewViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit,
    navigateToLocation: (Double?, Double?) -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    ResourceView(
        resource = uiState.value.currentLocationResource
    ) { currentLocation ->
        val navIcon = if (currentLocation != null) navigationIcon else emptyIcon

        OwmScaffold(
            topBar = {
                OwmSearchTopAppBar(
                    searchTerm = uiState.value.searchTerm,
                    navigationIcon = navIcon,
                    onSearchTermChanged = viewModel::onSearchTermChanged
                )
            }
        ) {
        }

    }


}