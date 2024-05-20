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

    private lateinit var subjectWithoutArgsAndWithoutLocations: SearchOverviewViewModel
    private lateinit var subjectWithoutArgsAndWithLocations: SearchOverviewViewModel
    private lateinit var subjectWithIsStartDestinationFalseAndWithoutLocations: SearchOverviewViewModel
    private lateinit var subjectWithIsStartDestinationFalseAndWithLocations: SearchOverviewViewModel
    private lateinit var subjectWithIsStartDestinationTrueAndWithoutLocations: SearchOverviewViewModel
    private lateinit var subjectWithIsStartDestinationTrueAndWithLocations: SearchOverviewViewModel

    private lateinit var geocodingRepositoryWithoutLocations: GeocodingRepository
    private lateinit var geocodingRepositoryWithLocations: GeocodingRepository

    @Before
    override fun setUp() = testScope.runTest {
        geocodingRepositoryWithoutLocations = GeocodingRepository.createFake(context)
        geocodingRepositoryWithLocations = GeocodingRepository.createFake(context)

        geocodingRepositoryWithLocations.insertLocationAndSetAsVisited(LAT_LONG_1)
        geocodingRepositoryWithLocations.insertLocationAndSetAsVisited(LAT_LONG_2)

        val savedStateHandleWithoutArgs = createTestSaveStateHandle()
        val savedStateHandleWithIsStartDestinationFalse = createTestSaveStateHandle(
            NBArgumentKeys.IsStartDestination to false
        )
        val savedStateHandleWithIsStartDestinationTrue = createTestSaveStateHandle(
            NBArgumentKeys.IsStartDestination to true
        )

        subjectWithoutArgsAndWithoutLocations = SearchOverviewViewModel(
            savedStateHandle = savedStateHandleWithoutArgs,
            geocodingRepository = geocodingRepositoryWithoutLocations
        )
        subjectWithoutArgsAndWithLocations = SearchOverviewViewModel(
            savedStateHandle = savedStateHandleWithoutArgs,
            geocodingRepository = geocodingRepositoryWithLocations
        )
        subjectWithIsStartDestinationFalseAndWithoutLocations = SearchOverviewViewModel(
            savedStateHandle = savedStateHandleWithIsStartDestinationFalse,
            geocodingRepository = geocodingRepositoryWithoutLocations
        )
        subjectWithIsStartDestinationFalseAndWithLocations = SearchOverviewViewModel(
            savedStateHandle = savedStateHandleWithIsStartDestinationFalse,
            geocodingRepository = geocodingRepositoryWithLocations
        )
        subjectWithIsStartDestinationTrueAndWithoutLocations = SearchOverviewViewModel(
            savedStateHandle = savedStateHandleWithIsStartDestinationTrue,
            geocodingRepository = geocodingRepositoryWithoutLocations
        )
        subjectWithIsStartDestinationTrueAndWithLocations = SearchOverviewViewModel(
            savedStateHandle = savedStateHandleWithIsStartDestinationTrue,
            geocodingRepository = geocodingRepositoryWithLocations
        )
    }

    @Test
    fun uiState_args_withoutArgs_withoutLocations_shouldBeSetCorrectly() = testScope.runTest {
        subjectWithoutArgsAndWithoutLocations.testUiStateArgs(
            expectedIsStartDestination = null,
            expectedVisitedLocationsIsEmptyWhenNotAllowed = false,
            expectedPopBackStackEnabled = false
        )
    }

    @Test
    fun uiState_args_withoutArgs_withLocations_shouldBeSetCorrectly() = testScope.runTest {
        subjectWithoutArgsAndWithLocations.testUiStateArgs(
            expectedIsStartDestination = null,
            expectedVisitedLocationsIsEmptyWhenNotAllowed = false,
            expectedPopBackStackEnabled = false
        )
    }

    @Test
    fun uiState_args_withIsStartDestinationFalse_withoutLocations_shouldBeSetCorrectly() =
        testScope.runTest {
            subjectWithIsStartDestinationFalseAndWithoutLocations.testUiStateArgs(
                expectedIsStartDestination = false,
                expectedVisitedLocationsIsEmptyWhenNotAllowed = true,
                expectedPopBackStackEnabled = false
            )
        }

    @Test
    fun uiState_args_withIsStartDestinationFalse_withLocations_shouldBeSetCorrectly() =
        testScope.runTest {
            subjectWithIsStartDestinationFalseAndWithLocations.testUiStateArgs(
                expectedIsStartDestination = false,
                expectedVisitedLocationsIsEmptyWhenNotAllowed = false,
                expectedPopBackStackEnabled = true
            )
        }

    @Test
    fun uiState_args_withIsStartDestinationTrue_withoutLocations_shouldBeSetCorrectly() =
        testScope.runTest {
            subjectWithIsStartDestinationTrueAndWithoutLocations.testUiStateArgs(
                expectedIsStartDestination = true,
                expectedVisitedLocationsIsEmptyWhenNotAllowed = false,
                expectedPopBackStackEnabled = false
            )
        }

    @Test
    fun uiState_args_withIsStartDestinationTrue_withLocations_shouldBeSetCorrectly() =
        testScope.runTest {
            subjectWithIsStartDestinationTrueAndWithLocations.testUiStateArgs(
                expectedIsStartDestination = true,
                expectedVisitedLocationsIsEmptyWhenNotAllowed = false,
                expectedPopBackStackEnabled = false
            )
        }

    @Test
    fun uiState_visitedLocationsResource_withoutLocations_shouldBeSetCorrectly() =
        testScope.runTest {
            subjectWithoutArgsAndWithoutLocations.testUiStateVisitedLocationsResource(
                expectedSize = 0
            )
        }

    @Test
    fun uiState_visitedLocationsResource_withLocations_shouldBeSetCorrectly() = testScope.runTest {
        subjectWithoutArgsAndWithLocations.testUiStateVisitedLocationsResource(
            expectedSize = 2
        )
    }

    @Test
    fun onSearchQueryChanged_shouldSetUiStateCorrectly() = testScope.runTest {
        // Arrange + Act
        subjectWithoutArgsAndWithoutLocations.onSearchQueryChange(SEARCH_QUERY)

        subjectWithoutArgsAndWithoutLocations.uiState.collectUntil(
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
        subjectWithoutArgsAndWithoutLocations.onSearchQueryChange("startingSearchQuery")
        subjectWithoutArgsAndWithoutLocations.onSearchQueryChange("")

        subjectWithoutArgsAndWithoutLocations.uiState.collectUntil(
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
            subjectWithoutArgsAndWithoutLocations.onSearchQueryChange("startingSearchQuery")
            subjectWithoutArgsAndWithoutLocations.onSearchQueryChange(SEARCH_QUERY)

            subjectWithoutArgsAndWithoutLocations.uiState.collectUntil(
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
        subjectWithoutArgsAndWithoutLocations.onSearchQueryChange(SEARCH_QUERY)
        subjectWithoutArgsAndWithoutLocations.onSearchActiveChange(false)

        subjectWithoutArgsAndWithoutLocations.uiState.collectUntil(
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
        subjectWithoutArgsAndWithoutLocations.onSearchQueryChange(SEARCH_QUERY)
        subjectWithoutArgsAndWithoutLocations.onSearchActiveChange(true)

        subjectWithoutArgsAndWithoutLocations.uiState.collectUntil(
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
        subjectWithoutArgsAndWithoutLocations.setFindLocationInProgress(true)

        subjectWithoutArgsAndWithoutLocations.uiState.collectUntil(
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
    fun deleteLocation_withoutLocations_shouldReturnNull() = testScope.runTest {
        // Arrange + Act
        val deletedLocation = subjectWithoutArgsAndWithoutLocations.deleteLocation(
            latitude = LAT_LONG_1.first,
            longitude = LAT_LONG_1.second
        )

        // Assert
        assertNull(deletedLocation)
    }

    @Test
    fun deleteLocation_withLocations_shouldDeleteLocation() = testScope.runTest {
        // Arrange + Act
        val deletedLocation = subjectWithoutArgsAndWithLocations.deleteLocation(
            latitude = LAT_LONG_1.first,
            longitude = LAT_LONG_1.second
        )

        // Assert
        assertNotNull(deletedLocation)
        assertEquals(LAT_LONG_1.first, deletedLocation.latitude)
        assertEquals(LAT_LONG_1.second, deletedLocation.longitude)

        geocodingRepositoryWithLocations.getCurrentLocation().collectUntil(
            stopCollecting = { resource ->
                val data = resource.dataOrNull
                val pair = Pair(data?.latitude, data?.longitude)
                resource.isSuccessOrError && pair == LAT_LONG_2
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

    private suspend fun SearchOverviewViewModel.testUiStateArgs(
        expectedIsStartDestination: Boolean?,
        expectedVisitedLocationsIsEmptyWhenNotAllowed: Boolean,
        expectedPopBackStackEnabled: Boolean
    ) {
        // Arrange + Act
        uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.isStartDestination == expectedIsStartDestination && uiState.visitedLocationsResource.isSuccessOrError
            },
            collectData = { uiState ->
                // Assert
                assertEquals(expectedIsStartDestination, uiState.isStartDestination)
                assertEquals(
                    expectedVisitedLocationsIsEmptyWhenNotAllowed,
                    uiState.visitedLocationsIsEmptyWhenNotAllowed
                )
                assertEquals(expectedPopBackStackEnabled, uiState.popBackStackEnabled)
            }
        )
    }

    private suspend fun SearchOverviewViewModel.testUiStateVisitedLocationsResource(
        expectedSize: Int
    ) {
        // Arrange + Act
        uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.visitedLocationsResource.isSuccessOrError && uiState.visitedLocationsResource.dataOrNull?.size == expectedSize
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsSuccess(uiState.visitedLocationsResource)
                assertListHasSize(uiState.visitedLocationsResource.dataOrNull, expectedSize)
            }
        )
    }

    private suspend fun GeocodingRepository.insertLocationAndSetAsVisited(
        pair: Pair<Double, Double>
    ) {
        val latitude = pair.first
        val longitude = pair.second

        getAndInsertLocation(
            latitude = latitude,
            longitude = longitude
        )!!
        val location = deleteLocation(
            latitude = latitude,
            longitude = longitude
        )!!
        val newLocation = location.copy(
            lastVisitedTimestampEpochSeconds = 1L,
            order = 2L
        )
        insertLocation(newLocation)
    }

}