package com.example.coinsapp.retrofit.models2


import com.google.gson.annotations.SerializedName

data class Public─░nterestStats(
    @SerializedName("alexa_rank")
    val alexaRank: Int,
    @SerializedName("bing_matches")
    val bingMatches: Any
)