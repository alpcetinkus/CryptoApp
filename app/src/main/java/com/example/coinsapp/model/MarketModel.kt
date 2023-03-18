package com.example.coinsapp.model.models


class MarketModel : ArrayList<MarketModelItem>()

data class MarketModelItem(
    val current_price: Double,
    val high_24h: Double,
    val id: String,
    val image: String,
    val low_24h: Double,
    val market_cap: Double,
    val market_cap_change_24h: Double,
    val market_cap_change_percentage_24h: Double,
    val name: String,
    val price_change_24h: Double,
    val price_change_percentage_24h: Double,
//    val roi: Roi,
    val symbol: String
)

data class Roi(
    val currency: String,
    val percentage: Double,
    val times: Double
)