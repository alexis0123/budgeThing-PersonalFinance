package com.budgething.ui.screen.mainscreen.pages.expense

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier

@Composable
fun KeyPad(
    onConfirm: (amount: String) -> Unit,
    viewModel: NumKeyViewModel
) {

    val amount = viewModel.amount.collectAsState()

    val keypad = listOf(
        listOf("7", "8", "9", "⌫"),
        listOf("4", "5", "6", "C"),
        listOf("1", "2", "3", "→"),
        listOf("00", "0", ".", "✔")
    )



    keypad.forEach { row ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            row.forEach { label ->
                when(label) {
                    "0" -> Key(label) { viewModel.addZero() }
                    "00" -> Key(label) { viewModel.addTwoZeros() }
                    "." -> Key(label) { viewModel.addDot() }
                    "⌫" -> Key(label) { viewModel.deleteLast() }
                    "C" -> Key(label) { viewModel.deleteAll() }
                    "→" -> Confirm("→", amount.value.isNotEmpty() ) {}
                    "✔" -> Confirm(
                        "✔",
                        amount.value.isNotEmpty() && amount.value.toDouble() >= 1
                    ) {
                        onConfirm(amount.value)
                        viewModel.deleteAll()
                    }
                    else -> Key(label) { viewModel.addDigit(label) }
                }
            }
        }
    }
}