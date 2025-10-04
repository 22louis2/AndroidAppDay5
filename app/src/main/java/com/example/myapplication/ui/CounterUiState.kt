package com.example.myapplication.ui

data class CounterUiState(
    val count: Int,
    val isLoading: Boolean,
    val errorMessage: String? = null,
    val isCompleted: Boolean = false
)
