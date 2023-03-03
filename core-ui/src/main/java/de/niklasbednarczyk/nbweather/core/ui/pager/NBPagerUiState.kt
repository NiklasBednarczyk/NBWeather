package de.niklasbednarczyk.nbweather.core.ui.pager

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource

interface NBPagerUiState<T: NBPagerViewData<*, *>> {

    val viewDataResource: NBResource<T>?

}