package com.example.coinsapp.tabLayouts

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.coinsapp.R
import com.example.coinsapp.recyclerView.CoinList
import com.example.coinsapp.recyclerView.CoinListAdapter
import com.example.coinsapp.retrofit.RetrofitClient
import com.example.coinsapp.retrofit.models.MarketModel
import kotlinx.android.synthetic.main.coin_list_card.*
import kotlinx.android.synthetic.main.coin_list_card.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_statistic.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList


class StatisticFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var coinList: ArrayList<CoinList>
    private lateinit var coinListAdapter: CoinListAdapter
    private lateinit var coinrv: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val design = inflater.inflate(R.layout.fragment_statistic, container, false)
        return design
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_list.setHasFixedSize(true)
        rv_list.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        coinList = ArrayList()
        coinListAdapter = CoinListAdapter(mContext, coinList)
        rv_list.adapter = coinListAdapter

        Log.e("alp","sorgu atiyorum")

        coinrv = rv_list
        coinrv.layoutManager = LinearLayoutManager(coinrv.context)
        coinrv.setHasFixedSize(true)
        val decorator = DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL)
        coinrv.addItemDecoration(decorator)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                coinListAdapter.filter.filter(newText)
                return false
            }
        })

        fetchMarketList()

    }

    fun fetchMarketList() {
        RetrofitClient.getApiImplementation().getCoinListMarket().enqueue(object : Callback<MarketModel> {
            override fun onResponse(call: Call<MarketModel>?, response: Response<MarketModel>?) {

                if (response != null) {
                    var responseBody = response.body()
                    for (coin in responseBody) {
                        coinList.add(CoinList(coin.image, coin.name, coin.symbol, coin.currentPrice.toString(), coin.atl.toString()))
                    }
                }
                coinListAdapter.notifyDataSetChanged()
                stopProgressBar2()

            }
            override fun onFailure(call: Call<MarketModel>?, t: Throwable?) {

                Log.e("alppppp", "Failed::" + (t))
            }
        })

    }

    fun stopProgressBar2() {
        progressBar2.visibility = ProgressBar.GONE
    }

}