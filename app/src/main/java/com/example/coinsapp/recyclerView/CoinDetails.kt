package com.example.coinsapp.recyclerView

import com.example.coinsapp.retrofit.models2.MarketData
import com.google.gson.annotations.SerializedName

data class CoinDetails(
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("market_cap")
    val market_cap: Double,
    @SerializedName("price")
    val price: Double,
    @SerializedName("price_percentage_change")
    val price_percentage_change: Double,
    @SerializedName("low_price")
    val low_price: Double,
    @SerializedName("high_price")
    val high_price: Double,
    @SerializedName("description")
    val description: String,
    @SerializedName("market_data")
    val marketData: MarketData

)
