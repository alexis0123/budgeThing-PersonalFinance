package com.budgething.ui.screen.mainscreen.pages.expense

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.budgething.ui.screen.mainscreen.pages.viewmodel.ExpenseViewModel
import com.budgething.ui.theme.BudgeThingTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.budgething.ui.screen.mainscreen.pages.expense.top.FormattedAmountDisplay
import androidx.lifecycle.viewmodel.compose.viewModel
import com.budgething.ui.screen.mainscreen.pages.expense.dialog.ConfirmExpenseDialog
import com.budgething.ui.screen.mainscreen.pages.expense.dialog.ConfirmExpenseViewModel

@Composable
fun ExpensePage(
    numKeyViewModel: NumKeyViewModel,
    confirmExpenseViewModel: ConfirmExpenseViewModel,
    expenseViewModel: ExpenseViewModel
) {

    val amount by numKeyViewModel.amount.collectAsState()
    var confirmedAmount by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    ConfirmExpenseDialog(
        showDialog = showDialog,
        amount = confirmedAmount,
        dismiss = {
            showDialog = false
            confirmExpenseViewModel.clearAllState()
        },
        confirm = {
            showDialog = false
        },
        viewModel = confirmExpenseViewModel,
        expenseViewModel = expenseViewModel
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .padding(vertical = 60.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            FormattedAmountDisplay(amount, 50, MaterialTheme.colorScheme.onBackground)
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
                    showDialog = true
                },
                numKeyViewModel
            )
        }
    }
}