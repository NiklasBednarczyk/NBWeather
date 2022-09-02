package de.niklasbednarczyk.openweathermap.feature.location.ui.screens.location

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LocationScreen(
    viewModel: LocationViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsState()

    //TODO (#9) Do right design

    val scrollState = rememberScrollState()
    Text(
        text = uiState.value.oneCall.toString(),
        modifier = Modifier.verticalScroll(scrollState)
    )

}