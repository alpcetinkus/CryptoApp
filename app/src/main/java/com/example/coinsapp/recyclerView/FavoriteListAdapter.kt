package com.example.coinsapp.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinsapp.R
import kotlinx.android.synthetic.main.coin_list_card.view.*
import kotlinx.android.synthetic.main.fav_card.view.*
import kotlin.collections.ArrayList



class FavoriteListAdapter (private var mContext: Context, private val items: ArrayList<String>)
    : RecyclerView.Adapter<FavoriteListAdapter.CardFavListDesign>() {


    class CardFavListDesign(view: View) : RecyclerView.ViewHolder(view) {



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardFavListDesign {
        val design = LayoutInflater.from(mContext).inflate(R.layout.fav_card, parent, false)
        return CardFavListDesign(design)
    }

    override fun onBindViewHolder(holder: CardFavListDesign, position: Int) {

        holder.itemView.FavName.text = items.elementAt(position)


    }



    override fun getItemCount(): Int {
      return items.size
    }


}