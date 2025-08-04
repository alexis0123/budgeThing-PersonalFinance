package com.budgething.data.local.expense

import androidx.annotation.RequiresPermission
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface ExpenseDao {

    @Insert
    suspend fun addExpense(expense: Expense)

    @Update
    suspend fun updateExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query("SELECT * FROM expense")
    suspend fun getAllExpenses(): List<Expense>

    @Query("SELECT * FROM expense WHERE date BETWEEN :start AND :end ORDER BY date ASC")
    fun getExpensesBetween(start: LocalDate, end: LocalDate): Flow<List<Expense>>

    @Query("SELECT * FROM expense ORDER BY date DESC LIMIT 20")
    fun getLastExpenses(): Flow<List<Expense>>

}