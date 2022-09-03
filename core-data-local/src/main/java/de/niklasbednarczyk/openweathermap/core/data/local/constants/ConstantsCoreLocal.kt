package de.niklasbednarczyk.openweathermap.core.data.local.constants

import androidx.room.OnConflictStrategy

object ConstantsCoreLocal {

    object Dao {
        const val DEFAULT_ON_CONFLICT = OnConflictStrategy.REPLACE
    }

}