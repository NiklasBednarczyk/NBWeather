package de.niklasbednarczyk.nbweather.core.data.disk.mappers

interface OneWayMapperDisk<Proto, Disk> {

    fun protoToDisk(proto: Proto): Disk

}