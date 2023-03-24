package com.example.coinsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinsapp.R
import com.example.coinsapp.model.CryptoCurrency
import kotlinx.android.synthetic.main.top_currency_layout.view.*


class HomeAdapter(private var mContext: Context, private val coinhomelist: List<CryptoCurrency>)
    : RecyclerView.Adapter<HomeAdapter.homeListDesign>()  {

   class homeListDesign(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): homeListDesign {
        val design = LayoutInflater.from(mContext).inflate(R.layout.top_currency_layout,parent,false)
        return homeListDesign(design)
    }

    override fun onBindViewHolder(holder: homeListDesign, position: Int) {
        val coin = coinhomelist[position]
        Glide.with(mContext).load("http://s2.coinmarketcap.com/static/img/coins/64x64/" + coin.id + ".png")
            .into(holder.itemView.topCurrencyImageView)

        if (coin.quotes!![0].percentChange24h > 0) {
            holder.itemView.topCurrencyChangeTextView.setTextColor(mContext.resources.getColor(R.color.green))
            holder.itemView.topCurrencyChangeTextView.text = "+ ${String.format("%.02f", coin.quotes[0].percentChange24h)} %"
        } else {
            holder.itemView.topCurrencyChangeTextView.setTextColor(mContext.resources.getColor(R.color.red))
            holder.itemView.topCurrencyChangeTextView.text = "${String.format("%.02f" , coin.quotes[0].percentChange24h)} %"
        }

        holder.itemView.topCurrencyNameTextView.text = coin.name
    }

    override fun getItemCount(): Int {
        return coinhomelist.take(8).size
    }



}