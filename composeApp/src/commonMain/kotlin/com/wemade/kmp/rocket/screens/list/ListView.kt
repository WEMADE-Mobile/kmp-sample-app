package com.wemade.kmp.rocket.screens.list

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wemade.kmp.rocket.model.ListData
import com.wemade.kmp.rocket.theme.Display
import com.wemade.kmp.rocket.theme.background2
import com.wemade.kmp.rocket.theme.background2Inverse

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ListView(
    dataList: List<ListData>,
    onItemClick: (ListData) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "로켓", style = Display)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = background2,
                    titleContentColor = background2Inverse
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            item {
                Spacer(modifier = Modifier.height((20.dp)))
            }
            items(items = dataList) { item ->
                ListItem(
                    itemData = item,
                    onClick = { onItemClick(item) },
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope
                )
            }
        }
    }
}