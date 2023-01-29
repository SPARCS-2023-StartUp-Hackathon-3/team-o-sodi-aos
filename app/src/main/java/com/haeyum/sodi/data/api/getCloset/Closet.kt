package com.haeyum.sodi.data.api.getCloset

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Closet(
    @SerialName("Brand")
    val brand: String,
    @SerialName("Images")
    val images: List<String>,
    @SerialName("Price")
    val price: String,
    @SerialName("Product")
    val product: String,
    @SerialName("Purchase")
    val purchase: Boolean,
    @SerialName("StoreId")
    val storeId: String
)