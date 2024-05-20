package de.niklasbednarczyk.nbweather

import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.isSuccessOrError
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsFontRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsOrderRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsUnitsRepository
import de.niklasbednarczyk.nbweather.navigation.drawer.NBNavigationDrawerItem
import de.niklasbednarczyk.nbweather.test.common.utils.createTemporaryFolderRule
import de.niklasbednarczyk.nbweather.test.ui.screens.NBViewModelTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class MainViewModelTest : NBViewModelTest {

    @get:Rule
    val temporaryFolder: TemporaryFolder = createTemporaryFolderRule()

    private lateinit var subject: MainViewModel

    private lateinit var geocodingRepository: GeocodingRepository

    @Before
    override fun setUp() {
        geocodingRepository = GeocodingRepository.createFake(context)
        subject = MainViewModel(
            geocodingRepository = geocodingRepository,
            settingsAppearanceRepository = SettingsAppearanceRepository.createFake(
                temporaryFolder,
                context
            ),
            settingsFontRepository = SettingsFontRepository.createFake(temporaryFolder),
            settingsOrderRepository = SettingsOrderRepository.createFake(temporaryFolder),
            settingsUnitsRepository = SettingsUnitsRepository.createFake(temporaryFolder)
        )
    }

    @Test
    fun uiState_appearance_shouldBeSetCorrectly() = testScope.runTest {
        // Arrange + Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.appearance != null
            },
            collectData = { uiState ->
                // Assert
                assertNotNull(uiState.appearance)
            }
        )
    }

    @Test
    fun uiState_font_shouldBeSetCorrectly() = testScope.runTest {
        // Arrange + Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.font != null
            },
            collectData = { uiState ->
                // Assert
                assertNotNull(uiState.font)
            }
        )
    }

    @Test
    fun uiState_order_shouldBeSetCorrectly() = testScope.runTest {
        // Arrange + Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.order != null
            },
            collectData = { uiState ->
                // Assert
                assertNotNull(uiState.order)
            }
        )
    }

    @Test
    fun uiState_units_shouldBeSetCorrectly() = testScope.runTest {
        // Arrange + Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.units != null
            },
            collectData = { uiState ->
                // Assert
                assertNotNull(uiState.units)
            }
        )
    }

    @Test
    fun uiState_viewDataResource_shouldBeSetCorrectly() = testScope.runTest {
        // Arrange + Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.viewDataResource.isSuccessOrError
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsSuccess(uiState.viewDataResource)

                val viewData = uiState.viewDataResource.dataOrNull
                assertNotNull(viewData)

                testDividerList(
                    items = viewData.drawerItems,
                    dividerKlass = NBNavigationDrawerItem.Divider::class.java,
                    headerKlass = NBNavigationDrawerItem.Headline::class.java
                )

                assertNull(viewData.initialCurrentLocation)
            }
        )
    }

}