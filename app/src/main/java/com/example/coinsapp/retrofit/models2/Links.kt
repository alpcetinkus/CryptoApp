package com.example.coinsapp.retrofit.models2


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("announcement_url")
    val announcementUrl: List<String>,
    @SerializedName("bitcointalk_thread_identifier")
    val bitcointalkThreadİdentifier: Any,
    @SerializedName("blockchain_site")
    val blockchainSite: List<String>,
    @SerializedName("chat_url")
    val chatUrl: List<String>,
    @SerializedName("facebook_username")
    val facebookUsername: String,
    @SerializedName("homepage")
    val homepage: List<String>,
    @SerializedName("official_forum_url")
    val officialForumUrl: List<String>,
    @SerializedName("repos_url")
    val reposUrl: ReposUrl,
    @SerializedName("subreddit_url")
    val subredditUrl: String,
    @SerializedName("telegram_channel_identifier")
    val telegramChannelİdentifier: String,
    @SerializedName("twitter_screen_name")
    val twitterScreenName: String
)