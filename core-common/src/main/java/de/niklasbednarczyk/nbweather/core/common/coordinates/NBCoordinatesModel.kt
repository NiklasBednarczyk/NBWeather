package de.niklasbednarczyk.nbweather.core.common.coordinates

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NBCoordinatesModel(
    val latitude: Double,
    val longitude: Double
) : Parcelable
