package de.niklasbednarczyk.nbweather.core.ui.card

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.theme.dimens.cardPadding
import de.niklasbednarczyk.nbweather.core.ui.theme.dimens.screenHorizontalPadding

@Composable
fun <T : NBCardItem> NBCard(
    item: T,
    content: @Composable (item: T) -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = screenHorizontalPadding)
    ) {
        Column(modifier = Modifier.padding(cardPadding)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = item.cardTitle.asString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(cardPadding))
            content(item)
        }

    }

}