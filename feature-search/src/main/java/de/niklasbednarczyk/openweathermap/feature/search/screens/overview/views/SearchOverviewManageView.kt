package de.niklasbednarczyk.openweathermap.feature.search.screens.overview.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.theme.buttonPaddingBetweenElements
import de.niklasbednarczyk.openweathermap.core.ui.uitext.OwmStringResource
import de.niklasbednarczyk.openweathermap.feature.search.screens.overview.LocationFoundType
import de.niklasbednarczyk.openweathermap.feature.search.screens.overview.SearchOverviewViewModel

@Composable
fun SearchOverviewManageView(
    findingLocationInProgress: Boolean,
    navigateToLocation: (Double, Double) -> Unit,
    onFindCurrentLocationClicked: (MultiplePermissionsState) -> Unit,
    onLocationPermissionResult: (Map<String, Boolean>) -> Unit,
    onLocationFound: (LocationFoundType) -> Unit,
) {

    FindCurrentLocation(
        findingLocationInProgress = findingLocationInProgress,
        navigateToLocation = navigateToLocation,
        onFindCurrentLocationClicked = onFindCurrentLocationClicked,
        onLocationPermissionResult = onLocationPermissionResult,
        onLocationFound = onLocationFound
    )


    // TODO (#10) Add VisitedLocation

    // TODO (#10) Make right design

}

@Composable
private fun FindCurrentLocation(
    findingLocationInProgress: Boolean,
    navigateToLocation: (Double, Double) -> Unit,
    onFindCurrentLocationClicked: (MultiplePermissionsState) -> Unit,
    onLocationPermissionResult: (Map<String, Boolean>) -> Unit,
    onLocationFound: (LocationFoundType) -> Unit,
) {
    val context = LocalContext.current

    val shouldShowButton = remember {
        GoogleApiAvailability.getInstance()
            .isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS
    }

    val fusedLocationProviderClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            SearchOverviewViewModel.LOCATION_PERMISSION_COARSE,
            SearchOverviewViewModel.LOCATION_PERMISSION_FINE
        ),
        onLocationPermissionResult
    )


    if (shouldShowButton) {
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

    LaunchedEffect(findingLocationInProgress) {
        if (findingLocationInProgress) {
            fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener { location ->
                    onLocationFound(LocationFoundType.SUCCESS)
                    navigateToLocation(location.latitude, location.longitude)
                }.addOnCanceledListener {
                    onLocationFound(LocationFoundType.CANCELED)
                }.addOnFailureListener {
                    onLocationFound(LocationFoundType.FAILURE)
                }
        }
    }


}
