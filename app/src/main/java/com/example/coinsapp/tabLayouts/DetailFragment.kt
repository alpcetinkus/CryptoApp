package com.example.coinsapp.tabLayouts

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.coinsapp.R
import com.example.coinsapp.recyclerView.*
import com.example.coinsapp.retrofit.RetrofitClient
import com.example.coinsapp.retrofit.models2.CoinDetail

import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailFragment : Fragment() {

    private lateinit var mContext: Context

    private lateinit var detailAdapter: DetailAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("zzzzzzzzzzzz", "sorgu atiyorum")

        back_btn.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val coinId = arguments?.getString("coinIdKey")


        fetchDetail(coinId)

    }

    fun fetchDetail(id: String?) {

        id?.let {
            RetrofitClient.getApiImplementation().getDetails(it).enqueue(object : Callback<CoinDetail> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<CoinDetail>?, response: Response<CoinDetail>?) {

                    Log.e("alppppppppp", "$id secildi")

                    if (response != null) {
                        var resBody = response.body()
                        detailName.text = resBody.name
                        Glide.with(mContext).load(resBody.image.small).into(CoinLogo)
                        detailPrice.text = "Current Price: $ ${resBody.marketData.currentPrice.usd}"
                        market_cap_tv.text = "Market Cap: ${resBody.marketData.marketCap.usd}"
                        high24_tv.text = "High 24: ${resBody.marketData.high24h.usd}"
                        low24_tv.text = "Low 24: ${resBody.marketData.low24h.usd}"
                        price_change_24h_tv.text = "Price Change 24: ${resBody.marketData.priceChange24h}"
                        val neg = 0
                        if (resBody.marketData.priceChange24h >= neg) {
                            Glide.with(mContext).load(R.drawable.ic_baseline_trending_up).into(indicator)
                        } else {
                            Glide.with(mContext).load(R.drawable.ic_baseline_trending_down_24).into(indicator)
                        }
                    }
                }

                override fun onFailure(call: Call<CoinDetail>?, t: Throwable?) {

                    Log.e("alppppp", "Failed::" + (t))
                }
            })
        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


}
