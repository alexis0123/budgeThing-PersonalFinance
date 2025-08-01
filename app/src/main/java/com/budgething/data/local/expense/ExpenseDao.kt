package com.budgething.data.local.expense

import androidx.annotation.RequiresPermission
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExpenseDao {

    @Insert
    suspend fun addExpense(expense: Expense)

    @Query("SELECT * FROM expense")
    suspend fun getAllExpenses(): List<Expense>

}