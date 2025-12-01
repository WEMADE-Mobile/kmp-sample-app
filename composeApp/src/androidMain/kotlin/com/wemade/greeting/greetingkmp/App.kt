package com.wemade.greeting.greetingkmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.wemade.greeting.greetingkmp.model.RocketItemData
import com.wemade.greeting.greetingkmp.theme.BodyL
import com.wemade.greeting.greetingkmp.theme.BodyM
import com.wemade.greeting.greetingkmp.theme.Display
import com.wemade.greeting.greetingkmp.theme.Title
import com.wemade.greeting.greetingkmp.theme.background2
import com.wemade.greeting.greetingkmp.theme.background2Inverse
import com.wemade.greeting.greetingkmp.theme.foreground1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App(
    mainViewModel: MainViewModel = viewModel()
) {
    val dataList = mainViewModel.dummyData    // ✅뷰모델에서 실제 데이터를 넣어주세요!

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "로켓",
                        style = Display
                    )},
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
            items(dataList) { text ->
                ListItem(text)
            }
        }
    }
}

@Composable
fun ListItem(itemData: RocketItemData) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .height(180.dp)
            .padding(20.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalPlatformContext.current)
                .data(itemData.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = itemData.description,
            modifier = Modifier
                .width(100.dp)
                .height(140.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (itemData.isSuccessLaunched) "🟢" else "❌"
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = itemData.title,
                    style = Title,
                    color = foreground1
                )
            }


            Text(
                text = itemData.description,
                style = BodyL,
                color = foreground1
            )

            Text(
                text = itemData.createdAt,
                style = BodyM,
                color = foreground1
            )
        }
    }
}