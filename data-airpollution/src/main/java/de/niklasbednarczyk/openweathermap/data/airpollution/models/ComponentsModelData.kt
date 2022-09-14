package de.niklasbednarczyk.openweathermap.data.airpollution.models

import de.niklasbednarczyk.openweathermap.data.airpollution.local.models.airpollution.ComponentsModelLocal
import de.niklasbednarczyk.openweathermap.data.airpollution.remote.models.airpollution.ComponentsModelRemote
import de.niklasbednarczyk.openweathermap.data.airpollution.values.ConcentrationValue

data class ComponentsModelData(
    val carbonMonoxideConcentration: ConcentrationValue,
    val nitrogenMonoxideConcentration: ConcentrationValue,
    val nitrogenDioxideConcentration: ConcentrationValue,
    val ozoneConcentration: ConcentrationValue,
    val sulphurDioxideConcentration: ConcentrationValue,
    val fineParticlesMatterConcentration: ConcentrationValue,
    val coarseParticulateMatterConcentration: ConcentrationValue,
    val ammoniaConcentration: ConcentrationValue
) {

    companion object {

        internal fun remoteToLocal(
            remote: ComponentsModelRemote?
        ): ComponentsModelLocal {
            return ComponentsModelLocal(
                co = remote?.co,
                no = remote?.no,
                no2 = remote?.no2,
                o3 = remote?.o3,
                so2 = remote?.so2,
                pm2_5 = remote?.pm2_5,
                pm10 = remote?.pm10,
                nh3 = remote?.nh3
            )
        }

        internal fun localToData(
            local: ComponentsModelLocal?
        ): ComponentsModelData {
            return ComponentsModelData(
                carbonMonoxideConcentration = ConcentrationValue(local?.co),
                nitrogenMonoxideConcentration = ConcentrationValue(local?.no),
                nitrogenDioxideConcentration = ConcentrationValue(local?.no2),
                ozoneConcentration = ConcentrationValue(local?.o3),
                sulphurDioxideConcentration = ConcentrationValue(local?.so2),
                fineParticlesMatterConcentration = ConcentrationValue(local?.pm2_5),
                coarseParticulateMatterConcentration = ConcentrationValue(local?.pm10),
                ammoniaConcentration = ConcentrationValue(local?.nh3)
            )
        }

    }

}
