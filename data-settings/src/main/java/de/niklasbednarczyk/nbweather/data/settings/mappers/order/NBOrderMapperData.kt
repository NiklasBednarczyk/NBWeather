package de.niklasbednarczyk.nbweather.data.settings.mappers.order

import de.niklasbednarczyk.nbweather.core.common.settings.order.NBOrderModel
import de.niklasbednarczyk.nbweather.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.nbweather.data.settings.proto.order.SettingsOrderProto

internal object NBOrderMapperData : TwoWayMapperDisk<SettingsOrderProto, NBOrderModel> {
    override fun protoToData(proto: SettingsOrderProto): NBOrderModel {
        return NBOrderModel(
            currentWeatherOrder = proto.currentWeatherOrder,
            dailyOrder = proto.dailyOrder,
            hourlyOrder = proto.hourlyOrder,
            precipitationOrder = proto.precipitationOrder,
            sunAndMoonOrder = proto.sunAndMoonOrder
        )
    }

    override fun dataToProto(data: NBOrderModel): SettingsOrderProto {
        return SettingsOrderProto.newBuilder()
            .setCurrentWeatherOrder(data.currentWeatherOrder)
            .setDailyOrder(data.dailyOrder)
            .setHourlyOrder(data.hourlyOrder)
            .setPrecipitationOrder(data.precipitationOrder)
            .setSunAndMoonOrder(data.sunAndMoonOrder)
            .build()
    }
}