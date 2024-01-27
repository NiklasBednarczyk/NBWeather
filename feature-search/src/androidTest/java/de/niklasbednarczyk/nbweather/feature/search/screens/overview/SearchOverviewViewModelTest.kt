package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.isSuccessOrError
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.test.ui.screens.NBViewModelTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class SearchOverviewViewModelTest : NBViewModelTest {

    companion object {
        private val LAT_LONG_1 = Pair(38.8950368, -77.0365427)
        private val LAT_LONG_2 = Pair(40.17396, -80.2461714)

        private const val SEARCH_QUERY = "Washington"
    }

    private lateinit var subject: SearchOverviewViewModel

    private lateinit var geocodingRepository: GeocodingRepository

    @Before
    override fun setUp() {
        geocodingRepository = GeocodingRepository.createFake(context)
        subject = SearchOverviewViewModel(
            geocodingRepository = geocodingRepository
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
    fun onSearchQueryChanged_shouldSetUiStateCorrectly() = testScope.runTest {
        // Arrange + Act
        subject.onSearchQueryChanged(SEARCH_QUERY)

        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.searchedLocationsResource.isSuccessOrError
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsSuccess(uiState.searchedLocationsResource)
                assertListIsNotEmpty(uiState.searchedLocationsResource?.dataOrNull)
                assertEquals(SEARCH_QUERY, uiState.searchQuery)
            }
        )
    }

    @Test
    fun onSearchQueryChanged_empty_shouldSetResourceCorrectlyBeforeDebounce() = testScope.runTest {
        // Arrange + Act
        subject.onSearchQueryChanged("startingSearchQuery")
        subject.onSearchQueryChanged("")

        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.searchedLocationsResource == null && uiState.searchQuery.isEmpty()
            },
            collectData = { uiState ->
                // Assert
                assertNull(uiState.searchedLocationsResource)
                assertTrue(uiState.searchQuery.isEmpty())
            }
        )
    }

    @Test
    fun onSearchQueryChanged_notEmpty_shouldSetResourceCorrectlyBeforeDebounce() =
        testScope.runTest {
            // Arrange + Act
            subject.onSearchQueryChanged("startingSearchQuery")
            subject.onSearchQueryChanged(SEARCH_QUERY)

            subject.uiState.collectUntil(
                stopCollecting = { uiState ->
                    uiState.searchedLocationsResource is NBResource.Loading && uiState.searchQuery == SEARCH_QUERY
                },
                collectData = { uiState ->
                    // Assert
                    assertResourceIsLoading(uiState.searchedLocationsResource)
                    assertEquals(SEARCH_QUERY, uiState.searchQuery)
                }
            )
        }

    @Test
    fun onSearchActiveChange_false_shouldSetUiStateCorrectly() = testScope.runTest {
        // Arrange + Act
        subject.onSearchQueryChanged(SEARCH_QUERY)
        subject.onSearchActiveChange(false)

        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.searchedLocationsResource == null
            },
            collectData = { uiState ->
                // Assert
                assertNull(uiState.searchedLocationsResource)
                assertTrue(uiState.searchQuery.isEmpty())
                assertFalse(uiState.searchActive)
            }
        )
    }

    @Test
    fun onSearchActiveChange_true_shouldSetUiStateCorrectly() = testScope.runTest {
        // Arrange + Act
        subject.onSearchQueryChanged(SEARCH_QUERY)
        subject.onSearchActiveChange(true)

        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.searchedLocationsResource.isSuccessOrError
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsSuccess(uiState.searchedLocationsResource)
                assertListIsNotEmpty(uiState.searchedLocationsResource?.dataOrNull)
                assertEquals(SEARCH_QUERY, uiState.searchQuery)
                assertTrue(uiState.searchActive)
            }
        )
    }

    @Test
    fun setFindLocationInProgress_shouldSetUiStateCorrectly() = testScope.runTest {
        // Arrange + Act
        subject.setFindLocationInProgress(true)

        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.findLocationInProgress
            },
            collectData = { uiState ->
                // Assert
                assertTrue(uiState.findLocationInProgress)
            }
        )
    }

    @Test
    fun setDeletedLocation_shouldSetUiStateCorrectly() = testScope.runTest {
        // Arrange
        val deletedLocationArrange = createTestLocation()

        // Act
        subject.setDeletedLocation(deletedLocationArrange)

        // Assert
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.deletedLocation != null
            },
            collectData = { uiState ->
                val deletedLocationUiState = uiState.deletedLocation
                assertNotNull(deletedLocationUiState)
                assertEquals(deletedLocationArrange.latitude, deletedLocationUiState.latitude)
                assertEquals(deletedLocationArrange.longitude, deletedLocationUiState.longitude)
            }
        )

    }

    @Test
    fun deleteLocation_shouldDeleteLocation() = testScope.runTest {
        // Arrange
        LAT_LONG_1.insertOrUpdateCurrentLocation()

        // Act
        subject.deleteLocation(LAT_LONG_1.first, LAT_LONG_1.second)

        // Assert
        geocodingRepository.getCurrentLocation().collectUntil(
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

        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.deletedLocation != null
            },
            collectData = { uiState ->
                val deletedLocation = uiState.deletedLocation
                assertNotNull(deletedLocation)
                assertEquals(LAT_LONG_1.first, deletedLocation.latitude)
                assertEquals(LAT_LONG_1.second, deletedLocation.longitude)
            }
        )
    }

    @Test
    fun restoreDeletedLocation_shouldRestoreDeletedLocation() = testScope.runTest {
        // Arrange
        val deletedLocationArrange = createTestLocation()
        subject.setDeletedLocation(deletedLocationArrange)

        // Act
        subject.restoreDeletedLocation()

        // Assert
        geocodingRepository.getVisitedLocations().collectUntil(
            stopCollecting = { resource ->
                resource.isSuccessOrError && resource.dataOrNull?.isNotEmpty() == true
            },
            collectData = { resource ->
                assertListIsNotEmpty(resource.dataOrNull)

                val deletedLocation = resource.dataOrNull?.find { visitedLocation ->
                    visitedLocation.latitude == deletedLocationArrange.latitude
                            && visitedLocation.longitude == deletedLocationArrange.longitude
                }
                assertNotNull(deletedLocation)
            }
        )
    }

    @Test
    fun setCurrentLocation_shouldSetCurrentLocation() = testScope.runTest {
        // Arrange + Act
        subject.setCurrentLocation(LAT_LONG_2.first, LAT_LONG_2.second)

        // Assert
        geocodingRepository.getCurrentLocation().collectUntil(
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

    private suspend fun Pair<Double, Double>.insertOrUpdateCurrentLocation() {
        geocodingRepository.insertOrUpdateCurrentLocation(first, second)
    }

    private fun createTestLocation(): LocationModelData {
        return LocationModelData(
            latitude = 1.0,
            longitude = 2.0,
            name = null,
            localNames = null,
            country = null,
            state = null,
            lastVisitedTimestampEpochSeconds = 3L,
            order = null
        )
    }

}