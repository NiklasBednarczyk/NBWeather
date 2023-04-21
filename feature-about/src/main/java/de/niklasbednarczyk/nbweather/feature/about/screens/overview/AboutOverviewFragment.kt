package de.niklasbednarczyk.nbweather.feature.about.screens.overview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.fragment.NBFragmentUiState
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcon
import de.niklasbednarczyk.nbweather.core.ui.image.NBImage
import de.niklasbednarczyk.nbweather.core.ui.image.NBImageModel
import de.niklasbednarczyk.nbweather.core.ui.snackbar.NBSnackbarModel
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.theme.dimens.*
import de.niklasbednarczyk.nbweather.feature.about.screens.overview.models.AboutOverviewButtonModel
import de.niklasbednarczyk.nbweather.feature.about.screens.overview.models.AboutOverviewItem

@AndroidEntryPoint
class AboutOverviewFragment : NBFragmentUiState<AboutOverviewUiState>() {

    companion object {
        private val horizontalPaddingValues = PaddingValues(
            horizontal = screenHorizontalPadding
        )
    }

    override val viewModel: AboutOverviewViewModel by viewModels()

    override fun createTopAppBarItem(viewData: AboutOverviewUiState): NBTopAppBarItem {
        return NBTopAppBarItem.Material.Small(
            title = NBString.Resource(R.string.screen_about_overview_title)
        )
    }

    @Composable
    override fun ScaffoldContent(viewData: AboutOverviewUiState) {
        LazyColumn(
            verticalArrangement = columnVerticalArrangementDefault
        ) {
            items(viewData.items) { item ->
                when (item) {
                    AboutOverviewItem.Divider -> {
                        Divider(
                            modifier = Modifier.padding(dividerPaddingVertical)
                        )
                    }
                    is AboutOverviewItem.WithBanner -> {
                        ItemColumn {
                            ItemBanner(
                                banner = item.banner
                            )
                            ItemText(
                                text = item.text
                            )
                            ItemButtons(
                                buttons = item.buttons
                            )
                        }
                    }
                    is AboutOverviewItem.WithoutBanner -> {
                        ItemColumn {
                            ItemText(
                                text = item.text
                            )
                            ItemButtons(
                                buttons = item.buttons
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun ItemColumn(
        content: @Composable ColumnScope.() -> Unit
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = columnVerticalArrangementDefault,
            content = content
        )
    }

    @Composable
    private fun ItemBanner(
        banner: NBImageModel
    ) {
        NBImage(
            modifier = Modifier.fillMaxWidth(),
            image = banner
        )
    }

    @Composable
    private fun ItemText(
        text: NBString?
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontalPaddingValues),
            text = text.asString(),
            style = MaterialTheme.typography.bodyLarge
        )
    }

    @Composable
    private fun ItemButtons(
        buttons: List<AboutOverviewButtonModel>
    ) {
        val context = LocalContext.current

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontalPaddingValues),
            horizontalArrangement = rowHorizontalArrangement
        ) {
            buttons.forEach { button ->
                FilledTonalButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        val intent = button.intent
                        if (intent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(button.intent)
                        } else {
                            onIntentFailed()
                        }
                    },
                ) {
                    NBIcon(
                        modifier = Modifier.size(filledTonalButtonIconSize),
                        icon = button.icon
                    )
                    Spacer(
                        modifier = Modifier.width(filledTonalButtonPaddingBetweenElements)
                    )
                    Text(
                        text = button.label.asString()
                    )
                }
            }
        }
    }

    private fun onIntentFailed() {
        val snackbar = NBSnackbarModel(
            message = NBString.Resource(R.string.snackbar_about_intent_failed_message)
        )
        sendSnackbar(snackbar)
    }


}