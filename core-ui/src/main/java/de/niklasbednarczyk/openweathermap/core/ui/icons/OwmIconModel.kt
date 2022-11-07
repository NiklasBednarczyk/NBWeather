package de.niklasbednarczyk.openweathermap.core.ui.icons

import androidx.annotation.DrawableRes
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString

sealed interface OwmIconModel {

    val contentDescription: OwmString

    interface Default : OwmIconModel {

        @get:DrawableRes
        val resId: Int

    }

    interface FilledAndOutlined : OwmIconModel {

        @get:DrawableRes
        val resIdFilled: Int

        @get:DrawableRes
        val resIdOutlined: Int

    }


}