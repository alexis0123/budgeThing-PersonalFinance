package com.budgething.ui.screen.mainscreen.pages.expense.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import com.budgething.data.local.expense.item.Item
import androidx.compose.foundation.lazy.items

@Composable
fun SearchableDropDown(
    query: String,
    filteredOptions: List<Item>,
    onQueryChange: (String) -> Unit,
    onItemSelected: (Item) -> Unit
) {
    var showFilter by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Box(contentAlignment = Alignment.TopCenter) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                onQueryChange(it)
                showFilter = it.isNotEmpty()
            },
            singleLine = true,
            placeholder = { Text("Expense name", color = MaterialTheme.colorScheme.onSecondary) },
            modifier = Modifier.fillMaxWidth()
        )

        if (showFilter) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 200.dp)
                    .padding(top = 55.dp)
            ) {
                items(filteredOptions) { item ->
                    Box(
                        modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)
                    ) {
                        Text(
                            text = item.name,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onItemSelected(item)
                                    showFilter = false
                                    focusManager.clearFocus()
                                }
                                .padding(12.dp)
                        )
                    }
                }
            }
        }
    }
}