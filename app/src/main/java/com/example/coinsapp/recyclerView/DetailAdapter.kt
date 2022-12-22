package com.example.coinsapp.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinsapp.R
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.fragment_detail.view.CoinLogo
import kotlinx.android.synthetic.main.home_list_card.view.*

class DetailAdapter (private var mContext: Context, private val detailList: ArrayList<CoinDetails>)
    : RecyclerView.Adapter<DetailAdapter.CardDetailDesign>() {

    inner class CardDetailDesign(view: View) : RecyclerView.ViewHolder(view) {

    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDetailDesign {
            val design = LayoutInflater.from(mContext).inflate(R.layout.fragment_detail, parent, false)
            return CardDetailDesign(design)
        }

        override fun onBindViewHolder(holder: CardDetailDesign, position: Int) {
            val detail = detailList[position]
            val neg = 0
            Glide.with(mContext).load(detail.image).into(holder.itemView.CoinLogo)
            holder.itemView.detailName.text = detail.name
            holder.itemView.detailSymbol.text = detail.description
            holder.itemView.market_cap_tv.text = detail.market_cap.toString()
            holder.itemView.high24_tv.text = detail.high_price.toString()
            holder.itemView.low24_tv.text = detail.low_price.toString()
            holder.itemView.price_change_24h_tv.text = detail.price_percentage_change.toString()

            holder.itemView.detailPrice.text = detail.marketData.currentPrice.toString()
            if (detail.marketData.priceChange24h >= neg) {
                Glide.with(mContext).load(R.drawable.ic_baseline_trending_up).into(holder.itemView.indicator)
            } else {
                Glide.with(mContext).load(R.drawable.ic_baseline_trending_down_24).into(holder.itemView.indicator)
            }



        }

        override fun getItemCount(): Int {
            return detailList.size
        }
    }
