package de.niklasbednarczyk.nbweather.data.settings.serializers

import androidx.datastore.core.Serializer
import de.niklasbednarczyk.nbweather.data.settings.proto.units.SettingsUnitsProto
import de.niklasbednarczyk.nbweather.test.data.disk.serializers.NBSerializerTest

class SettingsUnitsSerializerTest : NBSerializerTest<SettingsUnitsProto>() {

    override fun createSerializer(): Serializer<SettingsUnitsProto> {
        return SettingsUnitsSerializer()
    }

    override val expectedDefaultValue: SettingsUnitsProto
        get() = SettingsUnitsSerializer.createDefault(SettingsUnitsSerializer.usesImperialSystem())

    override val expectedNewValue: SettingsUnitsProto
        get() = SettingsUnitsSerializer.createDefault(!SettingsUnitsSerializer.usesImperialSystem())

}