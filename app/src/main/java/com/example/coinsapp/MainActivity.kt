package com.example.coinsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coinsapp.retrofit.RetrofitClient
import com.example.coinsapp.retrofit.models.MarketModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("alp","sorgu atiyorum")

        RetrofitClient.getApiImplementation().getCoinListMarket().enqueue(object : Callback<MarketModel> {
            override fun onResponse(call: Call<MarketModel>?, response: Response<MarketModel>?) {

                if (response != null) {
                    var responseBody = response.body()

                    for (coin in responseBody) {
                        Log.e("alp", "coin: " + coin.name)
                    }

                }
            }

            override fun onFailure(call: Call<MarketModel>?, t: Throwable?) {

                Log.e("alppppp", "Failed::" + (t))
            }

        })
    }
}