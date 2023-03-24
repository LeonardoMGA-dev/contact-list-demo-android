package com.demo.android.connectory.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.android.connectory.R
import com.demo.android.connectory.presentation.component.EmployeeCard
import com.demo.android.connectory.presentation.component.EmployeeCardSkeleton

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    Scaffold(
        topBar = { ConnectoryTopAppBar() },
        content = { padding ->
            Box(Modifier.padding(padding)) {
                val uiState = homeViewModel.uiStateFlow.collectAsState().value
                Content(uiState)
            }
        },
    )
}

@Composable
private fun Content(uiState: HomeScreenUiState) {
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
                items(uiState.employees) {
                    EmployeeCard(employee = it)
                }
            }
        }

    }
}

@Composable
private fun ConnectoryTopAppBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) }
    )
}