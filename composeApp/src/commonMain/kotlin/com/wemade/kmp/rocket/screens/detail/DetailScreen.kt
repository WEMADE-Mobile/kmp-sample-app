package com.wemade.kmp.rocket.screens.detail

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.wemade.kmp.rocket.model.dummyDetailData
import com.wemade.kmp.rocket.theme.BodyM
import com.wemade.kmp.rocket.theme.Display
import com.wemade.kmp.rocket.theme.Title
import com.wemade.kmp.rocket.theme.background2
import com.wemade.kmp.rocket.theme.background2Inverse
import com.wemade.kmp.rocket.theme.foreground1

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    launchId: String,
    onBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    // TODO: launchId 값을 통해 API 호출 필요
    val itemData = dummyDetailData

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = itemData.title, style = Display) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
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
                        .padding(vertical = 30.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalPlatformContext.current)
                            .data(itemData.imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = itemData.description,
                        modifier = Modifier
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
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Column(
                    modifier = Modifier.padding(0.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "발사 정보",
                        style = Title,
                        color = foreground1
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "성공 여부 : ",
                            style = BodyM,
                            color = foreground1
                        )

                        Text(
                            text = if (itemData.isSuccessLaunched) "🟢 성공" else "❌ 실패",
                            style = Title,
                            color = foreground1
                        )
                    }

                    Text(
                        text = "날짜 : " + itemData.createdAt,
                        style = BodyM,
                        color = Color.Gray
                    )
                }

                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = "로켓 정보",
                        style = Title,
                        color = foreground1
                    )

                    Text(
                        text = "이름 : " + itemData.title,
                        style = BodyM,
                        color = foreground1
                    )

                    Text(
                        text = "설명 : " + itemData.description,
                        style = BodyM,
                        color = foreground1
                    )

                    Text(
                        text = "높이 : " + itemData.height,
                        style = BodyM,
                        color = foreground1
                    )
                    Text(
                        text = "지름 : " + itemData.diameter,
                        style = BodyM,
                        color = foreground1
                    )
                    Text(
                        text = "무게 : " + itemData.mass,
                        style = BodyM,
                        color = foreground1
                    )
                }

                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = "이미지",
                        style = Title,
                        color = foreground1
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(items = itemData.images) { image ->
                            AsyncImage(
                                model =  ImageRequest.Builder(LocalPlatformContext.current)
                                    .data(image)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "Images",
                                modifier = Modifier
                                    .size(width = 120.dp, height = 80.dp)
                                    .background(Color.LightGray),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                }

                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = "위키피디아",
                        style = Title,
                        color = foreground1
                    )

                    Text(
                        text = itemData.wikipedia,
                        style = BodyM,
                        color = foreground1
                    )
                }
            }
        }
    }
}