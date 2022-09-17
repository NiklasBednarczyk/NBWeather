package de.niklasbednarczyk.openweathermap.data.geocoding.models

import de.niklasbednarczyk.openweathermap.data.geocoding.remote.models.LocalNamesRemote

data class LocalNamesData(
    val af: String?,
    val sq: String?,
    val ar: String?,
    val az: String?,
    val bg: String?,
    val ca: String?,
    val cs: String?,
    val da: String?,
    val de: String?,
    val el: String?,
    val en: String?,
    val eu: String?,
    val fa: String?,
    val fi: String?,
    val fr: String?,
    val gl: String?,
    val he: String?,
    val hi: String?,
    val hr: String?,
    val hu: String?,
    val id: String?,
    val it: String?,
    val ja: String?,
    val ko: String?,
    val lv: String?,
    val lt: String?,
    val mk: String?,
    val no: String?,
    val nl: String?,
    val pl: String?,
    val pt: String?,
    val ro: String?,
    val ru: String?,
    val sv: String?,
    val sk: String?,
    val sl: String?,
    val es: String?,
    val sr: String?,
    val th: String?,
    val tr: String?,
    val uk: String?,
    val vi: String?,
    val zh: String?,
    val zu: String?
) {

    companion object {

        internal fun remoteToData(
            remote: LocalNamesRemote?
        ): LocalNamesData? {
            if (remote == null) return null
            return LocalNamesData(
                af = remote.af,
                sq = remote.sq,
                ar = remote.ar,
                az = remote.az,
                bg = remote.bg,
                ca = remote.ca,
                cs = remote.cs,
                da = remote.da,
                de = remote.de,
                el = remote.el,
                en = remote.en,
                eu = remote.eu,
                fa = remote.fa,
                fi = remote.fi,
                fr = remote.fr,
                gl = remote.gl,
                he = remote.he,
                hi = remote.hi,
                hr = remote.hr,
                hu = remote.hu,
                id = remote.id,
                it = remote.it,
                ja = remote.ja,
                ko = remote.ko,
                lv = remote.lv,
                lt = remote.lt,
                mk = remote.mk,
                no = remote.no,
                nl = remote.nl,
                pl = remote.pl,
                pt = remote.pt,
                ro = remote.ro,
                ru = remote.ru,
                sv = remote.sv,
                sk = remote.sk,
                sl = remote.sl,
                es = remote.es,
                sr = remote.sr,
                th = remote.th,
                tr = remote.tr,
                uk = remote.uk,
                vi = remote.vi,
                zh = remote.zh,
                zu = remote.zu
            )
        }

    }

}