package com.budgething.data.local.income

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "income")
data class Income(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: LocalDate,
    val name: String,
    val amount: Double
)

@Entity(tableName = "income-type")
data class IncomeType(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)