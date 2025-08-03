package com.budgething.data.local.expense.item

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.budgething.data.local.expense.item.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert
    suspend fun addItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("SELECT * FROM item WHERE name LIKE '%' || :q || '%'")
    fun getItemsFor(q: String): Flow<List<Item>>

    @Query("SELECT * FROM item")
    fun getItems(): Flow<List<Item>>

}