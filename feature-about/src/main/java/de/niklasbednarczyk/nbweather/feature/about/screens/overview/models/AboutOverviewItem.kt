package de.niklasbednarczyk.nbweather.feature.about.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.intent.createUrlIntent
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.image.NBImageItem
import de.niklasbednarczyk.nbweather.core.ui.image.NBImages
import de.niklasbednarczyk.nbweather.feature.about.constants.AboutConstants

sealed interface AboutOverviewItem {

    data object Divider : AboutOverviewItem
    
    data class Credit(
        val banner: NBImageItem? = null,
        val text: NBString,
        val buttons: List<AboutOverviewButtonModel>
    ) : AboutOverviewItem
    
    companion object {

        private fun getGitHubButton(
            url: String
        ): AboutOverviewButtonModel {
            return AboutOverviewButtonModel(
                icon = NBIcons.GitHub,
                label = NBString.ResString(R.string.screen_about_overview_button_git_hub),
                intent = createUrlIntent(url)
            )
        }

        private fun getWebsiteButton(
            url: String
        ): AboutOverviewButtonModel {
            return AboutOverviewButtonModel(
                icon = NBIcons.Website,
                label = NBString.ResString(R.string.screen_about_overview_button_website),
                intent = createUrlIntent(url)
            )
        }

        fun from(): List<AboutOverviewItem> {
            val items = mutableListOf<AboutOverviewItem>()

            items.add(
                Credit(
                    banner = NBImages.OpenWeather,
                    text = NBString.ResString(R.string.screen_about_overview_text_open_weather),
                    buttons = listOf(
                        getWebsiteButton(AboutConstants.OpenWeather.WEBSITE)
                    )
                )
            )

            items.add(Divider)

            items.add(
                Credit(
                    text = NBString.ResString(R.string.screen_about_overview_text_erik_flowers),
                    buttons = listOf(
                        getWebsiteButton(AboutConstants.ErikFlowers.WEBSITE),
                        getGitHubButton(AboutConstants.ErikFlowers.GIT_HUB)
                    )
                )
            )

            items.add(Divider)

            items.add(
                Credit(
                    text = NBString.ResString(R.string.screen_about_overview_text_flagpedia_net),
                    buttons = listOf(
                        getWebsiteButton(AboutConstants.FlagpediaNet.WEBSITE),
                    )
                )
            )

            items.add(Divider)

            items.add(
                Credit(
                    text = NBString.ResString(R.string.screen_about_overview_text_niklas_bednarczyk),
                    buttons = listOf(
                        getGitHubButton(AboutConstants.NiklasBednarczyk.GIT_HUB),
                    )
                )
            )

            return items
        }

    }

}