package de.niklasbednarczyk.nbweather.core.data.disk.mappers

interface TwoWayMapperDisk<Proto, Data> : OneWayMapperDisk<Proto, Data> {

    fun dataToProto(data: Data): Proto

}