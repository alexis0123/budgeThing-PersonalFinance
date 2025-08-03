package com.budgething

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.budgething.data.local.AppDatabase
import com.budgething.data.local.expense.ExpenseRepository
import com.budgething.data.local.expense.category.CategoryRepository
import com.budgething.data.local.expense.item.ItemRepository
import com.budgething.ui.theme.BudgeThingTheme
import com.budgething.ui.screen.mainscreen.MainScreen
import com.budgething.ui.screen.mainscreen.pages.expense.NumKeyViewModel
import com.budgething.ui.screen.mainscreen.pages.expense.dialog.ConfirmExpenseViewModel
import com.budgething.ui.screen.mainscreen.pages.viewmodel.ExpenseViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = AppDatabase.getDatabase(applicationContext)

        val categoryRepo = CategoryRepository(db.categoryDao())
        val itemRepo = ItemRepository(db.itemDao())
        val expenseRepo = ExpenseRepository(db.expenseDao())

        val numKeyViewModel = NumKeyViewModel()
        val confirmExpenseViewModel = ConfirmExpenseViewModel(categoryRepo, itemRepo)
        val expenseViewModel = ExpenseViewModel(expenseRepo)

        setContent {
            BudgeThingTheme(
                dynamicColor = false
            ) {
                MainScreen(
                    numKeyViewModel,
                    confirmExpenseViewModel,
                    expenseViewModel
                )
            }
        }
    }
}