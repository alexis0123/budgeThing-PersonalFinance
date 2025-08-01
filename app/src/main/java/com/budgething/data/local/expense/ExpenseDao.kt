package com.budgething.data.local.expense

import androidx.annotation.RequiresPermission
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.time.LocalDate

@Dao
interface ExpenseDao {

    @Insert
    suspend fun addExpense(expense: Expense)

    @Update
    suspend fun updateExpense(expense: Expense)

    @Query("SELECT * FROM expense")
    suspend fun getAllExpenses(): List<Expense>

    @Query("SELECT * FROM expense WHERE date BETWEEN :start AND :end ORDER BY date ASC")
    suspend fun getExpenseBetween(start: LocalDate, end: LocalDate)

}