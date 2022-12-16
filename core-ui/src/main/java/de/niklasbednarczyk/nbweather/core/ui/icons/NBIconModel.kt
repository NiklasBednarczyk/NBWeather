package de.niklasbednarczyk.nbweather.core.ui.icons

import androidx.annotation.DrawableRes
import de.niklasbednarczyk.nbweather.core.common.string.NBString

sealed interface NBIconModel {

    val contentDescription: NBString

    interface Default : NBIconModel {

        @get:DrawableRes
        val resId: Int

    }

    interface FilledAndOutlined : NBIconModel {

        @get:DrawableRes
        val resIdFilled: Int

        @get:DrawableRes
        val resIdOutlined: Int

    }


}