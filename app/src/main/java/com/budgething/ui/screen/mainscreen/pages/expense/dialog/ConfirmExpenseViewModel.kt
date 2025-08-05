package com.budgething.ui.screen.mainscreen.pages.expense.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budgething.data.local.expense.category.CategoryRepository
import com.budgething.data.local.expense.item.Item
import com.budgething.data.local.expense.item.ItemRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ConfirmExpenseViewModel(
    private val categoryRepo: CategoryRepository,
    private val itemRepo: ItemRepository
) : ViewModel() {

    private val _mainCategory = MutableStateFlow("")
    private val _subCategory = MutableStateFlow("")
    private val _query = MutableStateFlow("")

    val mainCategory: StateFlow<String> get() = _mainCategory
    val subCategory: StateFlow<String> get() = _subCategory
    val query: StateFlow<String> get() = _query

    val mainCategories: StateFlow<List<String>> = flow {
        emit(categoryRepo.getMainCategories())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    val subCategories: StateFlow<List<String>> = _mainCategory.flatMapLatest { main ->
        flow {
            emit(categoryRepo.getSubCategories(main))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val allItems: StateFlow<List<Item>> = itemRepo.getItems()
        .stateIn(viewModelScope, SharingStarted.Companion.WhileSubscribed(), emptyList())

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val filteredOptions: StateFlow<List<Item>> = _query
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if (query.isBlank()) flowOf(emptyList())
            else itemRepo.searchFor(query)
        }
        .stateIn(viewModelScope, SharingStarted.Companion.WhileSubscribed(), emptyList())

    fun getMainCategories(onResult: (List<String>) -> Unit) {
        viewModelScope.launch {
            onResult(categoryRepo.getMainCategories())
        }
    }

    fun getSubCategories(mainCategory: String, onResult: (List<String>) -> Unit) {
        viewModelScope.launch {
            onResult(categoryRepo.getSubCategories(mainCategory))
        }
    }

    fun setMainCategoryTo(category: String) {
        _mainCategory.value = category
    }

    fun setSubCategoryTo(category: String) {
        _subCategory.value = category
    }

    fun fillMainCategory() {
        viewModelScope.launch {
            _mainCategory.value = categoryRepo.getMainCategoryFor(_subCategory.value) ?: ""
        }
    }

    fun changeQuery(query: String) {
        _query.value = query
    }

    fun clearMainCategory() = setMainCategoryTo("")
    fun clearSubCategory() = setSubCategoryTo("")
    fun clearQuery() = changeQuery("")
    fun clearAllState() {
        clearQuery()
        clearMainCategory()
        clearSubCategory()
    }

    fun addNewItem(item: Item) {
        viewModelScope.launch {
            itemRepo.add(item)
        }
    }

}