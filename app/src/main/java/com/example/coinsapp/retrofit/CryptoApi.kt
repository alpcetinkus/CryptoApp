package com.example.coinsapp.retrofit

import com.example.coinsapp.recyclerView.FavCardList
import com.example.coinsapp.retrofit.models.MarketModel
import com.example.coinsapp.retrofit.models2.CoinDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoApi {
    @GET("api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1")
    fun getCoinListMarket(): Call<MarketModel>

    @GET("api/v3/coins/{id}")
    fun getDetails(@Path("id")id: String): Call<CoinDetail>
    @GET("api/v3/coins/{id}")
    fun getFav(@Path("id")id: String): Call<FavCardList>
}