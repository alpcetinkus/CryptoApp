package com.example.coinsapp.recyclerView

import com.google.gson.annotations.SerializedName

data class FavCardList (
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
    ){

}
