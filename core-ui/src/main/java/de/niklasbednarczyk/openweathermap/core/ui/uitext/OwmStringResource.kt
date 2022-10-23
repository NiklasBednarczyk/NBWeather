package de.niklasbednarczyk.openweathermap.core.ui.uitext

import androidx.annotation.StringRes

class OwmStringResource(
    @StringRes val resId: Int,
    vararg val args: Any
) : OwmUiText
