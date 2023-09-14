package de.niklasbednarczyk.nbweather.data.geocoding.models

import de.niklasbednarczyk.nbweather.core.common.language.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.language.NBLanguageType.Companion.from
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocalNamesModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocalNamesModelRemote

data class LocalNamesModelData(
    val de: String?,
    val en: String?
) {

    val localizedName: String?
        get() = when (from()) {
            NBLanguageType.ENGLISH -> en
            NBLanguageType.GERMAN -> de
        }


    internal companion object {

        fun remoteToData(
            remote: LocalNamesModelRemote?
        ): LocalNamesModelData {
            return LocalNamesModelData(
                de = remote?.de,
                en = remote?.en
            )
        }

        fun remoteToLocal(remote: LocalNamesModelRemote?): LocalNamesModelLocal {
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

        fun localToData(
            local: LocalNamesModelLocal?
        ): LocalNamesModelData {
            return LocalNamesModelData(
                de = local?.de,
                en = local?.en
            )
        }

    }


}