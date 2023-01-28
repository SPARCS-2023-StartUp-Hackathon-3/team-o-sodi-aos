package com.haeyum.sodi.data.repository

import com.haeyum.sodi.data.api.getPost.GetPostListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class PostRepository @Inject constructor(private val client: HttpClient) {
    suspend fun getPostList() =
        client.get("http://ec2-43-201-75-12.ap-northeast-2.compute.amazonaws.com:8080/posts/getPost")
            .body<List<GetPostListResponse>>()

    suspend fun getWearItem() {

    }
}