package com.example.coinsapp.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinsapp.R
import kotlinx.android.synthetic.main.coin_list_card.view.*

class CoinListAdapter (private val mContext: Context, private val coinlist: List<CoinList>)
    : RecyclerView.Adapter<CoinListAdapter.CardCoinListDesign>(){

        inner class CardCoinListDesign(view: View): RecyclerView.ViewHolder(view){

            var coinListCardView :CardView
            var coinSymbol: TextView
            var coinName: TextView
            var coinPrice: TextView
            var coinChange: TextView
            var coinLogo: ImageView

            init {

                coinListCardView = view.findViewById(R.id.alp)
                coinSymbol = view.findViewById(R.id.CoinSymbol)
                coinName = view.findViewById(R.id.CoinNamee)
                coinPrice = view.findViewById(R.id.CoinPrice)
                coinChange = view.findViewById(R.id.CoinChange)
                coinLogo = view.findViewById(R.id.CoinLogo)

            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardCoinListDesign {
        val design = LayoutInflater.from(mContext).inflate(R.layout.coin_list_card,parent,false)
        return CardCoinListDesign(design)
    }

    override fun onBindViewHolder(holder: CardCoinListDesign, position: Int) {
        val coin = coinlist[position]
        Glide.with(mContext).load(coin.CoinImg).into(holder.coinLogo)
        holder.coinName.text = coin.CoinName
        holder.coinSymbol.text = coin.CoinSymbol
        holder.coinPrice.text = coin.CoinPrice.toString()
        holder.coinChange.text = coin.CoinChange.toString()

    }

    override fun getItemCount(): Int {
        return coinlist.size
    }

}