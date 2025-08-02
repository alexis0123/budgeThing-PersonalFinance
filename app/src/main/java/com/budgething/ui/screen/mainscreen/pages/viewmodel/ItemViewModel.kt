package com.budgething.ui.screen.mainscreen.pages.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budgething.data.local.expense.item.ItemRepository
import kotlinx.coroutines.flow.Flow
import com.budgething.data.local.expense.item.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class ItemViewModel(
    private val repo: ItemRepository
): ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query
    val items: Flow<List<Item>> = _query.flatMapLatest {q -> repo.searchFor(q)}
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun changeValue(value: String) {
        _query.value = value
    }

    fun add(item: Item) {
        viewModelScope.launch {
            repo.add(item)
        }
    }

}