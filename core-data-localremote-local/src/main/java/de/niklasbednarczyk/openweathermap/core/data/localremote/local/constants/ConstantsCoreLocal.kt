package de.niklasbednarczyk.openweathermap.core.data.localremote.local.constants

import androidx.room.OnConflictStrategy

object ConstantsCoreLocal {

    object ColumnName {
        const val METADATA_ID_ENTITY = "metadataId"
        const val METADATA_ID_PARENT = "id"
    }

    object Dao {
        const val DEFAULT_ON_CONFLICT = OnConflictStrategy.REPLACE
    }

    internal object Timestamp {
        const val EPOCH_SECONDS = 600 // 10 minutes
    }

}