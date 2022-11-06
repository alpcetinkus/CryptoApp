package com.example.coinsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.coinsapp.recyclerView.CoinList
import com.example.coinsapp.recyclerView.CoinListAdapter
import com.example.coinsapp.retrofit.RetrofitClient
import com.example.coinsapp.retrofit.models.MarketModel
import com.example.coinsapp.tabLayouts.AccountFragment
import com.example.coinsapp.tabLayouts.BookFragment
import com.example.coinsapp.tabLayouts.HomeFragment
import com.example.coinsapp.tabLayouts.StatisticFragment
import com.example.coinsapp.tabLayouts.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_book.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpTabs()




    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), "")
        adapter.addFragment(StatisticFragment(), "")
        adapter.addFragment(BookFragment(), "")
        adapter.addFragment(AccountFragment(), "")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_home)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_statistic)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_book)
        tabs.getTabAt(3)!!.setIcon(R.drawable.ic_account)
    }


}