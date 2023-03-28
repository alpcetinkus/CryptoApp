package com.example.coinsapp.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.os.IResultReceiver.Default
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.coinsapp.R
import com.example.coinsapp.adapter.MarketAdapter
import com.example.coinsapp.model.CryptoCurrency
import com.example.coinsapp.model.MarketModel
import com.example.coinsapp.service.CryptoApi
import com.example.coinsapp.service.RetrofitClient
import kotlinx.android.synthetic.main.fragment_market.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.Locale.filter
import kotlin.collections.ArrayList

class MarketFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var currencyRecyclerView : RecyclerView
    private lateinit var marketList: MutableList<CryptoCurrency>
    private lateinit var marketAdapter: MarketAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_market, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencyRecyclerView = view?.findViewById(R.id.currencyRecyclerView)!!
        marketList = ArrayList()
        marketAdapter = MarketAdapter(mContext, "market", marketList)
        currencyRecyclerView.adapter = marketAdapter
        fetchMarketData()
        searchCoin()

    }

    fun fetchMarketData() {
        RetrofitClient.getApiImplementation().getMarketData().enqueue(object :
            Callback<MarketModel> {
            override fun onResponse(call: Call<MarketModel>?, response: Response<MarketModel>?) {

                if (response != null) {
                    var responseBody = response.body()?.data?.cryptoCurrencyList
                    for (coin in responseBody!!) {
                        marketList.add(coin)
                    }
                }
                marketAdapter.notifyDataSetChanged()

            }
            override fun onFailure(call: Call<MarketModel>?, t: Throwable?) {

                Log.e("alppppp", "Failed::" + (t))
            }
        })
    }
lateinit var searchText: String
    private fun searchCoin() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                searchText = s.toString().lowercase()
                updateRecyclerView()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun updateRecyclerView() {
        val data = ArrayList<CryptoCurrency>()
        for (coin in marketList) {
            val coinName = coin.name.lowercase(Locale.getDefault())
            val coinSymbol = coin.symbol.lowercase(Locale.getDefault())

            if (coinName.contains(searchText) || coinSymbol.contains(searchText)) {
                data.add(coin)
            }

        }
        marketAdapter.updateData(data)
    }
}