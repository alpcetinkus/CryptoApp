package com.example.coinsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinsapp.R
import com.example.coinsapp.model.CryptoCurrency
import kotlinx.android.synthetic.main.currency_item_card.view.*
import kotlinx.android.synthetic.main.top_currency_layout.view.*


class MarketAdapter(private var mContext: Context, private val marketlist: List<CryptoCurrency>)
    : RecyclerView.Adapter<MarketAdapter.marketListDesign>()  {

   class marketListDesign(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): marketListDesign {
        val design = LayoutInflater.from(mContext).inflate(R.layout.currency_item_card,parent,false)
        return marketListDesign(design)
    }

    override fun onBindViewHolder(holder: marketListDesign, position: Int) {
        val coin = marketlist[position]
        Glide.with(mContext).load("http://s2.coinmarketcap.com/static/img/coins/64x64/" + coin.id + ".png")
            .thumbnail(Glide.with(mContext).load(R.drawable.spinner))
            .into(holder.itemView.currencyImageView)

        Glide.with(mContext).load("http://s3.coinmarketcap.com/generated/sparklines/web/7d/usd/" + coin.id + ".png")
            .thumbnail(Glide.with(mContext).load(R.drawable.spinner))
            .into(holder.itemView.currencyChartImageView)

        if (coin.quotes!![0].percentChange24h > 0) {
            holder.itemView.currencyChangeTextView.setTextColor(mContext.resources.getColor(R.color.green))
            holder.itemView.currencyChangeTextView.text = "+ ${String.format("%.02f", coin.quotes[0].percentChange24h)} %"
        } else {
            holder.itemView.currencyChangeTextView.setTextColor(mContext.resources.getColor(R.color.red))
            holder.itemView.currencyChangeTextView.text = "${String.format("%.02f" , coin.quotes[0].percentChange24h)} %"
        }

        holder.itemView.currencyNameTextView.text = coin.name
        holder.itemView.currencySymbolTextView.text = coin.symbol
        holder.itemView.currencyPriceTextView.text = "${String.format("$%.02f" , coin.quotes[0].price)}"


    }

    override fun getItemCount(): Int {
        return marketlist.size
    }



}