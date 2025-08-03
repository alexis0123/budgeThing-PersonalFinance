package com.budgething.data.helper

import com.budgething.data.local.expense.category.Category

object CategoryHelper {

    fun getMainBySub(categories: List<Category>, sub: String): String? {
        return categories.firstOrNull { it.sub.contains(sub) }?.main
    }

    fun getSubsByMain(categories: List<Category>, main: String): List<String> {
        return categories.firstOrNull { it.main == main }?.sub ?: emptyList()
    }

}