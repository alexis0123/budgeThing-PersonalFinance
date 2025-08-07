package com.budgething.ui.screen.mainscreen.pages.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budgething.data.local.expense.category.Category
import com.budgething.data.local.expense.category.CategoryRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Locale

class CategoryViewModel(
    private val repo: CategoryRepository
) : ViewModel() {

    private val _categories: StateFlow<List<Category>> = repo.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    val category: StateFlow<List<Category>> = _categories

    suspend fun getMainCategoryFor(sub: String): String? {
        val normalized = sub.trim().lowercase(Locale.US)
        return _categories.value
            .firstOrNull { category ->
                category.sub.any { it.trim().lowercase(Locale.US) == normalized }
            }?.main
    }

    suspend fun getSubCategoriesFor(main: String): List<String> {
        val normalizedMain = main.trim().lowercase(Locale.US)
        return _categories.value
            .firstOrNull { it.main.trim().lowercase(Locale.US) == normalizedMain }
            ?.sub
            ?: emptyList()
    }

    fun getMainCategories(): List<String> {
        return _categories.value.map { it.main }
    }

    fun editCategory(category: Category) {
        viewModelScope.launch {
            repo.editCategory(category)
        }
    }
}
