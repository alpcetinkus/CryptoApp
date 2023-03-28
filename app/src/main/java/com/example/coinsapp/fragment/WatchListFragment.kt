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
import com.example.coinsapp.model.CryptoCurrency
import com.example.coinsapp.model.MarketModel
import com.example.coinsapp.service.RetrofitClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WatchListFragment : Fragment() {
    private lateinit var watchList: ArrayList<String>
    private lateinit var watchListItem: ArrayList<CryptoCurrency>
    private lateinit var watchlistRecyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_watch_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        watchlistRecyclerView = view?.findViewById(R.id.watchlistRecyclerView)!!
        readData()
        fetchWatchList()
    }

    fun fetchWatchList() {
        RetrofitClient.getApiImplementation().getMarketData().enqueue(object :
            Callback<MarketModel> {
            override fun onResponse(call: Call<MarketModel>?, response: Response<MarketModel>?) {

                if (response != null) {
                    watchListItem = ArrayList()
                    watchListItem.clear()

                    for (watchData in watchList) {
                        for (data in response.body()?.data?.cryptoCurrencyList!!) {
                            if (data.symbol == watchData) {
                                watchListItem.add(data)
                            }
                        }
                    }
                    watchlistRecyclerView.adapter = MarketAdapter(requireContext(), "watchlistfragment", watchListItem)
                }
            }

            override fun onFailure(call: Call<MarketModel>?, t: Throwable?) {

                Log.e("alppppp", "Failed::" + (t))
            }
        })
    }
    private fun readData() {
        val sharedPreferences = requireActivity().getSharedPreferences("watchlist", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("watchlist", ArrayList<String>().toString())
        val type = object : TypeToken<ArrayList<String>>() {}.type
        watchList = gson.fromJson(json, type)
    }
}