package com.example.coinsapp.recyclerView

import com.google.gson.annotations.SerializedName

data class CoinList(
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("current_price")
    val currentPrice: Double,
    @SerializedName("price_change_24h")
    val priceChange24h: Double,
    @SerializedName("id")
    val id: String,
    )
{
}