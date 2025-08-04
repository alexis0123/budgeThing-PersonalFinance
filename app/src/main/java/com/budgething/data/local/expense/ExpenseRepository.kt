package com.budgething.data.local.expense

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class ExpenseRepository(private val dao: ExpenseDao) {

    suspend fun add(expense: Expense) {
        dao.addExpense(expense)
    }

    suspend fun update(expense: Expense) {
        dao.updateExpense(expense)
    }

    suspend fun getAll(): List<Expense> = dao.getAllExpenses()

    fun getBetween(start: LocalDate, end: LocalDate): Flow<List<Expense>> = dao.getExpensesBetween(start, end)

    fun getRecent(): Flow<List<Expense>> = dao.getLastExpenses()

    suspend fun deleteExpense(expense: Expense) = dao.deleteExpense(expense)

}