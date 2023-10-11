package de.niklasbednarczyk.nbweather.data.settings.repositories

import androidx.datastore.core.DataStore
import de.niklasbednarczyk.nbweather.core.common.settings.order.NBOrderModel
import de.niklasbednarczyk.nbweather.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.nbweather.core.data.disk.repositories.RepositoryDisk
import de.niklasbednarczyk.nbweather.core.data.disk.utils.createFakeDataStore
import de.niklasbednarczyk.nbweather.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.nbweather.data.settings.mappers.order.NBOrderMapperData
import de.niklasbednarczyk.nbweather.data.settings.proto.order.SettingsOrderProto
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsOrderSerializer
import org.junit.rules.TemporaryFolder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsOrderRepository @Inject constructor(
    override val dataStore: DataStore<SettingsOrderProto>
) : RepositoryDisk<SettingsOrderProto, NBOrderModel>() {

    companion object {

        fun createFake(
            temporaryFolder: TemporaryFolder,
        ): SettingsOrderRepository {
            val dataStore = createFakeDataStore(
                temporaryFolder = temporaryFolder,
                serializer = SettingsOrderSerializer(),
                fileName = ConstantsDataSettings.DataStore.SETTINGS_ORDER_FILE_NAME
            )
            return SettingsOrderRepository(
                dataStore = dataStore
            )
        }

    }

    override val mapper: TwoWayMapperDisk<SettingsOrderProto, NBOrderModel>
        get() = NBOrderMapperData

    suspend fun resetToDefault() {
        dataStore.updateData {
            SettingsOrderSerializer.createDefaultValue()
        }
    }

    suspend fun updateOrder(order: NBOrderModel) {
        dataStore.updateData {
            mapper.dataToProto(order)
        }
    }

}