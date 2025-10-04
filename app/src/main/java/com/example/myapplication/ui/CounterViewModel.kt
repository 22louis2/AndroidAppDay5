package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel: ViewModel() {
    private val _counterUiState = MutableStateFlow(CounterUiState(count = 0, isLoading = false)) // emits the data
    val counterUiState = _counterUiState.asStateFlow() // It exposes data as read only

    fun increment() {
        _counterUiState.update { counterUiState ->
            counterUiState.copy(
                count = counterUiState.count + 1,
                errorMessage = null,
            )
        }
    }

    fun decrement() {
        // update MutableStateFlow object
        if(counterUiState.value.count == 0)
        {
            // do not decrement, but show error message
            _counterUiState.update { counterUiState ->
                counterUiState.copy(
                    errorMessage = "Count cannot be negative",
                )
            }
        }
        else {
            _counterUiState.update { counterUiState ->
                counterUiState.copy(
                    count = counterUiState.count - 1,
                    errorMessage = null,
                )
            }
        }
    }
}