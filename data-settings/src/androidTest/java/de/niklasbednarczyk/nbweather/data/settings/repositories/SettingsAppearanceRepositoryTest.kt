package de.niklasbednarczyk.nbweather.data.settings.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import de.niklasbednarczyk.nbweather.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ColorSchemeTypeData
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.SettingsAppearanceModelData
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ThemeTypeData
import de.niklasbednarczyk.nbweather.data.settings.proto.appearance.SettingsAppearanceProto
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsAppearanceSerializer
import de.niklasbednarczyk.nbweather.test.data.disk.repositories.NBDiskRepositoryTest
import org.junit.Test

class SettingsAppearanceRepositoryTest :
    NBDiskRepositoryTest<SettingsAppearanceProto, SettingsAppearanceModelData, SettingsAppearanceRepository>() {

    override val fileName: String
        get() = ConstantsDataSettings.DataStore.SETTINGS_APPEARANCE_FILE_NAME

    override val updateStartingProto: SettingsAppearanceProto
        get() = SettingsAppearanceProto
            .newBuilder()
            .setTheme(SettingsAppearanceProto.ThemeProto.DARK)
            .setColorScheme(SettingsAppearanceProto.ColorSchemeProto.BLUE)
            .build()

    override fun createSerializer(): Serializer<SettingsAppearanceProto> {
        return SettingsAppearanceSerializer()
    }

    override fun createRepository(dataStore: DataStore<SettingsAppearanceProto>): SettingsAppearanceRepository {
        return SettingsAppearanceRepository(dataStore)
    }

    @Test
    fun getData_themeLight_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsAppearanceProto
                .newBuilder()
                .setTheme(SettingsAppearanceProto.ThemeProto.LIGHT)
                .build(),
            assertValue = { actual ->
                assertValue(ThemeTypeData.LIGHT, actual.theme)
            }
        )
    }

    @Test
    fun getData_themeDark_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsAppearanceProto
                .newBuilder()
                .setTheme(SettingsAppearanceProto.ThemeProto.DARK)
                .build(),
            assertValue = { actual ->
                assertValue(ThemeTypeData.DARK, actual.theme)
            }
        )
    }

    @Test
    fun getData_themeSystemDefault_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsAppearanceProto
                .newBuilder()
                .setTheme(SettingsAppearanceProto.ThemeProto.SYSTEM_DEFAULT)
                .build(),
            assertValue = { actual ->
                assertValue(ThemeTypeData.SYSTEM_DEFAULT, actual.theme)
            }
        )
    }

    @Test
    fun getData_colorSchemeRed_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsAppearanceProto
                .newBuilder()
                .setColorScheme(SettingsAppearanceProto.ColorSchemeProto.RED)
                .build(),
            assertValue = { actual ->
                assertValue(ColorSchemeTypeData.RED, actual.colorScheme)
            }
        )
    }

    @Test
    fun getData_colorSchemeGreen_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsAppearanceProto
                .newBuilder()
                .setColorScheme(SettingsAppearanceProto.ColorSchemeProto.GREEN)
                .build(),
            assertValue = { actual ->
                assertValue(ColorSchemeTypeData.GREEN, actual.colorScheme)
            }
        )
    }

    @Test
    fun getData_colorSchemeBlue_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsAppearanceProto
                .newBuilder()
                .setColorScheme(SettingsAppearanceProto.ColorSchemeProto.BLUE)
                .build(),
            assertValue = { actual ->
                assertValue(ColorSchemeTypeData.BLUE, actual.colorScheme)
            }
        )
    }

    @Test
    fun getData_colorSchemeCyan_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsAppearanceProto
                .newBuilder()
                .setColorScheme(SettingsAppearanceProto.ColorSchemeProto.CYAN)
                .build(),
            assertValue = { actual ->
                assertValue(ColorSchemeTypeData.CYAN, actual.colorScheme)
            }
        )
    }

    @Test
    fun getData_colorSchemeMagenta_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsAppearanceProto
                .newBuilder()
                .setColorScheme(SettingsAppearanceProto.ColorSchemeProto.MAGENTA)
                .build(),
            assertValue = { actual ->
                assertValue(ColorSchemeTypeData.MAGENTA, actual.colorScheme)
            }
        )
    }

    @Test
    fun getData_colorSchemeYellow_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsAppearanceProto
                .newBuilder()
                .setColorScheme(SettingsAppearanceProto.ColorSchemeProto.YELLOW)
                .build(),
            assertValue = { actual ->
                assertValue(ColorSchemeTypeData.YELLOW, actual.colorScheme)
            }
        )
    }

    @Test
    fun updateTheme_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateTheme(ThemeTypeData.SYSTEM_DEFAULT)
            },
            assertValue = { actual ->
                assertValue(SettingsAppearanceProto.ThemeProto.SYSTEM_DEFAULT, actual.theme)
            }
        )
    }

    @Test
    fun updateColorScheme_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateColorScheme(ColorSchemeTypeData.GREEN)
            },
            assertValue = { actual ->
                assertValue(SettingsAppearanceProto.ColorSchemeProto.GREEN, actual.colorScheme)
            }
        )
    }

}