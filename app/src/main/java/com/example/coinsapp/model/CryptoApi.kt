package com.example.coinsapp.model


import com.example.coinsapp.model.models.MarketModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoApi {
    @GET("api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1")
    fun getCoinListMarket(): Call<MarketModel>

    @GET("api/v3/coins/{id}")
    fun getDetails(@Path("id")id: String): Call<MarketModel>

}