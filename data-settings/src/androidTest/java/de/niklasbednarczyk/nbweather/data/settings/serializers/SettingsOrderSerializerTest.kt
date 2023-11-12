package de.niklasbednarczyk.nbweather.data.settings.serializers

import androidx.datastore.core.Serializer
import de.niklasbednarczyk.nbweather.data.settings.proto.order.SettingsOrderProto
import de.niklasbednarczyk.nbweather.test.data.disk.serializers.NBSerializerTest

class SettingsOrderSerializerTest : NBSerializerTest<SettingsOrderProto>() {

    override fun createSerializer(): Serializer<SettingsOrderProto> {
        return SettingsOrderSerializer()
    }

    override val expectedDefaultValue: SettingsOrderProto
        get() {
            return SettingsOrderSerializer.createDefaultValue()
        }

    override val expectedNewValue: SettingsOrderProto
        get() {
            return SettingsOrderProto.getDefaultInstance()
        }

}