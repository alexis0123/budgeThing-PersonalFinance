package com.budgething.ui.screen.mainscreen.pages.expense.top

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ColumnScope.TopScreen(
    onClick: () -> Unit,
    amount: String
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
        contentAlignment = Alignment.TopStart
    ) {
        Button(
            onClick = onClick
        ) {
            Text("Recent")
        }
    }
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomEnd
    ) {
        FormattedAmountDisplay(amount, 50, MaterialTheme.colorScheme.onBackground)
    }
}