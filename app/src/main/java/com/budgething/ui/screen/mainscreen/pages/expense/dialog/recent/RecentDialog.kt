package com.budgething.ui.screen.mainscreen.pages.expense.dialog.recent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.window.Dialog
import com.budgething.ui.screen.mainscreen.pages.viewmodel.ExpenseViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import com.budgething.data.local.expense.Expense

@Composable
fun RecentDialog(
    showDialog: Boolean,
    dismiss: () -> Unit,
    viewModel: ExpenseViewModel
) {

    val elevatedColor = MaterialTheme.colorScheme.surfaceColorAtElevation(200.dp)

    var showEditDialog by remember { mutableStateOf(false) }
    val recentExpenses by viewModel.recentExpenses.collectAsState()
    var selectedExpense: Expense? by remember { mutableStateOf(null) }

    if (showEditDialog && selectedExpense != null) {
        EditExpenseDialog(
            showDialog = showEditDialog,
            dismiss = { showEditDialog = false },
            expense = selectedExpense!!,
            expenseViewModel = viewModel
        )
    }

    if (showDialog) {
        Dialog(onDismissRequest = dismiss) {
            Box(
                modifier = Modifier
                    .width(350.dp)
                    .height(400.dp)
                    .padding(30.dp)
                    .background(color = elevatedColor, shape = RoundedCornerShape(30.dp))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    items(recentExpenses) { expense ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .height(60.dp)
                                .clickable(onClick = {
                                    selectedExpense = expense
                                    showEditDialog = true
                                }
                                )
                        ) {
                            Column {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(
                                        text = "₱ ${expense.amount}",
                                        modifier = Modifier.weight(0.5f).fillMaxHeight(),
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = expense.name,
                                        modifier = Modifier.weight(1f).fillMaxHeight()
                                    )
                                }
                                HorizontalDivider()
                            }
                        }
                    }
                }
            }
        }
    }
}