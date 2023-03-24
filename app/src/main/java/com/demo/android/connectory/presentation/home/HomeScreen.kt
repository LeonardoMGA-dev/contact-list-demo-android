package com.demo.android.connectory.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.android.connectory.presentation.component.EmployeeCard
import com.demo.android.connectory.util.employeeFakeList

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    Scaffold(
        topBar = { TopAppBar() {} },
        content = { padding ->
            Box(Modifier.padding(padding)) {
                LazyColumn {
                    items(employeeFakeList) {
                        // TODO: implement appearing animation
                        EmployeeCard(employee = it)
                    }
                }
            }
        },
    )
}