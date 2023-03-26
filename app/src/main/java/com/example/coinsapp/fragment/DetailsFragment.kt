package com.example.coinsapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.coinsapp.R
import com.example.coinsapp.model.CryptoCurrency
import kotlinx.android.synthetic.main.currency_item_card.view.*
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    private val item: DetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val data: CryptoCurrency = item.data!!
        setUpDetails(data)
        loadChart(data)
        setButtonOnClick(data)
        return inflater.inflate(R.layout.fragment_details, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    fun setUpDetails(data: CryptoCurrency) {
        detailSymbolTextView.text = data.symbol

        Glide.with(requireContext())
            .load("http://s2.coinmarketcap.com/static/img/coins/64x64/" + data.id + ".png")
            .thumbnail(Glide.with(requireContext()).load(R.drawable.spinner))
            .into(detailImageView)

        detailPriceTextView.text = "${String.format("$.4f", data.quotes[0].price)}"

        if (data.quotes!![0].percentChange24h > 0) {
            detailChangeTextView.setTextColor(requireContext().resources.getColor(R.color.green))
            detailChangeTextView.text =
                "+ ${String.format("%.02f", data.quotes[0].percentChange24h)} %"
        } else {
            detailChangeTextView.setTextColor(requireContext().resources.getColor(R.color.red))
            detailChangeTextView.text =
                "${String.format("%.02f", data.quotes[0].percentChange24h)} %"
        }

    }

    private fun loadChart(data: CryptoCurrency) {
        detaillChartWebView.settings.javaScriptEnabled = true
        detaillChartWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)

        detaillChartWebView.loadUrl(
            "https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol=" + data.symbol
                .toString() + "USD&interval=D&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1&toolbarbg=" +
                    "F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&overrides={}&enabled_features=" +
        "[]&disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget&utm_campaign=chart&utm_term=BTCUSDT"
        )
    }

    private fun setButtonOnClick(data : CryptoCurrency) {

        val oneMonth = button
        val oneWeek = button1
        val oneDay = button2
        val fourHour = button3
        val oneHour = button4
        val fifteenMinute = button5

        val clickListener = View.OnClickListener {
            when(it.id) {
                fifteenMinute.id -> loadChartData(it, "15", data, oneDay, oneMonth, oneHour, oneWeek, fourHour)
                oneHour.id -> loadChartData(it, "1H", data, oneDay, oneMonth, fifteenMinute, oneWeek, fourHour)
                fourHour.id -> loadChartData(it, "4H", data, oneDay, oneMonth, oneHour, oneWeek, fifteenMinute)
                oneDay.id -> loadChartData(it, "D", data, fifteenMinute, oneMonth, oneHour, oneWeek, fourHour)
                oneWeek.id -> loadChartData(it, "W", data, oneDay, oneMonth, oneHour, fifteenMinute, fourHour)
                oneMonth.id -> loadChartData(it, "M", data, oneDay, fifteenMinute, oneHour, oneWeek, fourHour)
            }
        }

        oneDay.setOnClickListener(clickListener)
        oneHour.setOnClickListener(clickListener)
        oneWeek.setOnClickListener(clickListener)
        oneMonth.setOnClickListener(clickListener)
        fifteenMinute.setOnClickListener(clickListener)
        fourHour.setOnClickListener(clickListener)
    }
    private fun loadChartData(
        it: View?,
        s: String,
        item: CryptoCurrency,
        oneDay: AppCompatButton?,
        oneMonth: AppCompatButton?,
        oneHour: AppCompatButton?,
        oneWeek: AppCompatButton?,
        fourHour: AppCompatButton?
    ) {

        disableButton(oneDay,oneHour,oneMonth,oneWeek,fourHour)
        it!!.setBackgroundResource(R.drawable.active_button)
        detaillChartWebView.settings.javaScriptEnabled = true
        detaillChartWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)

        detaillChartWebView.loadUrl(
            "https://s.tradingview.com/widgetembed..." + item.symbol
                .toString() + "USD&interval=" + s + "&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1&toolbarbg=F1F3F6&studies=[]" +
                    "&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&overrides={}" +
                    "&enabled_features=[]&disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget&utm_campaign=chart&utm_term=BTCUSDT"
        )
    }

    private fun disableButton(oneDay: AppCompatButton?, oneHour: AppCompatButton?, oneMonth: AppCompatButton?,
                              oneWeek: AppCompatButton?, fourHour: AppCompatButton?) {
        oneHour!!.background = null
        oneWeek!!.background = null
        oneDay!!.background = null
        fourHour!!.background = null
        oneMonth!!.background = null
    }
}