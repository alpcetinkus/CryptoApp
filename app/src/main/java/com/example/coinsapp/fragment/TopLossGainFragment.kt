package com.example.coinsapp.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coinsapp.R
import com.example.coinsapp.adapter.MarketAdapter
import com.example.coinsapp.adapter.TopLossGainPagerAdapter
import com.example.coinsapp.model.CryptoCurrency
import com.example.coinsapp.model.MarketModel
import com.example.coinsapp.service.RetrofitClient
import kotlinx.android.synthetic.main.fragment_top_loss_gain.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopLossGainFragment : Fragment() {

    private lateinit var mContext: Context
    private lateinit var marketList: MutableList<CryptoCurrency>
    private lateinit var marketAdapter: MarketAdapter
    private lateinit var topLossRecyclerView : RecyclerView


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_loss_gain, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topLossRecyclerView = view?.findViewById(R.id.topGainLoseRecyclerView)!!
        marketList = ArrayList()
        marketAdapter = MarketAdapter(mContext,marketList)
        topLossRecyclerView.adapter = marketAdapter

        fetchMarketData()

    }

    fun fetchMarketData() {
        RetrofitClient.getApiImplementation().getMarketData().enqueue(object :
            Callback<MarketModel> {
            override fun onResponse(call: Call<MarketModel>?, response: Response<MarketModel>?) {

                if (response != null) {
                    var responseBody = response.body()?.data?.cryptoCurrencyList
                    for (coin in responseBody!!) {
                        marketList.add(coin)
                    }

                }
                marketAdapter.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<MarketModel>?, t: Throwable?) {

                Log.e("alppppp", "Failed::" + (t))
            }
        })
    }
}