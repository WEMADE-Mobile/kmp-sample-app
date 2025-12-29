package com.wemade.kmp.rocket.screens.list

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.wemade.kmp.rocket.model.ListData
import com.wemade.kmp.rocket.theme.BodyL
import com.wemade.kmp.rocket.theme.BodyM
import com.wemade.kmp.rocket.theme.Title
import com.wemade.kmp.rocket.theme.foreground1
import com.wemade.kmp.rocket.theme.foreground2


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ListItem(
    itemData: ListData,
    onClick: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    with(sharedTransitionScope) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(180.dp)
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .clickable { onClick() }
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(itemData.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = itemData.title,
                modifier = Modifier
                    .sharedElement(
                        rememberSharedContentState(key = "image-${itemData.id}"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
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
                Text(
                    text = if (itemData.isSuccessLaunched) "🟢 성공" else "❌ 실패",
                    style = Title,
                    color = foreground1
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = itemData.title,
                    style = BodyL,
                    color = foreground1
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = itemData.launchDate,
                    style = BodyM,
                    color = foreground2
                )
            }
        }
    }
}
