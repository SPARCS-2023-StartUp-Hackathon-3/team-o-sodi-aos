package com.haeyum.sodi.data.api.getPost

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPostListResponse(
    val post: Post,
    val wearTags: List<WearTag>
)