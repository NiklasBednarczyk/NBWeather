package de.niklasbednarczyk.nbweather.core.data.disk.mappers

interface OneWayMapperDisk<Proto, Data> {

    fun protoToData(proto: Proto): Data

}