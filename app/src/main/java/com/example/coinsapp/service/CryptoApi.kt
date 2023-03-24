package com.example.coinsapp.service


import com.example.coinsapp.model.MarketModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoApi {
    @GET("data-api/v3/cryptocurrency/listing?start=1&limit=500")
    fun getMarketData(): Call<MarketModel>

}