package com.example.coinsapp.retrofit

import com.example.coinsapp.retrofit.models.CoinListMarketResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoApi {
    @GET("coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1")
    fun getCoinListMarket(): Call<CoinListMarketResponseModel>
}