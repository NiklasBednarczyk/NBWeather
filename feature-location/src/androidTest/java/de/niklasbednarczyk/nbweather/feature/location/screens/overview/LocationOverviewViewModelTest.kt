package de.niklasbednarczyk.nbweather.feature.location.screens.overview

import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.isSuccessOrError
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.LocationOverviewNavigationBarItem
import de.niklasbednarczyk.nbweather.test.common.utils.createTemporaryFolderRule
import de.niklasbednarczyk.nbweather.test.ui.screens.NBUiTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import javax.inject.Inject
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@HiltAndroidTest
class LocationOverviewViewModelTest : NBUiTest {

    companion object {
        private const val LATITUDE = 40.17396
        private const val LONGITUDE = -80.2461714
    }

    @BindValue
    @get:Rule(order = NBUiTest.TEMPORARY_FOLDER_ORDER)
    override val temporaryFolder: TemporaryFolder = createTemporaryFolderRule()

    private lateinit var subject: LocationOverviewViewModel

    @Inject
    lateinit var geocodingRepository: GeocodingRepository

    @Inject
    lateinit var oneCallRepository: OneCallRepository

    @Inject
    lateinit var settingsDataRepository: SettingsDataRepository

    @Before
    override fun setUp() {
        super.setUp()
        subject = LocationOverviewViewModel(
            geocodingRepository = geocodingRepository,
            oneCallRepository = oneCallRepository,
            settingsDataRepository = settingsDataRepository
        )
    }

    @Test
    fun uiState_locationResource_shouldNotGetDataWhenCurrentLocationNotSet() = testScope.runTest {
        // Arrange + Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.locationResource.isSuccessOrError
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsSuccess(uiState.locationResource)
                assertNull(uiState.locationResource?.dataOrNull)
            }
        )
    }

    @Test
    fun uiState_locationResource_shouldGetDataWhenCurrentLocationSet() = testScope.runTest {
        // Arrange
        insertCurrentLocation()

        // Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.locationResource.isSuccessOrError && uiState.locationResource?.dataOrNull != null
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsSuccess(uiState.locationResource)
                assertNotNull(uiState.locationResource?.dataOrNull)
            }
        )
    }

    @Test
    fun uiState_viewDataResource_shouldNotGetDataWhenCurrentLocationNotSet() = testScope.runTest {
        // Arrange + Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.viewDataResource is NBResource.Loading
            },
            collectData = { uiState ->
                assertResourceIsLoading(uiState.viewDataResource)
                assertNull(uiState.viewDataResource?.dataOrNull)
            }
        )
    }

    @Test
    fun uiState_viewDataResource_shouldGetDataWhenCurrentLocationSet() = testScope.runTest {
        // Arrange
        insertCurrentLocation()

        // Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.viewDataResource.isSuccessOrError
            },
            collectData = { uiState ->
                assertResourceIsSuccess(uiState.viewDataResource)
                assertListIsNotEmpty(uiState.viewDataResource?.dataOrNull?.todayCardItems)
                assertMapIsNotEmpty(uiState.viewDataResource?.dataOrNull?.hourlyMap)
                assertListIsNotEmpty(uiState.viewDataResource?.dataOrNull?.dailyModels)
            }
        )
    }

    @Test
    fun updateSelectedNavigationBarItem_shouldUpdateNavigationBarItem() = testScope.runTest {
        // Arrange
        val selectedNavigationBarItemOld = subject.uiState.value.selectedNavigationBarItem
        val selectedNavigationBarItemNew = LocationOverviewNavigationBarItem.DAILY

        // Act
        subject.updateSelectedNavigationBarItem(selectedNavigationBarItemNew)

        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.selectedNavigationBarItem != selectedNavigationBarItemOld
            },
            collectData = { uiState ->
                // Assert
                assertEquals(selectedNavigationBarItemNew, uiState.selectedNavigationBarItem)
                assertNotEquals(selectedNavigationBarItemOld, uiState.selectedNavigationBarItem)
            }
        )
    }

    private suspend fun insertCurrentLocation() {
        geocodingRepository.insertOrUpdateCurrentLocation(LATITUDE, LONGITUDE)
    }

}