package com.budgething.ui.screen.mainscreen.pages.expense

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Key(label: String, onClick: () -> Unit) {

    val elevatedColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
    val size = 70.dp

    Button(
        onClick = onClick,
        modifier = Modifier.width(size).height(size),
        colors = ButtonDefaults.buttonColors(
            containerColor = elevatedColor
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 5.dp,
            pressedElevation = 15.dp
        )
    ) {
        Text(
            label, fontSize = 17.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun Confirm(label: String, enabled: Boolean, onClick: () -> Unit) {

    val elevatedColor = MaterialTheme.colorScheme.surfaceColorAtElevation(30.dp)
    val size = 70.dp

    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = elevatedColor
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 5.dp,
            pressedElevation = 15.dp
        ),
        modifier = Modifier.width(size).height(size)
    ) {
        Text(
            label, fontSize = 30.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }

}