package com.haeyum.sodi.data.api.getSpecificStore

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSpecificStoreResponse(
    @SerialName("StoreId")
    val storeId: String,
    @SerialName("Brand")
    val brand: String,
    @SerialName("Product")
    val product: String,
    @SerialName("Price")
    val price: String,
    @SerialName("Images")
    val images: List<String>
)