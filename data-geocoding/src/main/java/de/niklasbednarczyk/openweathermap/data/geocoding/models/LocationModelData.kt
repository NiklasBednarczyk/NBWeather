package de.niklasbednarczyk.openweathermap.data.geocoding.models

import de.niklasbednarczyk.openweathermap.core.common.data.DataLanguageType
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.models.LocationModelRemote
import de.niklasbednarczyk.openweathermap.data.geocoding.values.CoordinateValue

data class LocationModelData(
    val name: String?,
    val localNames: LocalNamesData?,
    val country: String?,
    val state: String?,
    val latitude: CoordinateValue,
    val longitude: CoordinateValue
) {

    companion object {

        internal fun remoteToData(
            remoteList: List<LocationModelRemote>
        ): List<LocationModelData> {
            return remoteList.map { remote ->
                LocationModelData(
                    name = remote.name,
                    localNames = LocalNamesData.remoteToData(remote.localNames),
                    country = remote.country,
                    state = remote.state,
                    latitude = CoordinateValue(remote.lat),
                    longitude = CoordinateValue(remote.lon),
                )
            }
        }

    }

    fun getLocalizedName(dataLanguage: DataLanguageType): String? {
        val localizedName = when (dataLanguage) {
            DataLanguageType.AFRIKAANS -> localNames?.af
            DataLanguageType.ALBANIAN -> localNames?.sq
            DataLanguageType.ARABIC -> localNames?.ar
            DataLanguageType.AZERBAIJANI -> localNames?.az
            DataLanguageType.BULGARIAN -> localNames?.bg
            DataLanguageType.CATALAN -> localNames?.ca
            DataLanguageType.CZECH -> localNames?.cs
            DataLanguageType.DANISH -> localNames?.da
            DataLanguageType.GERMAN -> localNames?.de
            DataLanguageType.GREEK -> localNames?.el
            DataLanguageType.ENGLISH -> localNames?.en
            DataLanguageType.BASQUE -> localNames?.eu
            DataLanguageType.PERSIAN_FARSI -> localNames?.fa
            DataLanguageType.FINNISH -> localNames?.fi
            DataLanguageType.FRENCH -> localNames?.fr
            DataLanguageType.GALICIAN -> localNames?.gl
            DataLanguageType.HEBREW -> localNames?.he
            DataLanguageType.HINDI -> localNames?.hi
            DataLanguageType.CROATIAN -> localNames?.hr
            DataLanguageType.HUNGARIAN -> localNames?.hu
            DataLanguageType.INDONESIAN -> localNames?.id
            DataLanguageType.ITALIAN -> localNames?.it
            DataLanguageType.JAPANESE -> localNames?.ja
            DataLanguageType.KOREAN -> localNames?.ko
            DataLanguageType.LATVIAN -> localNames?.lv
            DataLanguageType.LITHUANIAN -> localNames?.lt
            DataLanguageType.MACEDONIAN -> localNames?.mk
            DataLanguageType.NORWEGIAN -> localNames?.no
            DataLanguageType.DUTCH -> localNames?.nl
            DataLanguageType.POLISH -> localNames?.pl
            DataLanguageType.PORTUGUESE, DataLanguageType.PORTUGUES_BRASIL -> localNames?.pt
            DataLanguageType.ROMANIAN -> localNames?.ro
            DataLanguageType.RUSSIAN -> localNames?.ru
            DataLanguageType.SWEDISH -> localNames?.sv
            DataLanguageType.SLOVAK -> localNames?.sk
            DataLanguageType.SLOVENIAN -> localNames?.sl
            DataLanguageType.SPANISH -> localNames?.es
            DataLanguageType.SERBIAN -> localNames?.sr
            DataLanguageType.THAI -> localNames?.th
            DataLanguageType.TURKISH -> localNames?.tr
            DataLanguageType.UKRAINIAN -> localNames?.uk
            DataLanguageType.VIETNAMESE -> localNames?.vi
            DataLanguageType.CHINESE_SIMPLIFIED, DataLanguageType.CHINESE_TRADITIONAL -> localNames?.zh
            DataLanguageType.ZULU -> localNames?.zu
        }
        return localizedName ?: localNames?.en ?: name
    }


}