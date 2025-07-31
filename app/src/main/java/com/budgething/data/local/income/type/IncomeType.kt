package com.budgething.data.local.income.type

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "income-type")
data class IncomeType(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)
