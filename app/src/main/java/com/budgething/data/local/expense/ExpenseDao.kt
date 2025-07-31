package com.budgething.data.local.expense

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface ExpenseDao {

    @Insert
    suspend fun addExpense(expense: Expense)

}