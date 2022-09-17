package de.niklasbednarczyk.openweathermap.feature.search.screens.overview

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.resource.ResourceView
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmScaffold
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmSmallTopAppBar

@Composable
fun SearchOverviewScreen(
    viewModel: SearchOverviewViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    OwmScaffold(
        topBar = { scrollBehavior ->
            //TODO (#10) Do with search top app bar
            OwmSmallTopAppBar(
                scrollBehavior = scrollBehavior,
                navigationIcon = navigationIcon,
                title = "Search"
            )
        }
    ) {

        ResourceView(
            resource = uiState.value.locationsResource,
            successContent = { locations ->
                LazyColumn {
                    items(locations) { location ->
                        Text(location.toString())
                    }
                }
            }
        )

    }

}