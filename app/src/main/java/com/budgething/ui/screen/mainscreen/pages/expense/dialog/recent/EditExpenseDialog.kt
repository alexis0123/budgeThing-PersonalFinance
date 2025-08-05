package com.budgething.ui.screen.mainscreen.pages.expense.dialog.recent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.budgething.data.local.expense.Expense
import com.budgething.ui.screen.mainscreen.pages.viewmodel.ExpenseViewModel
import java.util.Locale
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.surfaceColorAtElevation

@Composable
fun EditExpenseDialog(
    showDialog: Boolean,
    dismiss: () -> Unit,
    expense: Expense,
    expenseViewModel: ExpenseViewModel
) {

    val elevatedColor = MaterialTheme.colorScheme.surfaceColorAtElevation(200.dp)

    if (showDialog) {
        Dialog(onDismissRequest = {}) {
            Box(
                modifier = Modifier
                    .width(350.dp)
                    .height(400.dp)
                    .padding(10.dp)
                    .background(
                        color = elevatedColor,
                        shape = RoundedCornerShape(30.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 35.dp)
                        .padding(bottom = 25.dp)
                        .padding(horizontal = 30.dp),
                    verticalArrangement = Arrangement.spacedBy(1.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        contentAlignment = Alignment.TopEnd,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier
                                .clickable(onClick = {
                                    expenseViewModel.deleteExpense(expense)
                                    dismiss()
                                })
                        )
                    }

                    Text(
                        text = String.format(Locale.US, "\u20B1%,.2f", expense.amount),
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Row {
                        Button(
                            onClick = dismiss
                        ) { Text("Cancel") }
                    }
                }
            }
        }
    }
}