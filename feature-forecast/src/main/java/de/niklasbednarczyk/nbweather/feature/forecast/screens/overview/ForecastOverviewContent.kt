package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithLoadingView

@Composable
fun ForecastOverviewContent(
    uiState: ForecastOverviewUiState
) {
    NBResourceWithLoadingView(uiState.itemsResource) { items ->
        LazyColumn {
            items(items) { item ->
                Text(text = item)
            }
        }
    }
}