package com.budgething.ui.screen.mainscreen.pages.expense

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.budgething.ui.screen.mainscreen.pages.viewmodel.ExpenseViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.budgething.ui.screen.mainscreen.pages.expense.dialog.ConfirmExpenseDialog
import com.budgething.ui.screen.mainscreen.pages.expense.dialog.ConfirmExpenseViewModel
import com.budgething.ui.screen.mainscreen.pages.expense.dialog.recent.RecentDialog
import com.budgething.ui.screen.mainscreen.pages.expense.top.TopScreen

@Composable
fun ExpensePage(
    numKeyViewModel: NumKeyViewModel,
    confirmExpenseViewModel: ConfirmExpenseViewModel,
    expenseViewModel: ExpenseViewModel
) {

    val amount by numKeyViewModel.amount.collectAsState()
    var confirmedAmount by remember { mutableStateOf("") }
    var showConfirm by remember { mutableStateOf(false) }
    var showRecent by remember { mutableStateOf(false) }

    ConfirmExpenseDialog(
        showDialog = showConfirm,
        amount = confirmedAmount,
        dismiss = {
            showConfirm = false
            confirmExpenseViewModel.clearAllState()
        },
        confirm = {
            showConfirm = false
        },
        viewModel = confirmExpenseViewModel,
        expenseViewModel = expenseViewModel
    )

    RecentDialog(
        showDialog = showRecent,
        dismiss = { showRecent = false },
        viewModel = expenseViewModel
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .padding(bottom = 60.dp)
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            TopScreen(
                { showRecent = true },
                amount
            )
        }

        Column(
            modifier = Modifier
                .weight(1.2f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            HorizontalDivider(modifier = Modifier.padding(10.dp))
            KeyPad(
                {
                    confirmedAmount = amount
                    showConfirm = true
                },
                numKeyViewModel
            )
        }
    }
}