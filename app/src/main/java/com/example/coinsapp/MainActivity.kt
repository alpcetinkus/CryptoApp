package com.example.coinsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coinsapp.retrofit.RetrofitClient
import com.example.coinsapp.retrofit.models.CoinListMarketResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("alp","sorgu atiyorum")

        RetrofitClient.getApiImplementation().getCoinListMarket().enqueue(object : Callback<CoinListMarketResponseModel> {
            override fun onResponse(call: Call<CoinListMarketResponseModel>?, response: Response<CoinListMarketResponseModel>?) {

            }

            override fun onFailure(call: Call<CoinListMarketResponseModel>?, t: Throwable?) {

            }

        })
    }
}