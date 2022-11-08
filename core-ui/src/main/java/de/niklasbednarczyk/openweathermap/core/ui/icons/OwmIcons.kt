package de.niklasbednarczyk.openweathermap.core.ui.icons

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R

object OwmIcons {

    object Back : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_arrow_back_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_back)
    }

    object Cancel : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_close_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_cancel)
    }

    object ColorScheme : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_palette_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_color_scheme)
    }

    object Daily : OwmIconModel.FilledAndOutlined {
        override val resIdFilled: Int
            get() = R.drawable.ic_baseline_date_range_24
        override val resIdOutlined: Int
            get() = R.drawable.ic_outline_date_range_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_daily)
    }

    object Delete : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_delete_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_delete)
    }

    object Drawer : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_menu_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_drawer)
    }

    object ErrorNoInternet : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_cloud_off_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_error_no_internet)
    }

    object ErrorUnknown : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_error_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_error_unknown)
    }

    object Expand : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_expand_more_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_expand)
    }

    object FindLocation : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_my_location_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_find_location)
    }

    object Hourly : OwmIconModel.FilledAndOutlined {
        override val resIdFilled: Int
            get() = R.drawable.ic_baseline_watch_later_24
        override val resIdOutlined: Int
            get() = R.drawable.ic_outline_watch_later_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_hourly)
    }

    object Language : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_translate_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_language)
    }

    object Location : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_place_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_location)
    }

    object Search : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_search_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_search)
    }

    object Settings : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_settings_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_settings)
    }

    object Theme : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_dark_mode_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_theme)
    }

    object Today : OwmIconModel.FilledAndOutlined {
        override val resIdFilled: Int
            get() = R.drawable.ic_baseline_today_24
        override val resIdOutlined: Int
            get() = R.drawable.ic_outline_today_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_today)
    }

    object Units : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_thermostat_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_units)
    }

    object Warning : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_warning_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_warning)
    }

}