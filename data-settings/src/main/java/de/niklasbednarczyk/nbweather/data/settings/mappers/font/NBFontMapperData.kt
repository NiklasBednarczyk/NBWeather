package de.niklasbednarczyk.nbweather.data.settings.mappers.font

import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontModel
import de.niklasbednarczyk.nbweather.core.data.disk.mappers.OneWayMapperDisk
import de.niklasbednarczyk.nbweather.data.settings.proto.font.SettingsFontProto

internal object NBFontMapperData : OneWayMapperDisk<SettingsFontProto, NBFontModel> {
    override fun protoToData(proto: SettingsFontProto): NBFontModel {
        return NBFontModel(
            slant = proto.slant,
            width = proto.width,
            weight = proto.weight,
            grade = proto.grade,
            counterWidth = proto.counterWidth,
            thinStroke = proto.thinStroke,
            ascenderHeight = proto.ascenderHeight,
            descenderDepth = proto.descenderDepth,
            figureHeight = proto.figureHeight,
            lowercaseHeight = proto.lowercaseHeight,
            uppercaseHeight = proto.uppercaseHeight
        )
    }
}