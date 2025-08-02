package com.budgething.data.local.expense.category

class CategoryRepository(private val dao: CategoryDao) {

    suspend fun getAll(): List<Category> = dao.getCategory()

}