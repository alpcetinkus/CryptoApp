package com.example.coinsapp.recyclerView

import com.google.gson.annotations.SerializedName

class CoinHomeList(
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("price_change_24h")
    val priceChange24h: Double
    )
{}