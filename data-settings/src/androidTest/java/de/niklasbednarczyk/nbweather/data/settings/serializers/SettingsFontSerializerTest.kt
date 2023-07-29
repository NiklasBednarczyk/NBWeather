package de.niklasbednarczyk.nbweather.data.settings.serializers

import androidx.datastore.core.Serializer
import de.niklasbednarczyk.nbweather.data.settings.proto.font.SettingsFontProto
import de.niklasbednarczyk.nbweather.test.data.disk.serializers.NBSerializerTest

class SettingsFontSerializerTest : NBSerializerTest<SettingsFontProto>() {

    override fun createSerializer(): Serializer<SettingsFontProto> {
        return SettingsFontSerializer()
    }

    override val expectedDefaultValue: SettingsFontProto
        get() {
            return SettingsFontSerializer.createDefaultValue()
        }

    override val expectedNewValue: SettingsFontProto
        get() {
            return SettingsFontProto.getDefaultInstance()
        }

}