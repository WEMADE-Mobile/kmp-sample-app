package com.wemade.greeting.greetingkmp.presentation.mapper

import com.wemade.greeting.greetingkmp.presentation.model.LaunchListItemUiModel

/**
 * Domain 영역의 Launch 관련 클래스를 임시로 가정, 이후 실제 Domain의 클래스로 대체할 예정
 **/
data class Launch(
    val id: String,
    val name: String,
    val dateUtc: String,
    val success: Boolean?,
    val patchSmall: String?,
    val launchDate: String
)
fun Launch.toListItemUiModel(): LaunchListItemUiModel {
    val status = when (success) {
        true -> "🟢성공"
        false -> "❌실패"
        null -> "진행중"
    }

    return LaunchListItemUiModel(
        id = id,
        imageUrl = patchSmall,
        statusText = status,
        name = name,
        launchDate = launchDate
    )
}