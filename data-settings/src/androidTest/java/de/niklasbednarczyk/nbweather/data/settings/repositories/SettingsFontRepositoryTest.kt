package de.niklasbednarczyk.nbweather.data.settings.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontModel
import de.niklasbednarczyk.nbweather.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.nbweather.data.settings.proto.font.SettingsFontProto
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsFontSerializer
import de.niklasbednarczyk.nbweather.test.data.disk.repositories.NBDiskRepositoryTest
import org.junit.Test

class SettingsFontRepositoryTest :
    NBDiskRepositoryTest<SettingsFontProto, NBFontModel, SettingsFontRepository>() {

    companion object {
        private const val OLD_AXIS_VALUE = 1f
        private const val NEW_AXIS_VALUE = -1f
    }

    private fun getTestSettingsFontProto(axisValue: Float) = SettingsFontProto.newBuilder()
        .setSlant(axisValue)
        .setWidth(axisValue)
        .setWeight(axisValue)
        .setGrade(axisValue)
        .setCounterWidth(axisValue)
        .setThinStroke(axisValue)
        .setAscenderHeight(axisValue)
        .setDescenderDepth(axisValue)
        .setFigureHeight(axisValue)
        .setLowercaseHeight(axisValue)
        .setUppercaseHeight(axisValue)
        .build()

    override val fileName: String
        get() = ConstantsDataSettings.DataStore.SETTINGS_FONT_FILE_NAME

    override val updateStartingProto: SettingsFontProto
        get() = getTestSettingsFontProto(OLD_AXIS_VALUE)

    override fun createSerializer(): Serializer<SettingsFontProto> {
        return SettingsFontSerializer()
    }

    override fun createRepository(dataStore: DataStore<SettingsFontProto>): SettingsFontRepository {
        return SettingsFontRepository(dataStore)
    }

    @Test
    fun getData_shouldBeMappedCorrectly() {
        testGetData(
            proto = getTestSettingsFontProto(NEW_AXIS_VALUE),
            assertValue = { actual ->
                assertValue(NEW_AXIS_VALUE, actual.slant)
                assertValue(NEW_AXIS_VALUE, actual.width)
                assertValue(NEW_AXIS_VALUE, actual.weight)
                assertValue(NEW_AXIS_VALUE, actual.grade)
                assertValue(NEW_AXIS_VALUE, actual.counterWidth)
                assertValue(NEW_AXIS_VALUE, actual.thinStroke)
                assertValue(NEW_AXIS_VALUE, actual.ascenderHeight)
                assertValue(NEW_AXIS_VALUE, actual.descenderDepth)
                assertValue(NEW_AXIS_VALUE, actual.figureHeight)
                assertValue(NEW_AXIS_VALUE, actual.lowercaseHeight)
                assertValue(NEW_AXIS_VALUE, actual.uppercaseHeight)
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
                assertValue(SettingsFontSerializer.createDefaultValue(), actual)
            }
        )
    }

    @Test
    fun updateSlant_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateSlant(NEW_AXIS_VALUE)
            },
            assertValue = { actual ->
                assertValue(NEW_AXIS_VALUE, actual.slant)
            }
        )
    }

    @Test
    fun updateWidth_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateWidth(NEW_AXIS_VALUE)
            },
            assertValue = { actual ->
                assertValue(NEW_AXIS_VALUE, actual.width)
            }
        )
    }

    @Test
    fun updateWeight_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateWeight(NEW_AXIS_VALUE)
            },
            assertValue = { actual ->
                assertValue(NEW_AXIS_VALUE, actual.weight)
            }
        )
    }

    @Test
    fun updateGrade_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateGrade(NEW_AXIS_VALUE)
            },
            assertValue = { actual ->
                assertValue(NEW_AXIS_VALUE, actual.grade)
            }
        )
    }

    @Test
    fun updateCounterWidth_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateCounterWidth(NEW_AXIS_VALUE)
            },
            assertValue = { actual ->
                assertValue(NEW_AXIS_VALUE, actual.counterWidth)
            }
        )
    }

    @Test
    fun updateThinStroke_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateThinStroke(NEW_AXIS_VALUE)
            },
            assertValue = { actual ->
                assertValue(NEW_AXIS_VALUE, actual.thinStroke)
            }
        )
    }

    @Test
    fun updateAscenderHeight_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateAscenderHeight(NEW_AXIS_VALUE)
            },
            assertValue = { actual ->
                assertValue(NEW_AXIS_VALUE, actual.ascenderHeight)
            }
        )
    }

    @Test
    fun updateDescenderDepth_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateDescenderDepth(NEW_AXIS_VALUE)
            },
            assertValue = { actual ->
                assertValue(NEW_AXIS_VALUE, actual.descenderDepth)
            }
        )
    }

    @Test
    fun updateFigureHeight_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateFigureHeight(NEW_AXIS_VALUE)
            },
            assertValue = { actual ->
                assertValue(NEW_AXIS_VALUE, actual.figureHeight)
            }
        )
    }

    @Test
    fun updateLowercaseHeight_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateLowercaseHeight(NEW_AXIS_VALUE)
            },
            assertValue = { actual ->
                assertValue(NEW_AXIS_VALUE, actual.lowercaseHeight)
            }
        )
    }

    @Test
    fun updateUppercaseHeight_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateUppercaseHeight(NEW_AXIS_VALUE)
            },
            assertValue = { actual ->
                assertValue(NEW_AXIS_VALUE, actual.uppercaseHeight)
            }
        )
    }

}