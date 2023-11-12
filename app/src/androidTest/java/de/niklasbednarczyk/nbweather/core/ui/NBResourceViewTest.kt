package de.niklasbednarczyk.nbweather.core.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBErrorType
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithLoadingView
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test

@Suppress("TestFunctionName")
class NBResourceViewTest : NBComposableTest() {

    companion object {

        private const val TEXT_ERROR_NO_INTERNET = "ErrorNoInternet"
        private const val TEXT_ERROR_UNKNOWN = "ErrorUnknown"
        private const val TEXT_LOADING = "Loading"
        private const val TEXT_SUCCESS = "Success"

        private val RESOURCE_ERROR_NO_INTERNET = NBResource.Error(NBErrorType.NO_INTERNET)
        private val RESOURCE_ERROR_UNKNOWN = NBResource.Error(NBErrorType.UNKNOWN)
        private val RESOURCE_LOADING = NBResource.Loading
        private val RESOURCE_SUCCESS = NBResource.Success(TEXT_SUCCESS)

    }

    @Test
    fun resourceWithLoadingView_errorNoInternet_shouldRenderCorrectly() {
        // Arrange
        val resource = RESOURCE_ERROR_NO_INTERNET

        // Act
        setContentResourceWithLoadingView(resource)

        // Assert
        assertCompose {
            assertErrorNoInternetShouldBeDisplayed(true)
            assertErrorUnknownShouldBeDisplayed(false)
            assertLoadingShouldBeDisplayed(false)
            assertSuccessShouldBeDisplayed(false)
        }
    }

    @Test
    fun resourceWithLoadingView_errorUnknown_shouldRenderCorrectly() {
        // Arrange
        val resource = RESOURCE_ERROR_UNKNOWN

        // Act
        setContentResourceWithLoadingView(resource)

        // Assert
        assertCompose {
            assertErrorNoInternetShouldBeDisplayed(false)
            assertErrorUnknownShouldBeDisplayed(true)
            assertLoadingShouldBeDisplayed(false)
            assertSuccessShouldBeDisplayed(false)
        }
    }

    @Test
    fun resourceWithLoadingView_loading_shouldRenderCorrectly() {
        // Arrange
        val resource = RESOURCE_LOADING

        // Act
        setContentResourceWithLoadingView(resource)

        // Assert
        assertCompose {
            assertErrorNoInternetShouldBeDisplayed(false)
            assertErrorUnknownShouldBeDisplayed(false)
            assertLoadingShouldBeDisplayed(true)
            assertSuccessShouldBeDisplayed(false)
        }
    }

    @Test
    fun resourceWithLoadingView_success_shouldRenderCorrectly() {
        // Arrange
        val resource = RESOURCE_SUCCESS

        // Act
        setContentResourceWithLoadingView(resource)

        // Assert
        assertCompose {
            assertErrorNoInternetShouldBeDisplayed(false)
            assertErrorUnknownShouldBeDisplayed(false)
            assertLoadingShouldBeDisplayed(false)
            assertSuccessShouldBeDisplayed(true)
        }
    }

    @Test
    fun resourceWithoutLoadingView_errorNoInternet_shouldRenderCorrectly() {
        // Arrange
        val resource = RESOURCE_ERROR_NO_INTERNET

        // Act
        setContentResourceWithoutLoadingView(resource)

        // Assert
        assertCompose {
            assertErrorNoInternetShouldBeDisplayed(true)
            assertErrorUnknownShouldBeDisplayed(false)
            assertLoadingShouldBeDisplayed(false)
            assertSuccessShouldBeDisplayed(false)
        }
    }

    @Test
    fun resourceWithoutLoadingView_errorUnknown_shouldRenderCorrectly() {
        // Arrange
        val resource = RESOURCE_ERROR_UNKNOWN

        // Act
        setContentResourceWithoutLoadingView(resource)

        // Assert
        assertCompose {
            assertErrorNoInternetShouldBeDisplayed(false)
            assertErrorUnknownShouldBeDisplayed(true)
            assertLoadingShouldBeDisplayed(false)
            assertSuccessShouldBeDisplayed(false)
        }
    }

    @Test
    fun resourceWithoutLoadingView_loading_shouldRenderCorrectly() {
        // Arrange
        val resource = RESOURCE_LOADING

        // Act
        setContentResourceWithoutLoadingView(resource)

        // Assert
        assertCompose {
            assertErrorNoInternetShouldBeDisplayed(false)
            assertErrorUnknownShouldBeDisplayed(false)
            assertLoadingShouldBeDisplayed(true)
            assertSuccessShouldBeDisplayed(false)
        }
    }

    @Test
    fun resourceWithoutLoadingView_success_shouldRenderCorrectly() {
        // Arrange
        val resource = RESOURCE_SUCCESS

        // Act
        setContentResourceWithoutLoadingView(resource)

        // Assert
        assertCompose {
            assertErrorNoInternetShouldBeDisplayed(false)
            assertErrorUnknownShouldBeDisplayed(false)
            assertLoadingShouldBeDisplayed(false)
            assertSuccessShouldBeDisplayed(true)
        }
    }

    private fun setContentResourceWithLoadingView(
        resource: NBResource<String>
    ) {
        setContent {
            NBResourceWithLoadingView(
                resource = resource,
                errorView = { type ->
                    ErrorView(type)
                },
                loadingView = {
                    LoadingView()
                },
                successView = { text ->
                    SuccessView(text)
                }
            )
        }
    }

    private fun setContentResourceWithoutLoadingView(
        resource: NBResource<String>
    ) {
        setContent {
            NBResourceWithoutLoadingView(
                resource = resource,
                errorView = { type ->
                    ErrorView(type)
                },
                loadingView = {
                    LoadingView()
                },
                successView = { text ->
                    SuccessView(text)
                }
            )
        }
    }

    @Composable
    private fun ErrorView(type: NBErrorType) {
        val text = when (type) {
            NBErrorType.NO_INTERNET -> TEXT_ERROR_NO_INTERNET
            NBErrorType.UNKNOWN -> TEXT_ERROR_UNKNOWN
        }
        Text(text)
    }

    @Composable
    private fun LoadingView() {
        Text(TEXT_LOADING)
    }

    @Composable
    private fun SuccessView(text: String) {
        Text(text)
    }

    private fun ComposeContentTestRule.assertErrorNoInternetShouldBeDisplayed(expectedIsDisplayed: Boolean) {
        assertResourceShouldBeDisplayed(TEXT_ERROR_NO_INTERNET, expectedIsDisplayed)
    }

    private fun ComposeContentTestRule.assertErrorUnknownShouldBeDisplayed(expectedIsDisplayed: Boolean) {
        assertResourceShouldBeDisplayed(TEXT_ERROR_UNKNOWN, expectedIsDisplayed)
    }

    private fun ComposeContentTestRule.assertLoadingShouldBeDisplayed(expectedIsDisplayed: Boolean) {
        assertResourceShouldBeDisplayed(TEXT_LOADING, expectedIsDisplayed)
    }

    private fun ComposeContentTestRule.assertSuccessShouldBeDisplayed(expectedIsDisplayed: Boolean) {
        assertResourceShouldBeDisplayed(TEXT_SUCCESS, expectedIsDisplayed)
    }

    private fun ComposeContentTestRule.assertResourceShouldBeDisplayed(
        text: String,
        expectedIsDisplayed: Boolean
    ) {
        if (expectedIsDisplayed) {
            onNodeWithText(text)
                .assertIsDisplayed()
        } else {
            assertStringIsNotDisplayed(text)
        }
    }

}