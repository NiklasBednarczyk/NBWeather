package de.niklasbednarczyk.nbweather.data.geocoding.repositories

import android.content.Context
import android.util.Log
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.qualifiers.ApplicationContext
import de.niklasbednarczyk.nbweather.core.data.localremote.constants.ConstantsCoreLocalRemote
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
        fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location ->
                val latitude = location.latitude
                val longitude = location.longitude
                onSuccess(latitude, longitude)
            }
            .addOnCanceledListener {
                onCanceled()
            }
            .addOnFailureListener { throwable ->
                //TODO (#5) Better logging
                Log.e(ConstantsCoreLocalRemote.Logging.TAG, throwable.message.toString())
                onFailure()
            }
    }

}