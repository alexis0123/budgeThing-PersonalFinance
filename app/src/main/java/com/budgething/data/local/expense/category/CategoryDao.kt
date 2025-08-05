package com.budgething.data.local.expense.category

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CategoryDao {

    @Insert
    suspend fun addCategory(category: Category)

    @Update
    suspend fun editCategory(category: Category)

    @Query("SELECT * FROM category")
    suspend fun getCategory(): List<Category>

}