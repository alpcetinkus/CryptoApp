package com.example.coinsapp.recyclerView

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinsapp.R
import java.util.*
import kotlin.collections.ArrayList


class CoinListAdapter (private var mContext: Context, private val coinlist: ArrayList<CoinList>, )
    : RecyclerView.Adapter<CoinListAdapter.CardCoinListDesign>(), Filterable {

    var coinFilterList = ArrayList<CoinList>()



    inner class CardCoinListDesign(view: View) : RecyclerView.ViewHolder(view) {

        var coinListCardView: CardView
        var coinSymbol: TextView
        var coinName: TextView
        var coinPrice: TextView
        var coinChange: TextView
        var coinLogo: ImageView

        init {

            coinFilterList = coinlist
            coinListCardView = view.findViewById(R.id.alp)
            coinSymbol = view.findViewById(R.id.CoinSymbol)
            coinName = view.findViewById(R.id.CoinNamee)
            coinPrice = view.findViewById(R.id.CoinPrice)
            coinChange = view.findViewById(R.id.CoinChange)
            coinLogo = view.findViewById(R.id.CoinLogo)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardCoinListDesign {
        val design = LayoutInflater.from(mContext).inflate(R.layout.coin_list_card, parent, false)
        return CardCoinListDesign(design)
    }

    override fun onBindViewHolder(holder: CardCoinListDesign, position: Int) {
        val coin = coinlist[position]
        Glide.with(mContext).load(coin.CoinImg).into(holder.coinLogo)
        holder.coinName.text = coin.CoinName
        holder.coinSymbol.text = coin.CoinSymbol
        holder.coinPrice.text = coin.CoinPrice
        holder.coinChange.text = coin.CoinChange
        holder.coinListCardView.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_StatisticFragment_to_deatailFragment)
        }
    }

    override fun getItemCount(): Int {
      return coinlist.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSearch = p0.toString()
                if (charSearch.isEmpty()) {
                    coinFilterList = coinlist
                } else {
                    val resultList = ArrayList<CoinList>()
                    for (row in coinlist) {
                        if (row.CoinName.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    coinFilterList = resultList

                }
                val filterResult = FilterResults()
                filterResult.values = coinFilterList
                return filterResult
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                coinFilterList = results?.values as ArrayList<CoinList>
                notifyDataSetChanged()

            }
        }
    }
}