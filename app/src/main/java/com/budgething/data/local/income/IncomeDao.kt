package com.budgething.data.local.income

import androidx.room.Dao
import androidx.room.Insert
import com.budgething.data.local.income.Income

@Dao
interface IncomeDao {

    @Insert
    suspend fun addIncome(income: Income)

}