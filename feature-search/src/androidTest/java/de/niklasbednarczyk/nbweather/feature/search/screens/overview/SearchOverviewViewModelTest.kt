package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.isSuccessOrError
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.nbweather.test.common.utils.createTemporaryFolderRule
import de.niklasbednarczyk.nbweather.test.ui.screens.NBUiTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import javax.inject.Inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

@HiltAndroidTest
class SearchOverviewViewModelTest : NBUiTest {

    companion object {
        private val LAT_LONG_1 = Pair(38.8950368, -77.0365427)
        private val LAT_LONG_2 = Pair(40.17396, -80.2461714)

        private const val SEARCH_TERM = "Washington"

        private val LANGUAGE = NBLanguageType.ENGLISH
    }

    @BindValue
    @get:Rule(order = NBUiTest.TEMPORARY_FOLDER_ORDER)
    override val temporaryFolder: TemporaryFolder = createTemporaryFolderRule()

    private lateinit var subject: SearchOverviewViewModel

    @Inject
    lateinit var geocodingRepository: GeocodingRepository

    @Inject
    lateinit var settingsDataRepository: SettingsDataRepository

    @Before
    override fun setUp() {
        super.setUp()
        subject = SearchOverviewViewModel(
            geocodingRepository = geocodingRepository,
            settingsDataRepository = settingsDataRepository
        )
    }

    @Test
    fun uiState_visitedLocationsInfoResource_shouldBeSetCorrectly() =
        testScope.runTest {
            // Arrange + Act
            subject.uiState.collectUntil(
                stopCollecting = { uiState ->
                    uiState.visitedLocationsInfoResource.isSuccessOrError
                },
                collectData = { uiState ->
                    // Assert
                    assertResourceIsSuccess(uiState.visitedLocationsInfoResource)
                }
            )
        }


    @Test
    fun onSearchTermChanged_shouldChangeSearchTerm() = testScope.runTest {
        // Arrange + Act
        subject.onSearchTermChanged(SEARCH_TERM)

        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.searchedLocationsResource.isSuccessOrError
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsSuccess(uiState.searchedLocationsResource)
                assertListIsNotEmpty(uiState.searchedLocationsResource?.dataOrNull)
                assertNotNullOrEmpty(uiState.searchTerm)
            }
        )
    }

    @Test
    fun removeVisitedLocation_shouldRemoveVisitedLocation() = testScope.runTest {
        // Arrange
        LAT_LONG_1.insertLocation()

        // Act
        subject.removeVisitedLocation(LAT_LONG_1.first, LAT_LONG_1.second)

        // Assert
        geocodingRepository.getCurrentLocation(LANGUAGE).collectUntil(
            stopCollecting = { resource ->
                val data = resource.dataOrNull
                val pair = Pair(data?.latitude, data?.longitude)
                resource.isSuccessOrError && pair != LAT_LONG_1
            },
            collectData = { resource ->
                assertResourceIsSuccess(resource)
                assertNotEquals(resource.dataOrNull?.latitude, LAT_LONG_1.first)
                assertNotEquals(resource.dataOrNull?.longitude, LAT_LONG_1.second)
            }
        )
    }

    @Test
    fun setCurrentLocation_shouldSetCurrentLocation() = testScope.runTest {
        // Arrange + Act
        subject.setCurrentLocation(LAT_LONG_2.first, LAT_LONG_2.second)

        // Assert
        geocodingRepository.getCurrentLocation(LANGUAGE).collectUntil(
            stopCollecting = { resource ->
                val data = resource.dataOrNull
                val pair = Pair(data?.latitude, data?.longitude)
                resource.isSuccessOrError && pair == LAT_LONG_2
            },
            collectData = { resource ->
                assertResourceIsSuccess(resource)
                assertEquals(resource.dataOrNull?.latitude, LAT_LONG_2.first)
                assertEquals(resource.dataOrNull?.longitude, LAT_LONG_2.second)
            }
        )
    }

    @Test
    fun startFindingLocation_shouldSetUiStateCorrectly() = testScope.runTest {
        // Arrange + Act
        subject.startFindingLocation()

        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.findingLocationInProgress
            },
            collectData = { uiState ->
                // Assert
                assertTrue(uiState.findingLocationInProgress)
                assertFalse(uiState.showNavigationIcon)
            }
        )
    }

    @Test
    fun stopFindingLocation_shouldSetUiStateCorrectly() = testScope.runTest {
        // Arrange + Act
        subject.stopFindingLocation()

        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                !uiState.findingLocationInProgress
            },
            collectData = { uiState ->
                // Assert
                assertFalse(uiState.findingLocationInProgress)
            }
        )
    }


    private suspend fun Pair<Double, Double>.insertLocation() {
        geocodingRepository.insertOrUpdateCurrentLocation(first, second)
    }


}