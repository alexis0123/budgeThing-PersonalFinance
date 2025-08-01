package com.budgething.data.local.expense.item

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.budgething.data.local.expense.item.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert
    suspend fun addItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("SELECT * FROM item WHERE name LIKE '%' || :q || '%'")
    fun getItems(q: String): Flow<List<Item>>

}