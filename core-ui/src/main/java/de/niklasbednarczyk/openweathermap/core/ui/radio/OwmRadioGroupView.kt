package de.niklasbednarczyk.openweathermap.core.ui.radio

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ListItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.theme.listContentPaddingValues

@Composable
fun <T> OwmRadioGroupView(
    radioGroup: OwmRadioGroupModel<T>?,
    onItemSelected: (T) -> Unit,
    sortItemsAlphabetically: Boolean = false
) {
    if (radioGroup != null) {
        val options = if (sortItemsAlphabetically) {
            val context = LocalContext.current
            radioGroup.options.sortedBy { option -> option.text.asString(context) }
        } else {
            radioGroup.options
        }

        LazyColumn(
            modifier = Modifier.selectableGroup(),
            contentPadding = listContentPaddingValues
        ) {
            items(options) { option ->
                val selected = option.key == radioGroup.selectedKey
                ListItem(
                    modifier = Modifier.selectable(
                        selected = selected,
                        onClick = { onItemSelected(option.key) },
                        role = Role.RadioButton
                    ),
                    headlineText = {
                        Text(option.text.asString())
                    },
                    leadingContent = {
                        RadioButton(
                            selected = selected,
                            onClick = null
                        )
                    }
                )
            }
        }
    }

}