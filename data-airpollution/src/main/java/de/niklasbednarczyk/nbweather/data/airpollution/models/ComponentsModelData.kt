package de.niklasbednarczyk.nbweather.data.airpollution.models

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.airpollution.local.models.ComponentsModelLocal
import de.niklasbednarczyk.nbweather.data.airpollution.remote.models.ComponentsModelRemote
import de.niklasbednarczyk.nbweather.data.airpollution.values.components.AmmoniaValue
import de.niklasbednarczyk.nbweather.data.airpollution.values.components.CarbonMonoxideValue
import de.niklasbednarczyk.nbweather.data.airpollution.values.components.CoarseParticulateMatterValue
import de.niklasbednarczyk.nbweather.data.airpollution.values.components.FineParticlesMatterValue
import de.niklasbednarczyk.nbweather.data.airpollution.values.components.NitrogenDioxideValue
import de.niklasbednarczyk.nbweather.data.airpollution.values.components.NitrogenMonoxideValue
import de.niklasbednarczyk.nbweather.data.airpollution.values.components.OzoneValue
import de.niklasbednarczyk.nbweather.data.airpollution.values.components.SulphurDioxideValue

data class ComponentsModelData(
    val carbonMonoxide: CarbonMonoxideValue?,
    val nitrogenMonoxide: NitrogenMonoxideValue?,
    val nitrogenDioxide: NitrogenDioxideValue?,
    val ozone: OzoneValue?,
    val sulphurDioxide: SulphurDioxideValue?,
    val fineParticlesMatter: FineParticlesMatterValue?,
    val coarseParticulateMatter: CoarseParticulateMatterValue?,
    val ammonia: AmmoniaValue?
) {

    internal companion object {

        fun remoteToLocal(
            remote: ComponentsModelRemote?
        ): ComponentsModelLocal? {
            return nbNullSafe(remote) { r ->
                ComponentsModelLocal(
                    co = r.co,
                    no = r.no,
                    no2 = r.no2,
                    o3 = r.o3,
                    so2 = r.so2,
                    pm25 = r.pm25,
                    pm10 = r.pm10,
                    nh3 = r.nh3
                )
            }
        }

        fun localToData(
            local: ComponentsModelLocal?
        ): ComponentsModelData? {
            return nbNullSafe(local) { l ->
                ComponentsModelData(
                    carbonMonoxide = CarbonMonoxideValue.from(l.co),
                    nitrogenMonoxide = NitrogenMonoxideValue.from(l.no),
                    nitrogenDioxide = NitrogenDioxideValue.from(l.no2),
                    ozone = OzoneValue.from(l.o3),
                    sulphurDioxide = SulphurDioxideValue.from(l.so2),
                    fineParticlesMatter = FineParticlesMatterValue.from(l.pm25),
                    coarseParticulateMatter = CoarseParticulateMatterValue.from(l.pm10),
                    ammonia = AmmoniaValue.from(l.nh3)
                )
            }
        }

    }


}