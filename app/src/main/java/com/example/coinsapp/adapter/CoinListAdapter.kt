package com.example.coinsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinsapp.R
import com.example.coinsapp.model.models.MarketModelItem
import kotlinx.android.synthetic.main.coin_list_card.view.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

interface DetailClickInterface {
    fun onDetailClick(id: String)
}

class CoinListAdapter (private var mContext: Context, private var coinlist: ArrayList<MarketModelItem>, val clickListener: DetailClickInterface  )
    : RecyclerView.Adapter<CoinListAdapter.CardCoinListDesign>(), Filterable {
     val data = coinlist


    class CardCoinListDesign(view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardCoinListDesign {
        val design = LayoutInflater.from(mContext).inflate(R.layout.coin_list_card, parent, false)
        return CardCoinListDesign(design)
    }

    override fun onBindViewHolder(holder: CardCoinListDesign, position: Int) {
        val coin = coinlist[position]
        Glide.with(mContext).load(coin.image).into(holder.itemView.CoinLogo)
        holder.itemView.CoinNameStatistic.text = coin.name
        holder.itemView.CoinSymbol.text = coin.symbol
        holder.itemView.CoinPrice.text = "%.3f".format(coin.current_price)
        holder.itemView.CoinChange.text = "%.3f".format(coin.price_change_24h)
        holder.itemView.favBtn.isChecked = didItemInFavoriteList(coin.name)

        holder.itemView.favBtn.setOnClickListener {
            addToMyFavoriteList("${holder.itemView.CoinNameStatistic.text}")
            removeToFavoriteList("${holder.itemView.CoinNameStatistic.text}")
        }

        holder.itemView.setOnClickListener {
            val bundle = bundleOf("coinIdKey" to coin.id)
            Navigation.findNavController(it).navigate(R.id.action_StatisticFragment_to_deatailFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return coinlist.size
    }

    companion object {
        const val spKey = "fav_list"
    }

    fun persistFavoriteList(favNames: MutableSet<String>?) {
        val sp = mContext?.getSharedPreferences(Companion.spKey, Context.MODE_PRIVATE)
        val editor = sp?.edit()
        editor?.putStringSet("CoinFavName", favNames)
        editor?.apply()
    }

    fun addToMyFavoriteList(coinName: String) {
        val list = retrieveFavoriteList()
        if (list?.contains(coinName) == false) {
            list.add(coinName)
        }
        persistFavoriteList(list)
    }

    fun didItemInFavoriteList(coinName: String): Boolean {
        val list = retrieveFavoriteList()
        val contains = list?.contains(coinName)
        return contains == true

    }

    fun removeToFavoriteList(coinName: String) {
        val list = retrieveFavoriteList()
        if (list?.contains(coinName) == true) {
            list.remove(coinName)
        }
        persistFavoriteList(list)

    }

    fun retrieveFavoriteList(): MutableSet<String>? {
        val sp = mContext?.getSharedPreferences(Companion.spKey, Context.MODE_PRIVATE)
        val list = sp?.getStringSet("CoinFavName", HashSet<String>())
        return list
    }

override fun getFilter(): Filter {
    return object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = ArrayList<MarketModelItem>()
            if (constraint.isNullOrEmpty()) {
                filteredList.addAll(data)
            } else {
                val filterPattern = constraint.toString().lowercase(Locale.ROOT).trim()
                for (item in data) {
                    if (item.name.lowercase(Locale.ROOT).contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            coinlist.clear()
            coinlist.addAll(results?.values as ArrayList<MarketModelItem>)
            notifyDataSetChanged()
        }
    }
}
}


