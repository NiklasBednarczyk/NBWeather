package de.niklasbednarczyk.openweathermap.feature.location.screens.overview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconButton
import de.niklasbednarczyk.openweathermap.core.ui.resource.ResourceView
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmCenterAlignedTopAppBar
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmScaffold
import de.niklasbednarczyk.openweathermap.core.ui.strings.toStringOrEmpty
import de.niklasbednarczyk.openweathermap.feature.location.icons.LocationIcons

@Composable
fun LocationOverviewScreen(
    viewModel: LocationOverviewViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit,
    navigateToSearch: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    ResourceView(
        resource = uiState.value.locationResource
    ) { location ->
        OwmScaffold(
            topBar = { scrollBehavior ->
                OwmCenterAlignedTopAppBar(
                    scrollBehavior = scrollBehavior,
                    navigationIcon = navigationIcon,
                    title = location?.localizedName.toStringOrEmpty(),
                    actions = {
                        OwmIconButton(
                            icon = LocationIcons.Search,
                            onClick = navigateToSearch
                        )
                    }
                )
            }
        ) {
        }
    }


}