package com.demo.android.connectory.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.android.connectory.R
import com.demo.android.connectory.presentation.component.EmployeeCard
import com.demo.android.connectory.presentation.component.EmployeeCardSkeleton

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val uiState = homeViewModel.uiStateFlow.collectAsState().value
    var userName by remember { mutableStateOf("") }
    Scaffold(
        topBar = { ConnectoryTopAppBar() },
        content = { padding ->
            Box(Modifier.padding(padding)) {
                Column {
                    OutlinedTextField(
                        value = userName,
                        onValueChange = { userName = it },
                        Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                    )
                    Content(uiState, userName)
                }
            }
        },
    )
}

@Composable
private fun Content(uiState: HomeScreenUiState, nameSearchQuery: String) {
    when (uiState) {
        is HomeScreenUiState.Loading -> BoxWithConstraints {
            LazyColumn {
                items(7) {
                    EmployeeCardSkeleton()
                }
            }
        }
        is HomeScreenUiState.Loaded -> BoxWithConstraints {
            LazyColumn {
                items(uiState.employees.filter {
                    it.fullName.lowercase().contains(nameSearchQuery)
                }) {
                    EmployeeCard(employee = it)
                }
            }
        }

    }
}

@Composable
private fun ConnectoryTopAppBar() {
    TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
}