package com.haeyum.sodi.data.api.getProfile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetProfile(
    @SerialName("Bio")
    val bio: String,
    @SerialName("Closet")
    val closet: List<Closet>,
    @SerialName("Codi")
    val codi: List<String>,
    @SerialName("Email")
    val email: String,
    @SerialName("Followers")
    val followers: List<String>,
    @SerialName("Following")
    val following: List<String>,
    @SerialName("Point")
    val point: Int,
    @SerialName("ProfileImg")
    val profileImg: String,
    @SerialName("UserName")
    val userName: String,
)