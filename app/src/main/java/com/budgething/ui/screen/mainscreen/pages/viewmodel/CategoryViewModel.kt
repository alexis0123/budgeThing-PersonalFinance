package com.budgething.ui.screen.mainscreen.pages.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budgething.data.local.expense.category.Category
import com.budgething.data.local.expense.category.CategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale

class CategoryViewModel(
    private val repo: CategoryRepository
): ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val category: StateFlow<List<Category>> = _categories

    init { getCategories() }

    fun getCategories() {
        viewModelScope.launch {
            _categories.value = repo.getAll()
        }
    }

}