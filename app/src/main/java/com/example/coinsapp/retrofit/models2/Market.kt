package com.example.coinsapp.retrofit.models2


import com.google.gson.annotations.SerializedName

data class Market(
    @SerializedName("has_trading_incentive")
    val hasTradingİncentive: Boolean,
    @SerializedName("identifier")
    val identifier: String,
    @SerializedName("name")
    val name: String
)