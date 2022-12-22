package com.example.coinsapp.retrofit.models2


import com.google.gson.annotations.SerializedName

data class ReposUrl(
    @SerializedName("bitbucket")
    val bitbucket: List<Any>,
    @SerializedName("github")
    val github: List<String>
)