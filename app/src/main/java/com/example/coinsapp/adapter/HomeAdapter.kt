package com.example.coinsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinsapp.R
import com.example.coinsapp.model.models.MarketModelItem
import kotlinx.android.synthetic.main.home_list_card.view.*

class HomeAdapter(private var mContext: Context, private val coinhomelist: ArrayList<MarketModelItem>)
    : RecyclerView.Adapter<HomeAdapter.homeListDesign>()  {

   inner class homeListDesign(view: View) : RecyclerView.ViewHolder(view) {



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): homeListDesign {
        val design = LayoutInflater.from(mContext).inflate(R.layout.home_list_card,parent,false)
        return homeListDesign(design)
    }

    override fun onBindViewHolder(holder: homeListDesign, position: Int) {
        val coin = coinhomelist[position]
        Glide.with(mContext).load(coin.image).into(holder.itemView.CoinLogo)
        val neg = 0
        if (coin.price_change_24h >= neg) {
            Glide.with(mContext).load(R.drawable.ic_baseline_trending_up).into(holder.itemView.indicator_up)
        } else {
            Glide.with(mContext).load(R.drawable.ic_baseline_trending_down_24).into(holder.itemView.indicator_up)
        }


        holder.itemView.CoinNamee.text = coin.name
        holder.itemView.CoinSymbol.text = coin.symbol
    }

    override fun getItemCount(): Int {
        return coinhomelist.take(8).size
    }



}