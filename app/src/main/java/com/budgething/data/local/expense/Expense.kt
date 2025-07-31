package com.budgething.data.local.expense

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "expense")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: LocalDate,
    val mainCategory: String,
    val subCategory: String,
    val name: String,
    val amount: Double
)