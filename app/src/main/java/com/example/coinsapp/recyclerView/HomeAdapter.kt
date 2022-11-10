package com.example.coinsapp.recyclerView

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinsapp.R

class HomeAdapter(private var mContext: Context, private val coinhomelist: ArrayList<CoinHomeList>)
    : RecyclerView.Adapter<HomeAdapter.homeListDesign>()  {

   inner class homeListDesign(view: View) : RecyclerView.ViewHolder(view) {

       var homeListCard: CardView
       var coinImg: ImageView
       var coinSymbol: TextView
       var coinName: TextView
       var indicator1: ImageView




       init {
           homeListCard = view.findViewById(R.id.homeCardView)
           coinSymbol = view.findViewById(R.id.CoinSymbol)
           coinName = view.findViewById(R.id.CoinNamee)
           coinImg = view.findViewById(R.id.CoinLogo)
           indicator1 = view.findViewById(R.id.indicator_up)



       }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): homeListDesign {
        val design = LayoutInflater.from(mContext).inflate(R.layout.home_list_card,parent,false)
        return homeListDesign(design)
    }

    override fun onBindViewHolder(holder: homeListDesign, position: Int) {
        val coin = coinhomelist[position]
        Glide.with(mContext).load(coin.image).into(holder.coinImg)
        val neg = 0
        if (coin.priceChange >= neg) {
            Glide.with(mContext).load(R.drawable.ic_baseline_trending_up).into(holder.indicator1)
        } else {
            Glide.with(mContext).load(R.drawable.ic_baseline_trending_down_24).into(holder.indicator1)
        }


        holder.coinName.text = coin.name
        holder.coinSymbol.text = coin.coinSymbol

    }

    override fun getItemCount(): Int {
        return coinhomelist.size
    }


}