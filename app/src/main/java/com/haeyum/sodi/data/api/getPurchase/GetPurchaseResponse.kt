package com.haeyum.sodi.data.api.getPurchase

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPurchaseResponse(
    @SerialName("PurchaseId")
    val purchaseId: String,
    @SerialName("StoreId")
    val storeId: String,
    @SerialName("Brand")
    val brand: String,
    @SerialName("Product")
    val product: String,
    @SerialName("Price")
    val price: String,
    @SerialName("UserName")
    val userName: String,
    @SerialName("Images")
    val images: List<String>,
    @SerialName("Date")
    val date: String
)