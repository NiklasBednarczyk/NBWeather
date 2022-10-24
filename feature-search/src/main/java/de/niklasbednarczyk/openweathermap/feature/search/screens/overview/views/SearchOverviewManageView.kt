package de.niklasbednarczyk.openweathermap.feature.search.screens.overview.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.theme.buttonPaddingBetweenElements
import de.niklasbednarczyk.openweathermap.core.ui.uitext.OwmStringResource
import de.niklasbednarczyk.openweathermap.feature.search.screens.overview.SearchOverviewViewModel

@Composable
fun SearchOverviewManageView(
    shouldShowFindLocation: Boolean,
    onFindCurrentLocationClicked: (MultiplePermissionsState) -> Unit,
    onLocationPermissionResult: (Map<String, Boolean>) -> Unit,
) {

    FindCurrentLocation(
        shouldShowFindLocation = shouldShowFindLocation,
        onFindCurrentLocationClicked = onFindCurrentLocationClicked,
        onLocationPermissionResult = onLocationPermissionResult,
    )


    // TODO (#10) Add VisitedLocation

    // TODO (#10) Make right design

}

@Composable
private fun FindCurrentLocation(
    shouldShowFindLocation: Boolean,
    onFindCurrentLocationClicked: (MultiplePermissionsState) -> Unit,
    onLocationPermissionResult: (Map<String, Boolean>) -> Unit,
) {

    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            SearchOverviewViewModel.LOCATION_PERMISSION_COARSE,
            SearchOverviewViewModel.LOCATION_PERMISSION_FINE
        ),
        onLocationPermissionResult
    )

    if (shouldShowFindLocation) {
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onFindCurrentLocationClicked(locationPermissionsState) }
        ) {
            OwmIcon(
                icon = OwmIcons.FindLocation
            )
            Spacer(modifier = Modifier.width(buttonPaddingBetweenElements))
            Text(
                text = OwmStringResource(R.string.screen_search_overview_manage_find_current_location).asString()
            )
        }
    }

}
