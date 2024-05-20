package de.niklasbednarczyk.nbweather.feature.settings.screens.order

import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsOrderRepository
import de.niklasbednarczyk.nbweather.test.common.utils.createTemporaryFolderRule
import de.niklasbednarczyk.nbweather.test.ui.screens.NBViewModelTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class SettingsOrderViewModelTest : NBViewModelTest {

    @get:Rule
    val temporaryFolder: TemporaryFolder = createTemporaryFolderRule()

    private lateinit var subject: SettingsOrderViewModel

    private lateinit var settingsOrderRepository: SettingsOrderRepository

    @Before
    override fun setUp() {
        settingsOrderRepository = SettingsOrderRepository.createFake(temporaryFolder)
        subject = SettingsOrderViewModel(
            settingsOrderRepository = settingsOrderRepository
        )
    }

    @Test
    fun uiState_items_shouldBeSetCorrectly() = testScope.runTest {
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.items.isNotEmpty()
            },
            collectData = { uiState ->
                assertListIsNotEmpty(uiState.items)
            }
        )
    }

}