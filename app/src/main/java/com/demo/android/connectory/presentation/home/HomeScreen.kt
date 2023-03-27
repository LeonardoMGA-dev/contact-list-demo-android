package com.demo.android.connectory.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.android.connectory.R
import com.demo.android.connectory.presentation.component.EmployeeCard
import com.demo.android.connectory.presentation.component.EmployeeCardSkeleton
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val uiState = homeViewModel.uiStateFlow.collectAsState().value
    var employeeName by rememberSaveable { mutableStateOf("") }
    val refreshing by remember { mutableStateOf(false) }
    Scaffold(
        topBar = { ConnectoryTopAppBar() },
        content = { padding ->
            SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = refreshing), onRefresh = {
                homeViewModel.fetchEmployees(employeeName = employeeName)
            }) {
                Box(Modifier.padding(padding)) {
                    Column {
                        OutlinedTextField(value = employeeName,
                            onValueChange = {
                                employeeName = it
                                homeViewModel.searchDebounce(employeeName)
                            },
                            Modifier
                                .padding(5.dp)
                                .fillMaxWidth(),
                            label = { Text(stringResource(id = R.string.employee_name)) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search, contentDescription = null
                                )
                            })
                        Content(uiState)
                    }
                }
            }
        },
    )
}

@Composable
private fun Content(uiState: HomeScreenUiState) {
    when (uiState) {
        is HomeScreenUiState.Loading -> LoadingScreen()
        is HomeScreenUiState.Loaded -> LoadedScreen(uiState)
        is HomeScreenUiState.Error -> MessageScreen(message = stringResource(id = uiState.errorMessage))
    }
}

@Composable
private fun LoadingScreen() {
    BoxWithConstraints {
        LazyColumn {
            items(7) {
                EmployeeCardSkeleton()
            }
        }
    }
}

@Composable
private fun LoadedScreen(uiState: HomeScreenUiState.Loaded) {
    if (uiState.employees.isEmpty()) {
        MessageScreen(message = stringResource(id = R.string.no_employees_found_message))
    } else {
        BoxWithConstraints {
            LazyColumn {
                items(uiState.employees) {
                    EmployeeCard(employee = it)
                }
            }
        }
    }
}

@Composable
private fun MessageScreen(message: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(message)
    }
}

@Composable
private fun ConnectoryTopAppBar() {
    TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
}