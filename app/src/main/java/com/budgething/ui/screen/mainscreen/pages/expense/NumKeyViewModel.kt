package com.budgething.ui.screen.mainscreen.pages.expense

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NumKeyViewModel: ViewModel() {

    private val _amount = MutableStateFlow("")
    val amount: StateFlow<String> get() = _amount

    fun addDigit(num: String) {
        if (_amount.value.length >= 9) return

        when(_amount.value) {
            "0" -> _amount.value = num
            else -> _amount.value += num
        }
    }

    fun addZero() {
        if (_amount.value.length >= 12) return
        if (_amount.value != "0") _amount.value += "0"
    }

    fun addDot() {
        if (!_amount.value.contains(".")) {
            when(_amount.value) {
                "", "0" -> addDigit("0.")
                else -> addDigit(".")
            }
        }
    }

    fun addTwoZeros() {
        when(_amount.value) {
            "" -> addZero()
            "0" -> {}
            else -> addDigit("00")
        }
    }

    fun deleteLast() {
        _amount.value = _amount.value.dropLast(1)
    }

    fun deleteAll() {
        _amount.value = ""
    }
}