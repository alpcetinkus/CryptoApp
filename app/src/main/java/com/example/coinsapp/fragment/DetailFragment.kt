package com.example.coinsapp.fragment


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coinsapp.R
import com.example.coinsapp.adapter.*
import com.example.coinsapp.model.RetrofitClient
import com.example.coinsapp.model.models.MarketModel
import com.example.coinsapp.model.models.MarketModelItem

import kotlinx.android.synthetic.main.fragment_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailFragment : Fragment() {

    private lateinit var mContext: Context
private lateinit var detailList: ArrayList<MarketModelItem>
    private lateinit var detailAdapter: DetailAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("zzzzzzzzzzzz", "sorgu atiyorum")

        back_btn.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val coinId = arguments?.getString("coinIdKey")

        fetchDetail(coinId)

    }

    fun fetchDetail(id: String?) {

        id?.let {
            RetrofitClient.getApiImplementation().getDetails(it).enqueue(object : Callback<MarketModel> {
                override fun onResponse(call: Call<MarketModel>?, response: Response<MarketModel>?) {

                    Log.e("alppppppppp", "$id secildi")
                    if (response != null) {
                        var resBody = response.body()
                        for (coin in resBody) {
                            detailList.add(coin)
                        }
                        detailAdapter.notifyDataSetChanged()

                    }
                }

                override fun onFailure(call: Call<MarketModel>?, t: Throwable?) {

                    Log.e("alppppp", "Failed::" + (t))
                }
            })
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }
}
