package de.niklasbednarczyk.nbweather.data.settings.serializers

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontAxes
import de.niklasbednarczyk.nbweather.core.data.disk.constants.ConstantsCoreDisk
import de.niklasbednarczyk.nbweather.data.settings.proto.font.SettingsFontProto
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SettingsFontSerializer @Inject constructor() : Serializer<SettingsFontProto> {

    companion object {

        internal fun createDefaultValue(): SettingsFontProto = SettingsFontProto.newBuilder()
            .setSlant(NBFontAxes.Slant.defaultValue)
            .setWidth(NBFontAxes.Width.defaultValue)
            .setWeight(NBFontAxes.Weight.defaultValue)
            .setGrade(NBFontAxes.Grade.defaultValue)
            .setCounterWidth(NBFontAxes.CounterWidth.defaultValue)
            .setThinStroke(NBFontAxes.ThinStroke.defaultValue)
            .setAscenderHeight(NBFontAxes.AscenderHeight.defaultValue)
            .setDescenderDepth(NBFontAxes.DescenderDepth.defaultValue)
            .setFigureHeight(NBFontAxes.FigureHeight.defaultValue)
            .setLowercaseHeight(NBFontAxes.LowercaseHeight.defaultValue)
            .setUppercaseHeight(NBFontAxes.UppercaseHeight.defaultValue)
            .build()

    }

    override val defaultValue: SettingsFontProto = createDefaultValue()

    override suspend fun readFrom(input: InputStream): SettingsFontProto {
        try {
            return SettingsFontProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException(ConstantsCoreDisk.Serializer.ERROR_MESSAGE, exception)
        }
    }

    override suspend fun writeTo(t: SettingsFontProto, output: OutputStream) = t.writeTo(output)


}