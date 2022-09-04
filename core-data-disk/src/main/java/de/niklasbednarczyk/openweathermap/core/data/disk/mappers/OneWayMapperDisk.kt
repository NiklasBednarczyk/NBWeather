package de.niklasbednarczyk.openweathermap.core.data.disk.mappers

interface OneWayMapperDisk<Proto, Disk> {

    fun protoToDisk(proto: Proto): Disk

}