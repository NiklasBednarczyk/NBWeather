package de.niklasbednarczyk.nbweather.data.geocoding.repositories

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GmsLocationRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val googleApiAvailability: GoogleApiAvailability = GoogleApiAvailability.getInstance()
    private val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(
            context
        )

    val isGooglePlayServiceAvailable: Boolean
        get() = googleApiAvailability.isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS

    fun getCurrentLocation(
        onSuccess: (Double, Double) -> Unit,
        onCanceled: () -> Unit,
        onFailure: () -> Unit
    ) {
        val onCatch: (t: Throwable) -> Unit = { t ->
            Timber.e(t)
            onFailure()
        }
        try {
            fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener { location ->
                    try {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        onSuccess(latitude, longitude)
                    } catch (t: Throwable) {
                        onCatch(t)
                    }
                }
                .addOnCanceledListener {
                    onCanceled()
                }
                .addOnFailureListener { throwable ->
                    Timber.e(throwable)
                    onFailure()
                }
        } catch (t: Throwable) {
            onCatch(t)
        }

    }

}