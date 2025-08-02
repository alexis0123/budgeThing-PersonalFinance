package com.budgething.ui.screen.mainscreen.pages.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budgething.data.local.expense.Expense
import com.budgething.data.local.expense.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ExpenseViewModel(
    private val repo: ExpenseRepository
): ViewModel() {

    val recentExpenses: Flow<List<Expense>> = repo.getRecent().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

}