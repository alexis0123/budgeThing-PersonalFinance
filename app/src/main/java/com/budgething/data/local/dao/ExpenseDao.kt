package com.budgething.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import java.time.LocalDate

@Dao
interface ExpenseDao {

    @Insert
    suspend fun addExpense(
        date: LocalDate,
        mainCategory: String,
        subCategory: String,
        name: String,
        amount: Double
    )

}