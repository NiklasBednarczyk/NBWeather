package de.niklasbednarczyk.nbweather.data.geocoding.models

import de.niklasbednarczyk.nbweather.core.common.locale.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocalNamesModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocalNamesModelRemote

data class LocalNamesModelData(
    internal val af: String?,
    internal val sq: String?,
    internal val ar: String?,
    internal val az: String?,
    internal val bg: String?,
    internal val ca: String?,
    internal val cs: String?,
    internal val da: String?,
    internal val de: String?,
    internal val el: String?,
    internal val en: String?,
    internal val eu: String?,
    internal val fa: String?,
    internal val fi: String?,
    internal val fr: String?,
    internal val gl: String?,
    internal val he: String?,
    internal val hi: String?,
    internal val hr: String?,
    internal val hu: String?,
    internal val id: String?,
    internal val it: String?,
    internal val ja: String?,
    internal val ko: String?,
    internal val lv: String?,
    internal val lt: String?,
    internal val mk: String?,
    internal val no: String?,
    internal val nl: String?,
    internal val pl: String?,
    internal val pt: String?,
    internal val ro: String?,
    internal val ru: String?,
    internal val sv: String?,
    internal val sk: String?,
    internal val sl: String?,
    internal val es: String?,
    internal val sr: String?,
    internal val th: String?,
    internal val tr: String?,
    internal val uk: String?,
    internal val vi: String?,
    internal val zh: String?,
    internal val zu: String?
) {

    val localizedName: String?
        get() = when (NBLanguageType.fromLocale()) {
            NBLanguageType.ENGLISH -> en
            NBLanguageType.GERMAN -> de
        }

    internal companion object {

        fun dataToLocal(
            data: LocalNamesModelData?
        ): LocalNamesModelLocal? {
            return nbNullSafe(data) { d ->
                LocalNamesModelLocal(
                    af = d.af,
                    sq = d.sq,
                    ar = d.ar,
                    az = d.az,
                    bg = d.bg,
                    ca = d.ca,
                    cs = d.cs,
                    da = d.da,
                    de = d.de,
                    el = d.el,
                    en = d.en,
                    eu = d.eu,
                    fa = d.fa,
                    fi = d.fi,
                    fr = d.fr,
                    gl = d.gl,
                    he = d.he,
                    hi = d.hi,
                    hr = d.hr,
                    hu = d.hu,
                    id = d.id,
                    it = d.it,
                    ja = d.ja,
                    ko = d.ko,
                    lv = d.lv,
                    lt = d.lt,
                    mk = d.mk,
                    no = d.no,
                    nl = d.nl,
                    pl = d.pl,
                    pt = d.pt,
                    ro = d.ro,
                    ru = d.ru,
                    sv = d.sv,
                    sk = d.sk,
                    sl = d.sl,
                    es = d.es,
                    sr = d.sr,
                    th = d.th,
                    tr = d.tr,
                    uk = d.uk,
                    vi = d.vi,
                    zh = d.zh,
                    zu = d.zu
                )
            }
        }

        fun localToData(
            local: LocalNamesModelLocal?
        ): LocalNamesModelData? {
            return nbNullSafe(local) { l ->
                LocalNamesModelData(
                    af = l.af,
                    sq = l.sq,
                    ar = l.ar,
                    az = l.az,
                    bg = l.bg,
                    ca = l.ca,
                    cs = l.cs,
                    da = l.da,
                    de = l.de,
                    el = l.el,
                    en = l.en,
                    eu = l.eu,
                    fa = l.fa,
                    fi = l.fi,
                    fr = l.fr,
                    gl = l.gl,
                    he = l.he,
                    hi = l.hi,
                    hr = l.hr,
                    hu = l.hu,
                    id = l.id,
                    it = l.it,
                    ja = l.ja,
                    ko = l.ko,
                    lv = l.lv,
                    lt = l.lt,
                    mk = l.mk,
                    no = l.no,
                    nl = l.nl,
                    pl = l.pl,
                    pt = l.pt,
                    ro = l.ro,
                    ru = l.ru,
                    sv = l.sv,
                    sk = l.sk,
                    sl = l.sl,
                    es = l.es,
                    sr = l.sr,
                    th = l.th,
                    tr = l.tr,
                    uk = l.uk,
                    vi = l.vi,
                    zh = l.zh,
                    zu = l.zu
                )
            }
        }

        fun remoteToData(
            remote: LocalNamesModelRemote?
        ): LocalNamesModelData? {
            return nbNullSafe(remote) { r ->
                LocalNamesModelData(
                    af = r.af,
                    sq = r.sq,
                    ar = r.ar,
                    az = r.az,
                    bg = r.bg,
                    ca = r.ca,
                    cs = r.cs,
                    da = r.da,
                    de = r.de,
                    el = r.el,
                    en = r.en,
                    eu = r.eu,
                    fa = r.fa,
                    fi = r.fi,
                    fr = r.fr,
                    gl = r.gl,
                    he = r.he,
                    hi = r.hi,
                    hr = r.hr,
                    hu = r.hu,
                    id = r.id,
                    it = r.it,
                    ja = r.ja,
                    ko = r.ko,
                    lv = r.lv,
                    lt = r.lt,
                    mk = r.mk,
                    no = r.no,
                    nl = r.nl,
                    pl = r.pl,
                    pt = r.pt,
                    ro = r.ro,
                    ru = r.ru,
                    sv = r.sv,
                    sk = r.sk,
                    sl = r.sl,
                    es = r.es,
                    sr = r.sr,
                    th = r.th,
                    tr = r.tr,
                    uk = r.uk,
                    vi = r.vi,
                    zh = r.zh,
                    zu = r.zu
                )
            }
        }

        fun remoteToLocal(
            remote: LocalNamesModelRemote?
        ): LocalNamesModelLocal? {
            return nbNullSafe(remote) { r ->
                LocalNamesModelLocal(
                    af = r.af,
                    sq = r.sq,
                    ar = r.ar,
                    az = r.az,
                    bg = r.bg,
                    ca = r.ca,
                    cs = r.cs,
                    da = r.da,
                    de = r.de,
                    el = r.el,
                    en = r.en,
                    eu = r.eu,
                    fa = r.fa,
                    fi = r.fi,
                    fr = r.fr,
                    gl = r.gl,
                    he = r.he,
                    hi = r.hi,
                    hr = r.hr,
                    hu = r.hu,
                    id = r.id,
                    it = r.it,
                    ja = r.ja,
                    ko = r.ko,
                    lv = r.lv,
                    lt = r.lt,
                    mk = r.mk,
                    no = r.no,
                    nl = r.nl,
                    pl = r.pl,
                    pt = r.pt,
                    ro = r.ro,
                    ru = r.ru,
                    sv = r.sv,
                    sk = r.sk,
                    sl = r.sl,
                    es = r.es,
                    sr = r.sr,
                    th = r.th,
                    tr = r.tr,
                    uk = r.uk,
                    vi = r.vi,
                    zh = r.zh,
                    zu = r.zu
                )
            }
        }

    }

}