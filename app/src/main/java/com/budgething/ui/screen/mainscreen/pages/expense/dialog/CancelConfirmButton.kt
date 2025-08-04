import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.budgething.data.local.expense.Expense
import com.budgething.data.local.expense.item.Item
import com.budgething.ui.screen.mainscreen.pages.expense.dialog.ConfirmExpenseViewModel
import com.budgething.ui.screen.mainscreen.pages.viewmodel.ExpenseViewModel
import java.time.LocalDate

@Composable
fun CancelRecordButton(
    dismiss: () -> Unit,
    mainCategory: String,
    subCategory: String,
    name: String,
    amount: Double,
    filteredOption: List<Item>,
    viewModel: ExpenseViewModel,
    confirmExpenseViewModel: ConfirmExpenseViewModel
) {

    val today = LocalDate.now()

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = dismiss,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 5.dp,
                pressedElevation = 15.dp
            ),
            modifier = Modifier
                .height(50.dp)
        ) { Text("Cancel", color = MaterialTheme.colorScheme.onSecondary) }

        Button(
            enabled = mainCategory.isNotEmpty() && subCategory.isNotEmpty() && name.isNotEmpty(),
            onClick = {
                viewModel.addExpense(Expense(
                    date = today,
                    mainCategory = mainCategory,
                    subCategory = subCategory,
                    name = name,
                    amount = amount
                ))
                if (name !in filteredOption.map { it.name }) {
                    confirmExpenseViewModel.addNewItem(Item(
                        name = name,
                        mainCategory = mainCategory,
                        subCategory = subCategory
                    ))
                }
                dismiss()
            },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 5.dp,
                pressedElevation = 15.dp
            ),
            modifier = Modifier
                .height(50.dp)
        ) { Text("Record", color = MaterialTheme.colorScheme.onPrimary) }

    }
}