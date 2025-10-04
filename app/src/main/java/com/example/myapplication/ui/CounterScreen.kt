package com.example.myapplication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CounterScreen(modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            //var count: MutableIntState = remember { mutableIntStateOf(0) }
            //var count by rememberSaveable { mutableStateOf(0) }

            val counterViewModel: CounterViewModel = viewModel{
                CounterViewModel()
            }

            val counterUiState: CounterUiState by counterViewModel.counterUiState.collectAsState()

            Text(text = "${counterUiState.count}")
            Spacer(modifier = Modifier.padding(20.dp))
            Row {
                Button(
                    onClick = {
                        counterViewModel.decrement()
                    }
                ) {
                    Text(text = "Decrement")
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Button(
                    onClick = {
                        counterViewModel.increment()
                    }
                ) {
                    Text(text = "Increment")
                }
            }

            when {
                counterUiState.isLoading -> {
                    CircularProgressIndicator()
                }
                counterUiState.isCompleted -> {
                    Text(text = "Completed")
                }
                counterUiState.errorMessage != null -> {
                    Text(text = counterUiState.errorMessage!!)
                }
            }
        }
    }
}

fun increment(count: Int) = count + 1

fun decrement(count: Int) = count - 1

@Preview(
    showSystemUi = true
)
@Composable
fun CounterScreenPreview() {
    CounterScreen()
}