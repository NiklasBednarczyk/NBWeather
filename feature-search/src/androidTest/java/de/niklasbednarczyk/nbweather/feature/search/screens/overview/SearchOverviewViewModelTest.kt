package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.isSuccessOrError
import de.niklasbednarczyk.nbweather.core.ui.navigation.NBArgumentKeys
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
        private val LAT_LONG_1 = Pair(40.7127281, -74.0060152)
        private val LAT_LONG_2 = Pair(52.5170365, 13.3888599)

        private const val SEARCH_QUERY = "Sydney"
    }

    private lateinit var subjectWithoutArgs: SearchOverviewViewModel
    private lateinit var subjectWithIsStartDestinationFalse: SearchOverviewViewModel
    private lateinit var subjectWithIsStartDestinationTrue: SearchOverviewViewModel

    private lateinit var geocodingRepository: GeocodingRepository

    @Before
    override fun setUp() {
        geocodingRepository = GeocodingRepository.createFake(context)
        subjectWithoutArgs = SearchOverviewViewModel(
            savedStateHandle = createTestSaveStateHandle(),
            geocodingRepository = geocodingRepository
        )
        subjectWithIsStartDestinationFalse = SearchOverviewViewModel(
            savedStateHandle = createTestSaveStateHandle(
                NBArgumentKeys.IsStartDestination to false
            ),
            geocodingRepository = geocodingRepository
        )
        subjectWithIsStartDestinationTrue = SearchOverviewViewModel(
            savedStateHandle = createTestSaveStateHandle(
                NBArgumentKeys.IsStartDestination to true
            ),
            geocodingRepository = geocodingRepository
        )
    }

    @Test
    fun uiState_isStartDestination_withoutArgs_shouldBeSetCorrectly() = testScope.runTest {
        subjectWithoutArgs.testUiStateIsStartDestination(
            expectedIsStartDestination = null,
            expectedPopBackStackEnabled = false
        )
    }

    @Test
    fun uiState_isStartDestination_withIsStartDestinationFalse_shouldBeSetCorrectly() =
        testScope.runTest {
            subjectWithIsStartDestinationFalse.testUiStateIsStartDestination(
                expectedIsStartDestination = false,
                expectedPopBackStackEnabled = true
            )
        }

    @Test
    fun uiState_isStartDestination_withIsStartDestinationTrue_shouldBeSetCorrectly() =
        testScope.runTest {
            subjectWithIsStartDestinationTrue.testUiStateIsStartDestination(
                expectedIsStartDestination = true,
                expectedPopBackStackEnabled = false
            )
        }

    @Test
    fun uiState_visitedLocationsResource_shouldBeSetCorrectly() = testScope.runTest {
        // Arrange
        LAT_LONG_1.insertLocationAndSetAsVisited()
        LAT_LONG_2.insertLocationAndSetAsVisited()

        //Act
        subjectWithoutArgs.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.visitedLocationsResource.isSuccessOrError && uiState.visitedLocationsResource.dataOrNull?.size == 2
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsSuccess(uiState.visitedLocationsResource)
                assertListHasSize(uiState.visitedLocationsResource.dataOrNull, 2)
            }
        )
    }

    @Test
    fun onSearchQueryChanged_shouldSetUiStateCorrectly() = testScope.runTest {
        // Arrange + Act
        subjectWithoutArgs.onSearchQueryChange(SEARCH_QUERY)

        subjectWithoutArgs.uiState.collectUntil(
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
        subjectWithoutArgs.onSearchQueryChange("startingSearchQuery")
        subjectWithoutArgs.onSearchQueryChange("")

        subjectWithoutArgs.uiState.collectUntil(
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
            subjectWithoutArgs.onSearchQueryChange("startingSearchQuery")
            subjectWithoutArgs.onSearchQueryChange(SEARCH_QUERY)

            subjectWithoutArgs.uiState.collectUntil(
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
        subjectWithoutArgs.onSearchQueryChange(SEARCH_QUERY)
        subjectWithoutArgs.onSearchActiveChange(false)

        subjectWithoutArgs.uiState.collectUntil(
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
        subjectWithoutArgs.onSearchQueryChange(SEARCH_QUERY)
        subjectWithoutArgs.onSearchActiveChange(true)

        subjectWithoutArgs.uiState.collectUntil(
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
        subjectWithoutArgs.setFindLocationInProgress(true)

        subjectWithoutArgs.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.findLocationInProgress
            },
            collectData = { uiState ->
                // Assert
                assertTrue(uiState.findLocationInProgress)
                assertFalse(uiState.searchBarEnabled)
            }
        )
    }

    @Test
    fun deleteLocation_shouldDeleteLocation() = testScope.runTest {
        // Arrange
        LAT_LONG_1.insertLocationAndSetAsVisited()
        LAT_LONG_2.insertLocationAndSetAsVisited()

        // Act
        val deletedLocation = subjectWithoutArgs.deleteLocation(LAT_LONG_1.first, LAT_LONG_1.second)

        // Assert
        assertNotNull(deletedLocation)
        assertEquals(LAT_LONG_1.first, deletedLocation.latitude)
        assertEquals(LAT_LONG_1.second, deletedLocation.longitude)

        geocodingRepository.getCurrentLocation().collectUntil(
            stopCollecting = { resource ->
                val data = resource.dataOrNull
                val pair = Pair(data?.latitude, data?.longitude)
                resource.isSuccessOrError && pair != LAT_LONG_1
            },
            collectData = { resource ->
                assertResourceIsSuccess(resource)
                val latitude = resource.dataOrNull?.latitude
                val longitude = resource.dataOrNull?.longitude

                assertNotEquals(LAT_LONG_1.first, latitude)
                assertNotEquals(LAT_LONG_1.second, longitude)

                assertEquals(LAT_LONG_2.first, latitude)
                assertEquals(LAT_LONG_2.second, longitude)
            }
        )
    }

    private suspend fun SearchOverviewViewModel.testUiStateIsStartDestination(
        expectedIsStartDestination: Boolean?,
        expectedPopBackStackEnabled: Boolean
    ) {
        // Arrange + Act
        uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.isStartDestination == expectedIsStartDestination
            },
            collectData = { uiState ->
                // Assert
                assertEquals(expectedIsStartDestination, uiState.isStartDestination)
                assertEquals(expectedPopBackStackEnabled, uiState.popBackStackEnabled)
            }
        )
    }

    private suspend fun Pair<Double, Double>.insertLocationAndSetAsVisited() {
        geocodingRepository.getAndInsertLocation(
            latitude = first,
            longitude = second
        )!!
        val location = geocodingRepository.deleteLocation(
            latitude = first,
            longitude = second
        )!!
        val newLocation = location.copy(
            lastVisitedTimestampEpochSeconds = 1L,
            order = 2L
        )
        geocodingRepository.insertLocation(newLocation)
    }

}