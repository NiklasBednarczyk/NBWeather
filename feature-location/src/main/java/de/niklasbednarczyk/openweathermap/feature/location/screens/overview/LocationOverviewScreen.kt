package de.niklasbednarczyk.openweathermap.feature.location.screens.overview

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconButton
import de.niklasbednarczyk.openweathermap.core.ui.resource.ResourceView
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmCenterAlignedTopAppBar
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmScaffold
import de.niklasbednarczyk.openweathermap.feature.location.icons.LocationIcons

@Composable
fun LocationOverviewScreen(
    viewModel: LocationOverviewViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit,
    navigateToSearch: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    ResourceView(uiState.value.locationResource) { location ->
        OwmScaffold(
            topBar = { scrollBehavior ->
                OwmCenterAlignedTopAppBar(
                    scrollBehavior = scrollBehavior,
                    navigationIcon = navigationIcon,
                    title = location.localizedName.toString(), //TODO (#9) Replace with actual location name
                    actions = {
                        OwmIconButton(
                            icon = LocationIcons.Search,
                            onClick = navigateToSearch
                        )
                    }
                )
            }
        ) {
            //TODO (#9) Do right design

            ResourceView(uiState.value.oneCallResource) { oneCall ->
                Text(text = oneCall.metadata.timezoneOffset.toString())
            }
        }
    }


}