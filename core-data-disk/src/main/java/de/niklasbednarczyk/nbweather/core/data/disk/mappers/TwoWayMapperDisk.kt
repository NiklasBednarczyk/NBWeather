package de.niklasbednarczyk.nbweather.core.data.disk.mappers

interface TwoWayMapperDisk<Proto, Disk> : OneWayMapperDisk<Proto, Disk> {

    fun diskToProto(disk: Disk): Proto

}