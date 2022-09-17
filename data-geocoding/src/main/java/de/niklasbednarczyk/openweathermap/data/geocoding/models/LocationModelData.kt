package de.niklasbednarczyk.openweathermap.data.geocoding.models

import de.niklasbednarczyk.openweathermap.core.common.data.DataLanguageType
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.models.LocationModelRemote
import de.niklasbednarczyk.openweathermap.data.geocoding.values.CoordinateValue
import de.niklasbednarczyk.openweathermap.data.geocoding.values.CountryValue
import de.niklasbednarczyk.openweathermap.data.geocoding.values.LocationNameValue
import de.niklasbednarczyk.openweathermap.data.geocoding.values.StateValue

data class LocationModelData(
    val name: LocationNameValue,
    val country: CountryValue,
    val state: StateValue,
    val latitude: CoordinateValue,
    val longitude: CoordinateValue
) {

    companion object {

        internal fun remoteToData(
            remoteList: List<LocationModelRemote>,
            dataLanguage: DataLanguageType
        ): List<LocationModelData> {
            return remoteList.map { remote ->
                val localizedName = when (dataLanguage) {
                    DataLanguageType.AFRIKAANS -> remote.localNames?.af
                    DataLanguageType.ALBANIAN -> remote.localNames?.sq
                    DataLanguageType.ARABIC -> remote.localNames?.ar
                    DataLanguageType.AZERBAIJANI -> remote.localNames?.az
                    DataLanguageType.BULGARIAN -> remote.localNames?.bg
                    DataLanguageType.CATALAN -> remote.localNames?.ca
                    DataLanguageType.CZECH -> remote.localNames?.cs
                    DataLanguageType.DANISH -> remote.localNames?.da
                    DataLanguageType.GERMAN -> remote.localNames?.de
                    DataLanguageType.GREEK -> remote.localNames?.el
                    DataLanguageType.ENGLISH -> remote.localNames?.en
                    DataLanguageType.BASQUE -> remote.localNames?.eu
                    DataLanguageType.PERSIAN_FARSI -> remote.localNames?.fa
                    DataLanguageType.FINNISH -> remote.localNames?.fi
                    DataLanguageType.FRENCH -> remote.localNames?.fr
                    DataLanguageType.GALICIAN -> remote.localNames?.gl
                    DataLanguageType.HEBREW -> remote.localNames?.he
                    DataLanguageType.HINDI -> remote.localNames?.hi
                    DataLanguageType.CROATIAN -> remote.localNames?.hr
                    DataLanguageType.HUNGARIAN -> remote.localNames?.hu
                    DataLanguageType.INDONESIAN -> remote.localNames?.id
                    DataLanguageType.ITALIAN -> remote.localNames?.it
                    DataLanguageType.JAPANESE -> remote.localNames?.ja
                    DataLanguageType.KOREAN -> remote.localNames?.ko
                    DataLanguageType.LATVIAN -> remote.localNames?.lv
                    DataLanguageType.LITHUANIAN -> remote.localNames?.lt
                    DataLanguageType.MACEDONIAN -> remote.localNames?.mk
                    DataLanguageType.NORWEGIAN -> remote.localNames?.no
                    DataLanguageType.DUTCH -> remote.localNames?.nl
                    DataLanguageType.POLISH -> remote.localNames?.pl
                    DataLanguageType.PORTUGUESE, DataLanguageType.PORTUGUES_BRASIL -> remote.localNames?.pt
                    DataLanguageType.ROMANIAN -> remote.localNames?.ro
                    DataLanguageType.RUSSIAN -> remote.localNames?.ru
                    DataLanguageType.SWEDISH -> remote.localNames?.sv
                    DataLanguageType.SLOVAK -> remote.localNames?.sk
                    DataLanguageType.SLOVENIAN -> remote.localNames?.sl
                    DataLanguageType.SPANISH -> remote.localNames?.es
                    DataLanguageType.SERBIAN -> remote.localNames?.sr
                    DataLanguageType.THAI -> remote.localNames?.th
                    DataLanguageType.TURKISH -> remote.localNames?.tr
                    DataLanguageType.UKRAINIAN -> remote.localNames?.uk
                    DataLanguageType.VIETNAMESE -> remote.localNames?.vi
                    DataLanguageType.CHINESE_SIMPLIFIED, DataLanguageType.CHINESE_TRADITIONAL -> remote.localNames?.zh
                    DataLanguageType.ZULU -> remote.localNames?.zu
                }
                val name = localizedName ?: remote.localNames?.en ?: remote.name

                LocationModelData(
                    name = LocationNameValue(name),
                    country = CountryValue(remote.country),
                    state = StateValue(remote.state),
                    latitude = CoordinateValue(remote.lat),
                    longitude = CoordinateValue(remote.lon),
                )
            }
        }

    }


}