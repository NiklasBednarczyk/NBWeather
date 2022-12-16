package de.niklasbednarczyk.nbweather.feature.location.screens.daily

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.HorizontalPager
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerView
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.core.ui.scaffold.NBScaffold
import de.niklasbednarczyk.nbweather.core.ui.scaffold.topappbar.NBTopAppBar
import de.niklasbednarczyk.nbweather.feature.location.cards.views.LocationCardsView

@Composable
fun LocationDailyScreen(
    viewModel: LocationDailyViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    NBResourceWithoutLoadingView(uiState.value.viewDataResource) { viewData ->
        NBPagerView(viewData) { count, pagerState, item ->
            NBScaffold(
                topBar = { scrollBehavior ->
                    NBTopAppBar(
                        scrollBehavior = scrollBehavior,
                        navigationIcon = navigationIcon,
                        title = item.title
                    )
                }
            ) {
                HorizontalPager(
                    count = count,
                    state = pagerState
                ) {
                    LocationCardsView(
                        cardItems = item.cardItems,
                        navigateToAlerts = null
                    )
                }
            }
        }
    }
}