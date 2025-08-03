package com.budgething.ui.screen.mainscreen.pages.expense.top

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun FormattedAmountDisplay(
    amount: String,
    fSize: Int,
    color: Color,
    format: String = ""
) {
    val formattedAmount = amount.toLongOrNull()?.let {
        if (format.isNotBlank()) {
            "$format %,d".format(it)
        } else {
            "%,d".format(it)
        }
    } ?: amount

    Text(
        text = formattedAmount,
        fontSize = fSize.sp,
        color = color
    )
}