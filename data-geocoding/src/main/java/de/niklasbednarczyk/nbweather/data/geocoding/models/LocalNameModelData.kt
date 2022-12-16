package de.niklasbednarczyk.nbweather.data.geocoding.models

import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocalNamesModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocalNamesModelRemote

@JvmInline
internal value class LocalNameModelData(val value: String?) {

    companion object {

        internal fun remoteToData(
            remote: LocalNamesModelRemote?,
            language: NBLanguageType
        ): LocalNameModelData {
            val localName = when (language) {
                NBLanguageType.AFRIKAANS -> remote?.af
                NBLanguageType.ALBANIAN -> remote?.sq
                NBLanguageType.ARABIC -> remote?.ar
                NBLanguageType.AZERBAIJANI -> remote?.az
                NBLanguageType.BULGARIAN -> remote?.bg
                NBLanguageType.CATALAN -> remote?.ca
                NBLanguageType.CZECH -> remote?.cs
                NBLanguageType.DANISH -> remote?.da
                NBLanguageType.GERMAN -> remote?.de
                NBLanguageType.GREEK -> remote?.el
                NBLanguageType.ENGLISH -> remote?.en
                NBLanguageType.BASQUE -> remote?.eu
                NBLanguageType.PERSIAN_FARSI -> remote?.fa
                NBLanguageType.FINNISH -> remote?.fi
                NBLanguageType.FRENCH -> remote?.fr
                NBLanguageType.GALICIAN -> remote?.gl
                NBLanguageType.HEBREW -> remote?.he
                NBLanguageType.HINDI -> remote?.hi
                NBLanguageType.CROATIAN -> remote?.hr
                NBLanguageType.HUNGARIAN -> remote?.hu
                NBLanguageType.INDONESIAN -> remote?.id
                NBLanguageType.ITALIAN -> remote?.it
                NBLanguageType.JAPANESE -> remote?.ja
                NBLanguageType.KOREAN -> remote?.ko
                NBLanguageType.LATVIAN -> remote?.lv
                NBLanguageType.LITHUANIAN -> remote?.lt
                NBLanguageType.MACEDONIAN -> remote?.mk
                NBLanguageType.NORWEGIAN -> remote?.no
                NBLanguageType.DUTCH -> remote?.nl
                NBLanguageType.POLISH -> remote?.pl
                NBLanguageType.PORTUGUESE, NBLanguageType.PORTUGUES_BRASIL -> remote?.pt
                NBLanguageType.ROMANIAN -> remote?.ro
                NBLanguageType.RUSSIAN -> remote?.ru
                NBLanguageType.SWEDISH -> remote?.sv
                NBLanguageType.SLOVAK -> remote?.sk
                NBLanguageType.SLOVENIAN -> remote?.sl
                NBLanguageType.SPANISH -> remote?.es
                NBLanguageType.SERBIAN -> remote?.sr
                NBLanguageType.THAI -> remote?.th
                NBLanguageType.TURKISH -> remote?.tr
                NBLanguageType.UKRAINIAN -> remote?.uk
                NBLanguageType.VIETNAMESE -> remote?.vi
                NBLanguageType.CHINESE_SIMPLIFIED, NBLanguageType.CHINESE_TRADITIONAL -> remote?.zh
                NBLanguageType.ZULU -> remote?.zu
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
            language: NBLanguageType
        ): LocalNameModelData {
            val localName = when (language) {
                NBLanguageType.AFRIKAANS -> local?.af
                NBLanguageType.ALBANIAN -> local?.sq
                NBLanguageType.ARABIC -> local?.ar
                NBLanguageType.AZERBAIJANI -> local?.az
                NBLanguageType.BULGARIAN -> local?.bg
                NBLanguageType.CATALAN -> local?.ca
                NBLanguageType.CZECH -> local?.cs
                NBLanguageType.DANISH -> local?.da
                NBLanguageType.GERMAN -> local?.de
                NBLanguageType.GREEK -> local?.el
                NBLanguageType.ENGLISH -> local?.en
                NBLanguageType.BASQUE -> local?.eu
                NBLanguageType.PERSIAN_FARSI -> local?.fa
                NBLanguageType.FINNISH -> local?.fi
                NBLanguageType.FRENCH -> local?.fr
                NBLanguageType.GALICIAN -> local?.gl
                NBLanguageType.HEBREW -> local?.he
                NBLanguageType.HINDI -> local?.hi
                NBLanguageType.CROATIAN -> local?.hr
                NBLanguageType.HUNGARIAN -> local?.hu
                NBLanguageType.INDONESIAN -> local?.id
                NBLanguageType.ITALIAN -> local?.it
                NBLanguageType.JAPANESE -> local?.ja
                NBLanguageType.KOREAN -> local?.ko
                NBLanguageType.LATVIAN -> local?.lv
                NBLanguageType.LITHUANIAN -> local?.lt
                NBLanguageType.MACEDONIAN -> local?.mk
                NBLanguageType.NORWEGIAN -> local?.no
                NBLanguageType.DUTCH -> local?.nl
                NBLanguageType.POLISH -> local?.pl
                NBLanguageType.PORTUGUESE, NBLanguageType.PORTUGUES_BRASIL -> local?.pt
                NBLanguageType.ROMANIAN -> local?.ro
                NBLanguageType.RUSSIAN -> local?.ru
                NBLanguageType.SWEDISH -> local?.sv
                NBLanguageType.SLOVAK -> local?.sk
                NBLanguageType.SLOVENIAN -> local?.sl
                NBLanguageType.SPANISH -> local?.es
                NBLanguageType.SERBIAN -> local?.sr
                NBLanguageType.THAI -> local?.th
                NBLanguageType.TURKISH -> local?.tr
                NBLanguageType.UKRAINIAN -> local?.uk
                NBLanguageType.VIETNAMESE -> local?.vi
                NBLanguageType.CHINESE_SIMPLIFIED, NBLanguageType.CHINESE_TRADITIONAL -> local?.zh
                NBLanguageType.ZULU -> local?.zu
            }
            return LocalNameModelData(localName)
        }

    }


}