package de.niklasbednarczyk.openweathermap.core.ui.resource

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmErrorType

interface OwmResourceUiState {

    val errorType: OwmErrorType?

}