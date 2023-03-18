package com.example.coinsapp.model



data class CoinDetail(
    val coingeckoRank: Int,
    val coingeckoScore: Double,
    val id: String,
    val image: Image,
    val marketData: MarketData,
    val name: String
)

data class MarketData(
    val currentPrice: CurrentPrice,
    val high24h: High24h,
    val low24h: Low24h,
    val marketCap: MarketCap,
    val marketCapRank: Int,
)

data class MarketCap(
    val usd: Double,
)

data class CurrentPrice(
    val usd: Double,
)

data class High24h(
    val usd: Double,
)

data class Low24h(
    val usd: Double,
)

data class Image(
    val large: String,
    val small: String,
    val thumb: String
)
