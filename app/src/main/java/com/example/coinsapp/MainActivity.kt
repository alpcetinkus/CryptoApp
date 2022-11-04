package com.example.coinsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.coinsapp.retrofit.RetrofitClient
import com.example.coinsapp.retrofit.models.MarketModel
import com.example.coinsapp.tabLayouts.FragmentAccount
import com.example.coinsapp.tabLayouts.FragmentHome
import com.example.coinsapp.tabLayouts.FragmentStatistic
import com.example.coinsapp.tabLayouts.FragmentTwo
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val fragmentList = ArrayList<Fragment>()
    private val fragmenTagtList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentList.add(FragmentAccount())
        fragmentList.add(FragmentTwo())
        fragmentList.add(FragmentStatistic())
        fragmentList.add(FragmentHome())

        val adapter = MyViewPagerAdapter(this)
        viewPager2.adapter = adapter

        fragmenTagtList.add("One")
        fragmenTagtList.add("Two")
        fragmenTagtList.add("Three")
        fragmenTagtList.add("four")

        TabLayoutMediator(tabLayout,viewPager2){ tab, position ->
            tab.setText(fragmenTagtList[position])
        }.attach()

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

    inner class MyViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
        override fun getItemCount(): Int {

            return fragmentList.size

        }

        override fun createFragment(position: Int): Fragment {

            return fragmentList[position]

        }

    }
}