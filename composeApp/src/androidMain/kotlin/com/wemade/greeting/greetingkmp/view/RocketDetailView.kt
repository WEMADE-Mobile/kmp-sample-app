package com.wemade.greeting.greetingkmp.view

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RocketDetailView(
    itemData: RocketItemData,
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = itemData.title, style = Display) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = background2Inverse
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = background2,
                    titleContentColor = background2Inverse
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            with(sharedTransitionScope) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp), // 상하 여백
                    contentAlignment = Alignment.Center // 중앙 정렬
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalPlatformContext.current)
                            .data(itemData.imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = itemData.description,
                        modifier = Modifier
                            // 리스트와 같은 키를 사용해 연결
                            .sharedElement(
                                rememberSharedContentState(key = "image-${itemData.title}"),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                            // 요청하신 크기 (100x140)
                            .width(100.dp)
                            .height(140.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.LightGray),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (itemData.isSuccessLaunched) "Launch Success 🟢" else "Launch Failed ❌",
                        style = Title,
                        color = foreground1
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = itemData.createdAt,
                        style = BodyM,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Description",
                    style = Title,
                    color = foreground1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = itemData.description,
                    style = BodyL,
                    color = foreground1,
                    lineHeight = 24.sp
                )
            }
        }
    }
}