package com.wemade.kmp.rocket.mapper

import com.wemade.kmp.rocket.model.ui.LaunchDetailUiModel

/**
 * Domain 영역의 LaunchDetail 관련 클래스를 임시로 가정, 이후 실제 Domain의 클래스로 대체할 예정
 **/

data class LaunchDetail(
    val id: String,
    val name: String,
    val dateUtc: String,
    val success: Boolean?,
    val patchSmall: String?,
    val details: String?,
    val linksArticle: String?,
    val linksWikipedia: String?,
    val linksWebcast: String?
)


fun LaunchDetail.toDetailUiModel(): LaunchDetailUiModel {
    val status = when (success) {
        true -> "🟢성공"
        false -> "❌실패"
        null -> "진행중"
    }

    return LaunchDetailUiModel(
        id = id,
        name = name,
        imageUrl = patchSmall,
        statusText = status,
        dateUtcRaw = dateUtc,
        details = details,
        articleUrl = linksArticle,
        wikipediaUrl = linksWikipedia,
        webcastUrl = linksWebcast
    )
}
