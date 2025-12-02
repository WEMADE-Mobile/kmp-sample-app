package com.wemade.greeting.greetingkmp.view

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
import com.wemade.greeting.greetingkmp.view.RocketListItem
import com.wemade.greeting.greetingkmp.model.RocketItemData
import com.wemade.greeting.greetingkmp.theme.Display
import com.wemade.greeting.greetingkmp.theme.background2
import com.wemade.greeting.greetingkmp.theme.background2Inverse

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RocketListView(
    dataList: List<RocketItemData>,
    onItemClick: (RocketItemData) -> Unit,
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
            items(dataList) { item ->
                RocketListItem(
                    itemData = item,
                    onClick = { onItemClick(item) },
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope
                )
            }
        }
    }
}