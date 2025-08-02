package com.budgething.data.local.expense.item

import kotlinx.coroutines.flow.Flow

class ItemRepository(private val dao: ItemDao) {

    suspend fun add(item: Item) {
        dao.addItem(item)
    }

    suspend fun update(item: Item) {
        dao.updateItem(item)
    }

    suspend fun delete(item: Item) {
        dao.deleteItem(item)
    }

    fun searchFor(q: String): Flow<List<Item>> = dao.getItemsFor(q)

}