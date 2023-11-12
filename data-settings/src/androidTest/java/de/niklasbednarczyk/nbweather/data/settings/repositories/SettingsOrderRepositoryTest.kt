package de.niklasbednarczyk.nbweather.data.settings.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import de.niklasbednarczyk.nbweather.core.common.settings.order.NBOrderModel
import de.niklasbednarczyk.nbweather.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.nbweather.data.settings.mappers.order.NBOrderMapperData
import de.niklasbednarczyk.nbweather.data.settings.proto.order.SettingsOrderProto
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsOrderSerializer
import de.niklasbednarczyk.nbweather.test.data.disk.repositories.NBDiskRepositoryTest
import org.junit.Test

class SettingsOrderRepositoryTest :
    NBDiskRepositoryTest<SettingsOrderProto, NBOrderModel, SettingsOrderRepository>() {

    companion object {
        private const val OLD_STARTING_ORDER_VALUE = 1
        private const val NEW_STARTING_ORDER_VALUE = -1
    }

    private fun getTestSettingsOrderProto(startingOrder: Int) = SettingsOrderProto.newBuilder()
        .setCurrentWeatherOrder(startingOrder + 1)
        .setDailyOrder(startingOrder + 2)
        .setHourlyOrder(startingOrder + 3)
        .setPrecipitationOrder(startingOrder + 4)
        .setSunAndMoonOrder(startingOrder + 5)
        .build()

    override val fileName: String
        get() = ConstantsDataSettings.DataStore.SETTINGS_ORDER_FILE_NAME

    override val updateStartingProto: SettingsOrderProto
        get() = getTestSettingsOrderProto(OLD_STARTING_ORDER_VALUE)

    override fun createSerializer(): Serializer<SettingsOrderProto> {
        return SettingsOrderSerializer()
    }

    override fun createRepository(dataStore: DataStore<SettingsOrderProto>): SettingsOrderRepository {
        return SettingsOrderRepository(dataStore)
    }

    @Test
    fun getData_shouldBeMappedCorrectly() {
        testGetData(
            proto = getTestSettingsOrderProto(NEW_STARTING_ORDER_VALUE),
            assertValue = { actual ->
                assertValue(NEW_STARTING_ORDER_VALUE + 1, actual.currentWeatherOrder)
                assertValue(NEW_STARTING_ORDER_VALUE + 2, actual.dailyOrder)
                assertValue(NEW_STARTING_ORDER_VALUE + 3, actual.hourlyOrder)
                assertValue(NEW_STARTING_ORDER_VALUE + 4, actual.precipitationOrder)
                assertValue(NEW_STARTING_ORDER_VALUE + 5, actual.sunAndMoonOrder)
            }
        )
    }

    @Test
    fun resetToDefault_shouldResetCorrectly() {
        testUpdate(
            update = {
                subject.resetToDefault()
            },
            assertValue = { actual ->
                assertValue(SettingsOrderSerializer.createDefaultValue(), actual)
            }
        )
    }

    @Test
    fun updateOrder_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                val orderProto = getTestSettingsOrderProto(NEW_STARTING_ORDER_VALUE)
                val orderData = NBOrderMapperData.protoToData(orderProto)
                subject.updateOrder(orderData)
            },
            assertValue = { actual ->
                assertValue(NEW_STARTING_ORDER_VALUE + 1, actual.currentWeatherOrder)
                assertValue(NEW_STARTING_ORDER_VALUE + 2, actual.dailyOrder)
                assertValue(NEW_STARTING_ORDER_VALUE + 3, actual.hourlyOrder)
                assertValue(NEW_STARTING_ORDER_VALUE + 4, actual.precipitationOrder)
                assertValue(NEW_STARTING_ORDER_VALUE + 5, actual.sunAndMoonOrder)
            }
        )
    }

}