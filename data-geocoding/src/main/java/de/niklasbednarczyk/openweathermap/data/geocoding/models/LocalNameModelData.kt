package de.niklasbednarczyk.openweathermap.data.geocoding.models

import de.niklasbednarczyk.openweathermap.core.common.display.OwmDataLanguageType
import de.niklasbednarczyk.openweathermap.data.geocoding.local.models.LocalNamesModelLocal
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.models.LocalNamesModelRemote

@JvmInline
internal value class LocalNameModelData(val value: String?) {

    companion object {

        internal fun remoteToData(
            remote: LocalNamesModelRemote?,
            dataLanguage: OwmDataLanguageType
        ): LocalNameModelData {
            val localName = when (dataLanguage) {
                OwmDataLanguageType.AFRIKAANS -> remote?.af
                OwmDataLanguageType.ALBANIAN -> remote?.sq
                OwmDataLanguageType.ARABIC -> remote?.ar
                OwmDataLanguageType.AZERBAIJANI -> remote?.az
                OwmDataLanguageType.BULGARIAN -> remote?.bg
                OwmDataLanguageType.CATALAN -> remote?.ca
                OwmDataLanguageType.CZECH -> remote?.cs
                OwmDataLanguageType.DANISH -> remote?.da
                OwmDataLanguageType.GERMAN -> remote?.de
                OwmDataLanguageType.GREEK -> remote?.el
                OwmDataLanguageType.ENGLISH -> remote?.en
                OwmDataLanguageType.BASQUE -> remote?.eu
                OwmDataLanguageType.PERSIAN_FARSI -> remote?.fa
                OwmDataLanguageType.FINNISH -> remote?.fi
                OwmDataLanguageType.FRENCH -> remote?.fr
                OwmDataLanguageType.GALICIAN -> remote?.gl
                OwmDataLanguageType.HEBREW -> remote?.he
                OwmDataLanguageType.HINDI -> remote?.hi
                OwmDataLanguageType.CROATIAN -> remote?.hr
                OwmDataLanguageType.HUNGARIAN -> remote?.hu
                OwmDataLanguageType.INDONESIAN -> remote?.id
                OwmDataLanguageType.ITALIAN -> remote?.it
                OwmDataLanguageType.JAPANESE -> remote?.ja
                OwmDataLanguageType.KOREAN -> remote?.ko
                OwmDataLanguageType.LATVIAN -> remote?.lv
                OwmDataLanguageType.LITHUANIAN -> remote?.lt
                OwmDataLanguageType.MACEDONIAN -> remote?.mk
                OwmDataLanguageType.NORWEGIAN -> remote?.no
                OwmDataLanguageType.DUTCH -> remote?.nl
                OwmDataLanguageType.POLISH -> remote?.pl
                OwmDataLanguageType.PORTUGUESE, OwmDataLanguageType.PORTUGUES_BRASIL -> remote?.pt
                OwmDataLanguageType.ROMANIAN -> remote?.ro
                OwmDataLanguageType.RUSSIAN -> remote?.ru
                OwmDataLanguageType.SWEDISH -> remote?.sv
                OwmDataLanguageType.SLOVAK -> remote?.sk
                OwmDataLanguageType.SLOVENIAN -> remote?.sl
                OwmDataLanguageType.SPANISH -> remote?.es
                OwmDataLanguageType.SERBIAN -> remote?.sr
                OwmDataLanguageType.THAI -> remote?.th
                OwmDataLanguageType.TURKISH -> remote?.tr
                OwmDataLanguageType.UKRAINIAN -> remote?.uk
                OwmDataLanguageType.VIETNAMESE -> remote?.vi
                OwmDataLanguageType.CHINESE_SIMPLIFIED, OwmDataLanguageType.CHINESE_TRADITIONAL -> remote?.zh
                OwmDataLanguageType.ZULU -> remote?.zu
            }
            return LocalNameModelData(localName)
        }

        internal fun remoteToLocal(remote: LocalNamesModelRemote?): LocalNamesModelLocal {
            return LocalNamesModelLocal(
                af = remote?.af,
                sq = remote?.sq,
                ar = remote?.ar,
                az = remote?.az,
                bg = remote?.bg,
                ca = remote?.ca,
                cs = remote?.cs,
                da = remote?.da,
                de = remote?.de,
                el = remote?.el,
                en = remote?.en,
                eu = remote?.eu,
                fa = remote?.fa,
                fi = remote?.fi,
                fr = remote?.fr,
                gl = remote?.gl,
                he = remote?.he,
                hi = remote?.hi,
                hr = remote?.hr,
                hu = remote?.hu,
                id = remote?.id,
                it = remote?.it,
                ja = remote?.ja,
                ko = remote?.ko,
                lv = remote?.lv,
                lt = remote?.lt,
                mk = remote?.mk,
                no = remote?.no,
                nl = remote?.nl,
                pl = remote?.pl,
                pt = remote?.pt,
                ro = remote?.ro,
                ru = remote?.ru,
                sv = remote?.sv,
                sk = remote?.sk,
                sl = remote?.sl,
                es = remote?.es,
                sr = remote?.sr,
                th = remote?.th,
                tr = remote?.tr,
                uk = remote?.uk,
                vi = remote?.vi,
                zh = remote?.zh,
                zu = remote?.zu
            )
        }

        internal fun localToData(
            local: LocalNamesModelLocal?,
            dataLanguage: OwmDataLanguageType
        ): LocalNameModelData {
            val localName = when (dataLanguage) {
                OwmDataLanguageType.AFRIKAANS -> local?.af
                OwmDataLanguageType.ALBANIAN -> local?.sq
                OwmDataLanguageType.ARABIC -> local?.ar
                OwmDataLanguageType.AZERBAIJANI -> local?.az
                OwmDataLanguageType.BULGARIAN -> local?.bg
                OwmDataLanguageType.CATALAN -> local?.ca
                OwmDataLanguageType.CZECH -> local?.cs
                OwmDataLanguageType.DANISH -> local?.da
                OwmDataLanguageType.GERMAN -> local?.de
                OwmDataLanguageType.GREEK -> local?.el
                OwmDataLanguageType.ENGLISH -> local?.en
                OwmDataLanguageType.BASQUE -> local?.eu
                OwmDataLanguageType.PERSIAN_FARSI -> local?.fa
                OwmDataLanguageType.FINNISH -> local?.fi
                OwmDataLanguageType.FRENCH -> local?.fr
                OwmDataLanguageType.GALICIAN -> local?.gl
                OwmDataLanguageType.HEBREW -> local?.he
                OwmDataLanguageType.HINDI -> local?.hi
                OwmDataLanguageType.CROATIAN -> local?.hr
                OwmDataLanguageType.HUNGARIAN -> local?.hu
                OwmDataLanguageType.INDONESIAN -> local?.id
                OwmDataLanguageType.ITALIAN -> local?.it
                OwmDataLanguageType.JAPANESE -> local?.ja
                OwmDataLanguageType.KOREAN -> local?.ko
                OwmDataLanguageType.LATVIAN -> local?.lv
                OwmDataLanguageType.LITHUANIAN -> local?.lt
                OwmDataLanguageType.MACEDONIAN -> local?.mk
                OwmDataLanguageType.NORWEGIAN -> local?.no
                OwmDataLanguageType.DUTCH -> local?.nl
                OwmDataLanguageType.POLISH -> local?.pl
                OwmDataLanguageType.PORTUGUESE, OwmDataLanguageType.PORTUGUES_BRASIL -> local?.pt
                OwmDataLanguageType.ROMANIAN -> local?.ro
                OwmDataLanguageType.RUSSIAN -> local?.ru
                OwmDataLanguageType.SWEDISH -> local?.sv
                OwmDataLanguageType.SLOVAK -> local?.sk
                OwmDataLanguageType.SLOVENIAN -> local?.sl
                OwmDataLanguageType.SPANISH -> local?.es
                OwmDataLanguageType.SERBIAN -> local?.sr
                OwmDataLanguageType.THAI -> local?.th
                OwmDataLanguageType.TURKISH -> local?.tr
                OwmDataLanguageType.UKRAINIAN -> local?.uk
                OwmDataLanguageType.VIETNAMESE -> local?.vi
                OwmDataLanguageType.CHINESE_SIMPLIFIED, OwmDataLanguageType.CHINESE_TRADITIONAL -> local?.zh
                OwmDataLanguageType.ZULU -> local?.zu
            }
            return LocalNameModelData(localName)
        }

    }


}