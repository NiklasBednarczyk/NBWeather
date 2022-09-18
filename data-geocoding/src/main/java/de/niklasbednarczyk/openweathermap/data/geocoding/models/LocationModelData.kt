package de.niklasbednarczyk.openweathermap.data.geocoding.models

import de.niklasbednarczyk.openweathermap.core.common.data.DataLanguageType
import de.niklasbednarczyk.openweathermap.data.geocoding.local.models.LocalNamesModelLocal
import de.niklasbednarczyk.openweathermap.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.models.LocationModelRemote
import de.niklasbednarczyk.openweathermap.data.geocoding.values.CoordinateValue

data class LocationModelData(
    val localizedName: String?,
    val country: String?,
    val state: String?,
    val latitude: CoordinateValue,
    val longitude: CoordinateValue
) {

    companion object {

        internal fun remoteToData(
            remoteList: List<LocationModelRemote>,
            dataLanguage: DataLanguageType
        ): List<LocationModelData> {
            return remoteList.map { remote ->
                val localNames = remote.localNames
                val localName = when (dataLanguage) {
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

                val localizedName = localName ?: localNames?.en ?: remote.name

                LocationModelData(
                    localizedName = localizedName,
                    country = remote.country,
                    state = remote.state,
                    latitude = CoordinateValue(remote.lat),
                    longitude = CoordinateValue(remote.lon),
                )
            }
        }

        internal fun remoteToLocal(
            remote: LocationModelRemote?,
            latitude: Double,
            longitude: Double
        ): LocationModelLocal? {
            if (remote == null) return null

            val remoteLocalNames = remote.localNames
            val localLocalNames = if (remoteLocalNames != null) {
                LocalNamesModelLocal(
                    af = remoteLocalNames.af,
                    sq = remoteLocalNames.sq,
                    ar = remoteLocalNames.ar,
                    az = remoteLocalNames.az,
                    bg = remoteLocalNames.bg,
                    ca = remoteLocalNames.ca,
                    cs = remoteLocalNames.cs,
                    da = remoteLocalNames.da,
                    de = remoteLocalNames.de,
                    el = remoteLocalNames.el,
                    en = remoteLocalNames.en,
                    eu = remoteLocalNames.eu,
                    fa = remoteLocalNames.fa,
                    fi = remoteLocalNames.fi,
                    fr = remoteLocalNames.fr,
                    gl = remoteLocalNames.gl,
                    he = remoteLocalNames.he,
                    hi = remoteLocalNames.hi,
                    hr = remoteLocalNames.hr,
                    hu = remoteLocalNames.hu,
                    id = remoteLocalNames.id,
                    it = remoteLocalNames.it,
                    ja = remoteLocalNames.ja,
                    ko = remoteLocalNames.ko,
                    lv = remoteLocalNames.lv,
                    lt = remoteLocalNames.lt,
                    mk = remoteLocalNames.mk,
                    no = remoteLocalNames.no,
                    nl = remoteLocalNames.nl,
                    pl = remoteLocalNames.pl,
                    pt = remoteLocalNames.pt,
                    ro = remoteLocalNames.ro,
                    ru = remoteLocalNames.ru,
                    sv = remoteLocalNames.sv,
                    sk = remoteLocalNames.sk,
                    sl = remoteLocalNames.sl,
                    es = remoteLocalNames.es,
                    sr = remoteLocalNames.sr,
                    th = remoteLocalNames.th,
                    tr = remoteLocalNames.tr,
                    uk = remoteLocalNames.uk,
                    vi = remoteLocalNames.vi,
                    zh = remoteLocalNames.zh,
                    zu = remoteLocalNames.zu
                )
            } else {
                null
            }

            return LocationModelLocal(
                name = remote.name,
                localNames = localLocalNames,
                country = remote.country,
                state = remote.state,
                latitude = latitude,
                longitude = longitude,
            )
        }

        internal fun localToData(
            local: LocationModelLocal,
            dataLanguage: DataLanguageType
        ): LocationModelData {
            val localNames = local.localNames
            val localName = when (dataLanguage) {
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

            val localizedName = localName ?: localNames?.en ?: local.name

            return LocationModelData(
                localizedName = localizedName,
                country = local.country,
                state = local.state,
                latitude = CoordinateValue(local.latitude),
                longitude = CoordinateValue(local.longitude)
            )
        }

    }

}