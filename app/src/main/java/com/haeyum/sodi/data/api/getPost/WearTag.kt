package com.haeyum.sodi.data.api.getPost

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WearTag(
    @SerialName("Brand")
    val brand: String,
    @SerialName("Images")
    val images: List<String>,
    @SerialName("Price")
    val price: String,
    @SerialName("Product")
    val product: String,
    @SerialName("StoreId")
    val storeId: String,
)