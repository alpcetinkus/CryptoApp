package com.example.coinsapp.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.coinsapp.R
import com.example.coinsapp.adapter.*
import com.example.coinsapp.model.RetrofitClient
import com.example.coinsapp.model.models.MarketModel
import com.example.coinsapp.model.models.MarketModelItem
import kotlinx.android.synthetic.main.fragment_statistic.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList


class StatisticFragment : Fragment(), DetailClickInterface {

    private lateinit var mContext: Context
    private lateinit var coinList: ArrayList<MarketModelItem>
    private lateinit var coinListAdapter: CoinListAdapter
    private lateinit var coinrv: RecyclerView

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
        coinListAdapter = CoinListAdapter(mContext, coinList, this)

        rv_list.adapter = coinListAdapter
        Log.e("alp", "sorgu atiyorum")
        coinrv = rv_list
        coinrv.layoutManager = LinearLayoutManager(coinrv.context)
        coinrv.setHasFixedSize(true)
        val decorator = DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL)
        coinrv.addItemDecoration(decorator)

        fetchMarketList()

       positiveBtn.setOnClickListener {
           fetchMarketList()
            val positiveCoins = coinList.filter {
                it.price_change_24h >= 0
            }
           updateData(positiveCoins)

        }

        negativeBtn.setOnClickListener {
            fetchMarketList()
            val negativeCoins = coinList.filter {
                it.price_change_24h < 0
            }
            updateData(negativeCoins)
        }

        turnAllCoins.setOnClickListener {
            coinList.clear()
            fetchMarketList()
            coinListAdapter.notifyDataSetChanged()
        }

        toSort.setOnClickListener {
            fetchMarketList()
            val lowTo = coinList.sortedBy {
                it.price_change_24h
            }
            updateData(lowTo)
        }

        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.setQuery("", false)
                searchView.clearFocus()
                updateData(coinList)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                coinListAdapter.filter.filter(newText)
                return true
            }
        })
    }

    fun updateData(newData: List<MarketModelItem>) {
        coinList.clear()
        coinList.addAll(newData)
        coinListAdapter.notifyDataSetChanged()
    }

    fun fetchMarketList() {
        progressBar2.visibility = View.VISIBLE
        RetrofitClient.getApiImplementation().getCoinListMarket().enqueue(object : Callback<MarketModel> {
            override fun onResponse(call: Call<MarketModel>?, response: Response<MarketModel>?) {
                if (response != null) {
                    val responseBody = response.body()
                    for (coin in responseBody) {
                        coinList.add(coin)  }
                }
                coinListAdapter.notifyDataSetChanged()
                progressBar2.visibility = View.GONE
            }

            override fun onFailure(call: Call<MarketModel>?, t: Throwable?) {
                Log.e("alppppp", "Failed::" + (t))
            }
        })
    }

    override fun onDetailClick(id: String) {
        RetrofitClient.getApiImplementation().getDetails(id).enqueue(object : Callback<MarketModel> {
            override fun onResponse(call: Call<MarketModel>?, response: Response<MarketModel>?) {
                Log.e("alppppppppp", "$id secildi")

                if (response != null) {
                    Log.e("sorgu", "detay")
                }
            }

            override fun onFailure(call: Call<MarketModel>?, t: Throwable?) {
                Log.e("alppppp", "Failed::" + (t))
            }
        })
    }
}