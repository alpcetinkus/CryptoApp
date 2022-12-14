package com.example.coinsapp.tabLayouts

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.coinsapp.R
import com.example.coinsapp.recyclerView.CoinList
import com.example.coinsapp.recyclerView.CoinListAdapter
import com.example.coinsapp.recyclerView.FavoriteListAdapter
import kotlinx.android.synthetic.main.fragment_book.*


class BookFragment : Fragment() {

    private lateinit var mContext: Context
    private lateinit var favAdapter: FavoriteListAdapter
    private lateinit var myFavoriteList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        val list = retrieveFavoriteList()
        if (list != null) {
            myFavoriteList.clear()
            myFavoriteList.addAll(list)
        }
       favAdapter.notifyDataSetChanged()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fav_rv.setHasFixedSize(true)
        fav_rv.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        myFavoriteList = ArrayList()
        favAdapter = FavoriteListAdapter(mContext, myFavoriteList)
        fav_rv.adapter = favAdapter


    }



    fun retrieveFavoriteList() : MutableSet<String>? {
        val sp = activity?.getSharedPreferences(CoinListAdapter.spKey,Context.MODE_PRIVATE)
        val list = sp?.getStringSet("CoinFavName",null)

        return list
    }



}