package com.example.coinsapp.tabLayouts

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import com.bumptech.glide.Glide
import com.example.coinsapp.R
import kotlinx.android.synthetic.main.fragment_deatail.*
import kotlinx.android.synthetic.main.fragment_deatail.view.*


class DeatailFragment : Fragment() {

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fav_btn.setOnClickListener {
            Glide.with(it).load(R.drawable.ic_baseline_favorite_24).into(fav_btn)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deatail, container, false)
    }

}