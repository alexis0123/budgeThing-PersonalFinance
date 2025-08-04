package com.budgething.ui.screen.mainscreen.pages.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budgething.data.local.expense.Expense
import com.budgething.data.local.expense.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate

class ExpenseViewModel(
    private val repo: ExpenseRepository
): ViewModel() {

    private val startOfWeek = LocalDate.now().with(DayOfWeek.MONDAY)
    private val endOfWeek = LocalDate.now().with(DayOfWeek.SUNDAY)

    val recentExpenses: StateFlow<List<Expense>> = repo.getRecent().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    val expensesThisWeek: Flow<List<Expense>> = repo.getBetween(startOfWeek, endOfWeek)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            repo.add(expense)
        }
    }

    fun updateExpense(expense: Expense) {
        viewModelScope.launch {
            repo.update(expense)
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            repo.deleteExpense(expense)
        }
    }

}