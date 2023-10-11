package de.niklasbednarczyk.nbweather.data.settings.serializers

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import de.niklasbednarczyk.nbweather.core.data.disk.constants.ConstantsCoreDisk
import de.niklasbednarczyk.nbweather.data.settings.proto.order.SettingsOrderProto
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SettingsOrderSerializer @Inject constructor() : Serializer<SettingsOrderProto> {

    internal companion object {

        fun createDefaultValue(): SettingsOrderProto = SettingsOrderProto.newBuilder()
            .setCurrentWeatherOrder(4)
            .setDailyOrder(3)
            .setHourlyOrder(2)
            .setPrecipitationOrder(1)
            .setSunAndMoonOrder(5)
            .build()

    }

    override val defaultValue: SettingsOrderProto = createDefaultValue()

    override suspend fun readFrom(input: InputStream): SettingsOrderProto {
        try {
            return SettingsOrderProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException(ConstantsCoreDisk.Serializer.ERROR_MESSAGE, exception)
        }
    }

    override suspend fun writeTo(t: SettingsOrderProto, output: OutputStream) = t.writeTo(output)


}