package de.niklasbednarczyk.openweathermap.feature.location.screens.daily

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.HorizontalPager
import de.niklasbednarczyk.openweathermap.core.ui.pager.OwmPagerView
import de.niklasbednarczyk.openweathermap.core.ui.resource.OwmResourceWithoutLoadingView
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmScaffold
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.topappbar.OwmTopAppBar
import de.niklasbednarczyk.openweathermap.feature.location.cards.views.LocationCardsView

@Composable
fun LocationDailyScreen(
    viewModel: LocationDailyViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    OwmResourceWithoutLoadingView(uiState.value.viewDataResource) { viewData ->
        OwmPagerView(viewData) { count, pagerState, item ->
            OwmScaffold(
                topBar = { scrollBehavior ->
                    OwmTopAppBar(
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