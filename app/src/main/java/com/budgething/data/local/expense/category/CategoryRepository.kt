package com.budgething.data.local.expense.category

class CategoryRepository(private val dao: CategoryDao) {
    suspend fun getAll(): List<Category> = dao.getCategory()

    suspend fun getMainCategoryFor(sub: String): String? {
        val all = dao.getCategory()
        return all.firstOrNull { it.sub.contains(sub) }?.main
    }

    suspend fun getSubCategories(main: String): List<String> {
        val all = dao.getCategory()
        return all.firstOrNull { it.main == main }?.sub ?: emptyList()
    }

    suspend fun getMainCategories(): List<String> {
        return dao.getCategory().map { it.main }
    }
}