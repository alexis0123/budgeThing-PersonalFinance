package com.budgething.data.local.income.type

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.budgething.data.local.income.IncomeType
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeTypeDao {

    @Insert
    suspend fun addType(incomeType: IncomeType)

    @Query("SELECT * FROM `income-type` WHERE name LIKE :q || '%'")
    fun getTypes(q: String): Flow<List<IncomeType>>

}