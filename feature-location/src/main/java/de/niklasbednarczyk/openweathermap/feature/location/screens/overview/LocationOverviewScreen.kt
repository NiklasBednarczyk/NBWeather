package de.niklasbednarczyk.openweathermap.feature.location.screens.overview

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.resource.ResourceView
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmCenterAlignedTopAppBar
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmScaffold

@Composable
fun LocationOverviewScreen(
    viewModel: LocationOverviewViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    OwmScaffold(
        topBar = { scrollBehavior ->
            OwmCenterAlignedTopAppBar(
                scrollBehavior = scrollBehavior,
                navigationIcon = navigationIcon,
                title = "Location Name", // (#9) Replace with actual location name
                actions = {} //TODO (#10) Navigate to search
            )
        }
    ) {
        //TODO (#9) Do right design

        ResourceView(uiState.value.oneCallResource) { oneCall ->
            Text(text = oneCall.metadata.timezoneOffset.toString())
        }
    }

}