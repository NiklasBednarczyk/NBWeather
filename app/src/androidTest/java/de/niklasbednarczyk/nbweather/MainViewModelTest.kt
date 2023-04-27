package de.niklasbednarczyk.nbweather

import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.isSuccessOrError
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.nbweather.navigation.NBNavigationDrawerItem
import de.niklasbednarczyk.nbweather.test.common.utils.createTemporaryFolderRule
import de.niklasbednarczyk.nbweather.test.ui.screens.NBViewModelTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class MainViewModelTest : NBViewModelTest {

    companion object {
        private const val LATITUDE = 41.2990848
        private const val LONGITUDE = -91.6923661

        private val LANGUAGE = NBLanguageType.ENGLISH
    }

    @get:Rule
    val temporaryFolder: TemporaryFolder = createTemporaryFolderRule()

    private lateinit var subject: MainViewModel

    private lateinit var geocodingRepository: GeocodingRepository

    @Before
    override fun setUp() {
        geocodingRepository = GeocodingRepository.createFake(context)
        subject = MainViewModel(
            geocodingRepository = geocodingRepository,
            settingsAppearanceRepository = SettingsAppearanceRepository.createFake(temporaryFolder),
            settingsDataRepository = SettingsDataRepository.createFake(temporaryFolder, context)
        )
    }

    @Test
    fun uiState_drawerItems_shouldHaveCorrectDividers() = testScope.runTest {
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.drawerItems.isNotEmpty()
            },
            collectData = { uiState ->
                testDividerList(
                    items = uiState.drawerItems,
                    dividerClassJava = NBNavigationDrawerItem.Divider::class.java
                )
            }
        )
    }

    @Test
    fun uiState_isInitialCurrentLocationSetResource_shouldBeSetCorrectly() = testScope.runTest {
        // Arrange + Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.isInitialCurrentLocationSetResource.isSuccessOrError
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsSuccess(uiState.isInitialCurrentLocationSetResource)
                assertNotNull(uiState.isInitialCurrentLocationSetResource?.dataOrNull)
            }
        )
    }

    @Test
    fun uiState_settingsAppearance_shouldBeSetCorrectly() = testScope.runTest {
        // Arrange + Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.settingsAppearance != null
            },
            collectData = { uiState ->
                // Assert
                assertNotNull(uiState.settingsAppearance)
            }
        )
    }

    @Test
    fun setCurrentLocation_shouldSetCurrentLocation() = testScope.runTest {
        // Arrange + Act
        subject.setCurrentLocation(LATITUDE, LONGITUDE)

        // Assert
        geocodingRepository.getCurrentLocation(LANGUAGE).collectUntil(
            stopCollecting = { resource ->
                resource.isSuccessOrError && resource.dataOrNull != null
            }
        ) { resource ->
            val data = resource.dataOrNull
            assertNotNull(data)
            assertEquals(LATITUDE, data.latitude)
            assertEquals(LONGITUDE, data.longitude)
        }
    }

}