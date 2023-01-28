package com.haeyum.sodi.data.repository

import android.graphics.Bitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.haeyum.sodi.data.api.getCloset.GetClosetResponse
import com.haeyum.sodi.data.api.getPost.GetPostListResponse
import com.haeyum.sodi.data.api.getPurchase.GetPurchaseResponse
import com.haeyum.sodi.data.api.getSpecificStore.GetSpecificStoreResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.util.InternalAPI
import io.ktor.utils.io.core.buildPacket
import io.ktor.utils.io.core.writeFully
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@OptIn(InternalAPI::class)
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
            parameter("userName", "Pangmu")
        }.body<GetClosetResponse>()

    suspend fun getPurchase() =
        client.get("http://ec2-43-201-75-12.ap-northeast-2.compute.amazonaws.com:8080/purchase/myPurchase") {
            parameter("userName", "Pangmu")
        }.body<List<GetPurchaseResponse>>()

    suspend fun postUploadClothesImage(username: String, bitmap: Bitmap) {
        client.post("http://ec2-43-201-75-12.ap-northeast-2.compute.amazonaws.com:8080/loginRouter/camera") {
            body = MultiPartFormDataContent(
                formData {
                    appendInput(
                        key = "file",
                        headers = Headers.build {
                            append(HttpHeaders.ContentType, "image/jpg")
                            append(
                                HttpHeaders.ContentDisposition,
                                "filename=kawai_pangmoo_${System.currentTimeMillis()}.jpg"
                            )
                        }
                    ) {
                        buildPacket {
                            ByteArrayOutputStream().let {
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                                it.toByteArray()
                            }.also(::writeFully)
                        }
                    }
                    append(key = "userName", value = username)
                }
            )
        }
    }
}