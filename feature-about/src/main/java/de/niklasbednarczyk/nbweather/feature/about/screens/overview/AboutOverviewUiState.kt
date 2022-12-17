package de.niklasbednarczyk.nbweather.feature.about.screens.overview

import android.content.Intent
import android.net.Uri
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.image.NBImages
import de.niklasbednarczyk.nbweather.feature.about.constants.AboutConstants
import de.niklasbednarczyk.nbweather.feature.about.screens.overview.models.AboutOverviewButtonModel
import de.niklasbednarczyk.nbweather.feature.about.screens.overview.models.AboutOverviewItem

class AboutOverviewUiState {

    companion object {

        private val buttonEmailIcon = NBIcons.Email
        private val buttonEmailLabel =
            NBString.Resource(R.string.screen_about_overview_button_email)

    }

    val items: List<AboutOverviewItem>
        get() {
            val items = mutableListOf<AboutOverviewItem>()

            items.add(
                AboutOverviewItem.WithBanner(
                    banner = NBImages.OpenWeatherLogo,
                    text = NBString.Resource(R.string.screen_about_overview_text_open_weather),
                    buttons = listOf(
                        getWebsiteButton(AboutConstants.OpenWeather.WEBSITE)
                    )
                )
            )

            items.add(
                AboutOverviewItem.Divider
            )

            items.add(
                AboutOverviewItem.WithoutBanner(
                    text = NBString.Resource(R.string.screen_about_overview_text_erik_flowers),
                    buttons = listOf(
                        getWebsiteButton(AboutConstants.ErikFlowers.WEBSITE),
                        getGitHubButton(AboutConstants.ErikFlowers.GIT_HUB)
                    )
                )
            )

            items.add(
                AboutOverviewItem.Divider
            )

            items.add(
                AboutOverviewItem.WithoutBanner(
                    text = NBString.Resource(R.string.screen_about_overview_text_niklas_bednarczyk),
                    buttons = listOf(
                        getGitHubButton(AboutConstants.NiklasBednarczyk.GIT_HUB),
                        getEmailButton(
                            emailAddress = AboutConstants.NiklasBednarczyk.EMAIL_ADDRESS,
                            emailSubject = AboutConstants.NiklasBednarczyk.EMAIL_SUBJECT
                        )
                    )
                )
            )

            return items
        }

    private fun getUrlIntent(
        url: String
    ): Intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
        addCategory(Intent.CATEGORY_DEFAULT)
    }

    private fun getEmailButton(
        emailAddress: String,
        emailSubject: String
    ): AboutOverviewButtonModel {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
            putExtra(Intent.EXTRA_SUBJECT, emailSubject)
            addCategory(Intent.CATEGORY_DEFAULT)
        }
        return AboutOverviewButtonModel(
            icon = NBIcons.Email,
            label = NBString.Resource(R.string.screen_about_overview_button_email),
            intent = intent
        )
    }

    private fun getGitHubButton(
        url: String
    ): AboutOverviewButtonModel {
        return AboutOverviewButtonModel(
            icon = NBIcons.GitHub,
            label = NBString.Resource(R.string.screen_about_overview_button_git_hub),
            intent = getUrlIntent(url)
        )
    }

    private fun getWebsiteButton(
        url: String
    ): AboutOverviewButtonModel {
        return AboutOverviewButtonModel(
            icon = NBIcons.Website,
            label = NBString.Resource(R.string.screen_about_overview_button_website),
            intent = getUrlIntent(url)
        )
    }

}