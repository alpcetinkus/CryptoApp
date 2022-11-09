package com.example.coinsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.coinsapp.tabLayouts.AccountFragment
import com.example.coinsapp.tabLayouts.StatisticFragment
import com.example.coinsapp.tabLayouts.HomeFragment
import com.example.coinsapp.tabLayouts.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.coin_list_card.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNav,navHostFragment.navController)



    }




}