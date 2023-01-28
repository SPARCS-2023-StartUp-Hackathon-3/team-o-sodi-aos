package com.haeyum.sodi.data.api.getPost

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPostListResponse(
    @SerialName("PostId")
    val postId: String,
    @SerialName("Title")
    val title: String,
    @SerialName("Description")
    val description: String,
    @SerialName("UserName")
    val userName: String,
    @SerialName("WearTag")
    val wearTag: List<String>,
    @SerialName("Comments")
    val comments: List<String>,
    @SerialName("Likes")
    val likes: List<String>,
    @SerialName("Images")
    val images: List<String>,
    @SerialName("Date")
    val date: String
)