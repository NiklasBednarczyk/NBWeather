package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallMetadataEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallModelLocal
import kotlinx.coroutines.flow.Flow

interface NBOneCallDao {

    fun getOneCall(latitude: Double?, longitude: Double?): Flow<OneCallModelLocal?>

    fun insertOneCallMetadata(oneCallMetadata: OneCallMetadataEntityLocal): Long

    fun deleteOneCallMetadata(id: Long?)

}