package de.niklasbednarczyk.openweathermap.data.geocoding.models

import de.niklasbednarczyk.openweathermap.core.common.data.OwmLanguageType
import de.niklasbednarczyk.openweathermap.data.geocoding.local.models.LocalNamesModelLocal
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.models.LocalNamesModelRemote

@JvmInline
internal value class LocalNameModelData(val value: String?) {

    companion object {

        internal fun remoteToData(
            remote: LocalNamesModelRemote?,
            language: OwmLanguageType
        ): LocalNameModelData {
            val localName = when (language) {
                OwmLanguageType.AFRIKAANS -> remote?.af
                OwmLanguageType.ALBANIAN -> remote?.sq
                OwmLanguageType.ARABIC -> remote?.ar
                OwmLanguageType.AZERBAIJANI -> remote?.az
                OwmLanguageType.BULGARIAN -> remote?.bg
                OwmLanguageType.CATALAN -> remote?.ca
                OwmLanguageType.CZECH -> remote?.cs
                OwmLanguageType.DANISH -> remote?.da
                OwmLanguageType.GERMAN -> remote?.de
                OwmLanguageType.GREEK -> remote?.el
                OwmLanguageType.ENGLISH -> remote?.en
                OwmLanguageType.BASQUE -> remote?.eu
                OwmLanguageType.PERSIAN_FARSI -> remote?.fa
                OwmLanguageType.FINNISH -> remote?.fi
                OwmLanguageType.FRENCH -> remote?.fr
                OwmLanguageType.GALICIAN -> remote?.gl
                OwmLanguageType.HEBREW -> remote?.he
                OwmLanguageType.HINDI -> remote?.hi
                OwmLanguageType.CROATIAN -> remote?.hr
                OwmLanguageType.HUNGARIAN -> remote?.hu
                OwmLanguageType.INDONESIAN -> remote?.id
                OwmLanguageType.ITALIAN -> remote?.it
                OwmLanguageType.JAPANESE -> remote?.ja
                OwmLanguageType.KOREAN -> remote?.ko
                OwmLanguageType.LATVIAN -> remote?.lv
                OwmLanguageType.LITHUANIAN -> remote?.lt
                OwmLanguageType.MACEDONIAN -> remote?.mk
                OwmLanguageType.NORWEGIAN -> remote?.no
                OwmLanguageType.DUTCH -> remote?.nl
                OwmLanguageType.POLISH -> remote?.pl
                OwmLanguageType.PORTUGUESE, OwmLanguageType.PORTUGUES_BRASIL -> remote?.pt
                OwmLanguageType.ROMANIAN -> remote?.ro
                OwmLanguageType.RUSSIAN -> remote?.ru
                OwmLanguageType.SWEDISH -> remote?.sv
                OwmLanguageType.SLOVAK -> remote?.sk
                OwmLanguageType.SLOVENIAN -> remote?.sl
                OwmLanguageType.SPANISH -> remote?.es
                OwmLanguageType.SERBIAN -> remote?.sr
                OwmLanguageType.THAI -> remote?.th
                OwmLanguageType.TURKISH -> remote?.tr
                OwmLanguageType.UKRAINIAN -> remote?.uk
                OwmLanguageType.VIETNAMESE -> remote?.vi
                OwmLanguageType.CHINESE_SIMPLIFIED, OwmLanguageType.CHINESE_TRADITIONAL -> remote?.zh
                OwmLanguageType.ZULU -> remote?.zu
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
            language: OwmLanguageType
        ): LocalNameModelData {
            val localName = when (language) {
                OwmLanguageType.AFRIKAANS -> local?.af
                OwmLanguageType.ALBANIAN -> local?.sq
                OwmLanguageType.ARABIC -> local?.ar
                OwmLanguageType.AZERBAIJANI -> local?.az
                OwmLanguageType.BULGARIAN -> local?.bg
                OwmLanguageType.CATALAN -> local?.ca
                OwmLanguageType.CZECH -> local?.cs
                OwmLanguageType.DANISH -> local?.da
                OwmLanguageType.GERMAN -> local?.de
                OwmLanguageType.GREEK -> local?.el
                OwmLanguageType.ENGLISH -> local?.en
                OwmLanguageType.BASQUE -> local?.eu
                OwmLanguageType.PERSIAN_FARSI -> local?.fa
                OwmLanguageType.FINNISH -> local?.fi
                OwmLanguageType.FRENCH -> local?.fr
                OwmLanguageType.GALICIAN -> local?.gl
                OwmLanguageType.HEBREW -> local?.he
                OwmLanguageType.HINDI -> local?.hi
                OwmLanguageType.CROATIAN -> local?.hr
                OwmLanguageType.HUNGARIAN -> local?.hu
                OwmLanguageType.INDONESIAN -> local?.id
                OwmLanguageType.ITALIAN -> local?.it
                OwmLanguageType.JAPANESE -> local?.ja
                OwmLanguageType.KOREAN -> local?.ko
                OwmLanguageType.LATVIAN -> local?.lv
                OwmLanguageType.LITHUANIAN -> local?.lt
                OwmLanguageType.MACEDONIAN -> local?.mk
                OwmLanguageType.NORWEGIAN -> local?.no
                OwmLanguageType.DUTCH -> local?.nl
                OwmLanguageType.POLISH -> local?.pl
                OwmLanguageType.PORTUGUESE, OwmLanguageType.PORTUGUES_BRASIL -> local?.pt
                OwmLanguageType.ROMANIAN -> local?.ro
                OwmLanguageType.RUSSIAN -> local?.ru
                OwmLanguageType.SWEDISH -> local?.sv
                OwmLanguageType.SLOVAK -> local?.sk
                OwmLanguageType.SLOVENIAN -> local?.sl
                OwmLanguageType.SPANISH -> local?.es
                OwmLanguageType.SERBIAN -> local?.sr
                OwmLanguageType.THAI -> local?.th
                OwmLanguageType.TURKISH -> local?.tr
                OwmLanguageType.UKRAINIAN -> local?.uk
                OwmLanguageType.VIETNAMESE -> local?.vi
                OwmLanguageType.CHINESE_SIMPLIFIED, OwmLanguageType.CHINESE_TRADITIONAL -> local?.zh
                OwmLanguageType.ZULU -> local?.zu
            }
            return LocalNameModelData(localName)
        }

    }


}