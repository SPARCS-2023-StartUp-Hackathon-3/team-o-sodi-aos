package com.haeyum.sodi.data.repository

import com.haeyum.sodi.data.api.getCloset.GetClosetResponse
import com.haeyum.sodi.data.api.getPost.GetPostListResponse
import com.haeyum.sodi.data.api.getSpecificStore.GetSpecificStoreResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class PostRepository @Inject constructor(private val client: HttpClient) {
    suspend fun getPostList() =
        client.get("http://ec2-43-201-75-12.ap-northeast-2.compute.amazonaws.com:8080/posts/getPostDump")
            .body<List<GetPostListResponse>>()

    suspend fun getSpecificStoreResponse(storeId: String) =
        client.get("http://ec2-43-201-75-12.ap-northeast-2.compute.amazonaws.com:8080/store/getSpecificStore") {
            parameter("storeId", storeId)
        }.body<GetSpecificStoreResponse>()

    suspend fun getCloset() =
        client.get("http://ec2-43-201-75-12.ap-northeast-2.compute.amazonaws.com:8080/loginRouter/getProfile?") {
            parameter("userName", "HongGilDong")
        }.body<GetClosetResponse>()
}