package com.example.coinsapp.tabLayouts

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.ToggleButton
import androidx.core.view.isGone
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.coinsapp.R
import com.example.coinsapp.recyclerView.CoinHomeList
import com.example.coinsapp.recyclerView.CoinList
import com.example.coinsapp.recyclerView.CoinListAdapter
import com.example.coinsapp.recyclerView.HomeAdapter
import com.example.coinsapp.retrofit.RetrofitClient
import com.example.coinsapp.retrofit.models.MarketModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_statistic.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var coinHomeList: ArrayList<CoinHomeList>
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_home_list.setHasFixedSize(true)
        rv_home_list.layoutManager = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL)
        coinHomeList = ArrayList()
        homeAdapter = HomeAdapter(mContext,coinHomeList)
        rv_home_list.adapter = homeAdapter

        homelist_seeAll_TextView.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.StatisticFragment)
        }

        fetchMarketList()

    }

    fun fetchMarketList() {

       progressBar.visibility = View.VISIBLE
        RetrofitClient.getApiImplementation().getCoinListMarket().enqueue(object : Callback<MarketModel> {
            override fun onResponse(call: Call<MarketModel>?, response: Response<MarketModel>?) {

                if (response != null) {
                    var responseBody = response.body()
                    for (coin in responseBody) {
                        coinHomeList.add(CoinHomeList(coin.image,coin.name,coin.symbol,coin.id,coin.priceChange24h))
                    }
                }
                homeAdapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE

            }
            override fun onFailure(call: Call<MarketModel>?, t: Throwable?) {

                Log.e("alppppp", "Failed::" + (t))
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }




}