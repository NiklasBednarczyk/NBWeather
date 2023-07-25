package de.niklasbednarczyk.nbweather.data.settings.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import de.niklasbednarczyk.nbweather.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBColorSchemeType
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBAppearanceModel
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBThemeType
import de.niklasbednarczyk.nbweather.data.settings.proto.appearance.SettingsAppearanceProto
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsAppearanceSerializer
import de.niklasbednarczyk.nbweather.test.data.disk.repositories.NBDiskRepositoryTest
import org.junit.Test

class SettingsAppearanceRepositoryTest :
    NBDiskRepositoryTest<SettingsAppearanceProto, NBAppearanceModel, SettingsAppearanceRepository>() {

    override val fileName: String
        get() = ConstantsDataSettings.DataStore.SETTINGS_APPEARANCE_FILE_NAME

    override val updateStartingProto: SettingsAppearanceProto
        get() = SettingsAppearanceProto.newBuilder()
            .setUseDeviceTheme(false)
            .setTheme(SettingsAppearanceProto.ThemeProto.DARK)
            .setUseDynamicColorScheme(false)
            .setColorScheme(SettingsAppearanceProto.ColorSchemeProto.BLUE)
            .build()

    override fun createSerializer(): Serializer<SettingsAppearanceProto> {
        return SettingsAppearanceSerializer(context)
    }

    override fun createRepository(dataStore: DataStore<SettingsAppearanceProto>): SettingsAppearanceRepository {
        return SettingsAppearanceRepository(dataStore)
    }

    @Test
    fun getData_useDeviceTheme_false_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsAppearanceProto
                .newBuilder()
                .setUseDeviceTheme(false)
                .build(),
            assertValue = { actual ->
                assertValue(false, actual.useDeviceTheme)
            }
        )
    }

    @Test
    fun getData_useDeviceTheme_true_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsAppearanceProto
                .newBuilder()
                .setUseDeviceTheme(true)
                .build(),
            assertValue = { actual ->
                assertValue(true, actual.useDeviceTheme)
            }
        )
    }

    @Test
    fun getData_theme_light_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsAppearanceProto
                .newBuilder()
                .setTheme(SettingsAppearanceProto.ThemeProto.LIGHT)
                .build(),
            assertValue = { actual ->
                assertValue(NBThemeType.LIGHT, actual.theme)
            }
        )
    }

    @Test
    fun getData_theme_dark_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsAppearanceProto
                .newBuilder()
                .setTheme(SettingsAppearanceProto.ThemeProto.DARK)
                .build(),
            assertValue = { actual ->
                assertValue(NBThemeType.DARK, actual.theme)
            }
        )
    }

    @Test
    fun getData_useDynamicColorScheme_false_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsAppearanceProto
                .newBuilder()
                .setUseDynamicColorScheme(false)
                .build(),
            assertValue = { actual ->
                assertValue(false, actual.useDynamicColorScheme)
            }
        )
    }

    @Test
    fun getData_useDynamicColorScheme_true_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsAppearanceProto
                .newBuilder()
                .setUseDynamicColorScheme(true)
                .build(),
            assertValue = { actual ->
                assertValue(true, actual.useDynamicColorScheme)
            }
        )
    }

    @Test
    fun getData_colorScheme_blue_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsAppearanceProto
                .newBuilder()
                .setColorScheme(SettingsAppearanceProto.ColorSchemeProto.BLUE)
                .build(),
            assertValue = { actual ->
                assertValue(NBColorSchemeType.BLUE, actual.colorScheme)
            }
        )
    }

    @Test
    fun getData_colorScheme_green_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsAppearanceProto
                .newBuilder()
                .setColorScheme(SettingsAppearanceProto.ColorSchemeProto.GREEN)
                .build(),
            assertValue = { actual ->
                assertValue(NBColorSchemeType.GREEN, actual.colorScheme)
            }
        )
    }

    @Test
    fun getData_colorScheme_red_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsAppearanceProto
                .newBuilder()
                .setColorScheme(SettingsAppearanceProto.ColorSchemeProto.RED)
                .build(),
            assertValue = { actual ->
                assertValue(NBColorSchemeType.RED, actual.colorScheme)
            }
        )
    }

    @Test
    fun getData_colorScheme_yellow_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsAppearanceProto
                .newBuilder()
                .setColorScheme(SettingsAppearanceProto.ColorSchemeProto.YELLOW)
                .build(),
            assertValue = { actual ->
                assertValue(NBColorSchemeType.YELLOW, actual.colorScheme)
            }
        )
    }

    @Test
    fun updateUseDeviceTheme_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateUseDeviceTheme(true)
            },
            assertValue = { actual ->
                assertValue(true, actual.useDeviceTheme)
            }
        )
    }

    @Test
    fun updateTheme_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateTheme(NBThemeType.LIGHT)
            },
            assertValue = { actual ->
                assertValue(SettingsAppearanceProto.ThemeProto.LIGHT, actual.theme)
            }
        )
    }

    @Test
    fun updateUseDynamicColorScheme_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateUseDynamicColorScheme(true)
            },
            assertValue = { actual ->
                assertValue(true, actual.useDynamicColorScheme)
            }
        )
    }

    @Test
    fun updateColorScheme_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateColorScheme(NBColorSchemeType.GREEN)
            },
            assertValue = { actual ->
                assertValue(SettingsAppearanceProto.ColorSchemeProto.GREEN, actual.colorScheme)
            }
        )
    }

}