package com.budgething.data.local.expense.category

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class CategoryRepository(private val dao: CategoryDao) {
    fun getAll(): Flow<List<Category>> = dao.getCategory()

    suspend fun getMainCategories(): List<String> {
        return dao.getCategory()
            .map { list -> list.map { it.main } }
            .first()
    }

    suspend fun getSubCategories(main: String): List<String> {
        val normalizedMain = main.trim().lowercase()
        return dao.getCategory()
            .map { list ->
                list.firstOrNull {
                    it.main.trim().lowercase() == normalizedMain
                }?.sub ?: emptyList()
            }
            .first()
    }

    suspend fun getMainCategoryFor(sub: String): String? {
        val normalizedSub = sub.trim().lowercase()
        return dao.getCategory()
            .map { list ->
                list.firstOrNull { category ->
                    category.sub.any {
                        it.trim().lowercase() == normalizedSub
                    }
                }?.main
            }
            .first()
    }

    suspend fun editCategory(category: Category) {
        dao.editCategory(category)
    }
}