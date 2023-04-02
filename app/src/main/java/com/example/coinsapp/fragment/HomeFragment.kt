package com.example.coinsapp.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.coinsapp.R
import com.example.coinsapp.adapter.HomeAdapter
import com.example.coinsapp.adapter.TopLoseGainPagerAdapter
import com.example.coinsapp.model.CryptoCurrency
import com.example.coinsapp.model.MarketModel
import com.example.coinsapp.service.RetrofitClient
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var coinHomeList: MutableList<CryptoCurrency>
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var topCurrencyRecyclerView : RecyclerView
    private lateinit var contentViewPager: ViewPager2
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topCurrencyRecyclerView = view.findViewById(R.id.topCurrencyRecyclerView)
        contentViewPager = view.findViewById(R.id.contentViewPager)
        coinHomeList = ArrayList()
        homeAdapter = HomeAdapter(mContext,coinHomeList)
        topCurrencyRecyclerView.adapter = homeAdapter

        fetchMarketList()
        setTabLayout()
    }
    fun fetchMarketList() {
        RetrofitClient.getApiImplementation().getMarketData().enqueue(object : Callback<MarketModel> {
            override fun onResponse(call: Call<MarketModel>?, response: Response<MarketModel>?) {

                if (response != null) {
                    var responseBody = response.body()?.data?.cryptoCurrencyList
                    for (coin in responseBody!!) {
                        coinHomeList.add(coin)
                    }
                }
                homeAdapter.notifyDataSetChanged()
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
    fun setTabLayout() {

        val adapter = TopLoseGainPagerAdapter(this)
        contentViewPager.adapter = adapter
        contentViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == 0) {
                    topGainIndicator.visibility = View.VISIBLE
                    topLoseIndicator.visibility = View.GONE
                } else {
                    topGainIndicator.visibility = View.GONE
                    topLoseIndicator.visibility = View.VISIBLE
                }
            }
        })
        TabLayoutMediator(tabLayout, contentViewPager) { tab, position ->
            var title = if (position == 0) {
                "GAINS"
            } else {
                "LOSERS"
            }
            tab.text = title
        }.attach()
    }
}
