package com.demo.android.connectory.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.android.connectory.R
import com.demo.android.connectory.presentation.component.EmployeeCard

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        homeViewModel.fetchEmployees()
    }
    Scaffold(
        topBar = { ConnectoryTopAppBar() },
        content = { padding ->
            Box(Modifier.padding(padding)) {
                LazyColumn {
                    items(homeViewModel.employees) {
                        // TODO: implement appearing animation
                        EmployeeCard(employee = it)
                    }
                }
            }
        },
    )
}

@Composable
private fun ConnectoryTopAppBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) }
    )
}