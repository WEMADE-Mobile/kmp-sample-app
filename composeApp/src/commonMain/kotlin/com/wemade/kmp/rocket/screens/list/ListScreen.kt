package com.wemade.kmp.rocket.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ListScreen(
    mainViewModel: ListViewModel = koinViewModel<ListViewModel>()
) {
    val greetings by mainViewModel.greetingList.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .padding(all = 10.dp)
            .safeContentPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        greetings.forEach { greeting ->
            Text(greeting)
            HorizontalDivider()
        }
    }
}