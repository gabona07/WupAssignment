package com.smartlynx.wupassignment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.smartlynx.wupassignment.R
import com.smartlynx.wupassignment.adapter.ViewPagerAdapter
import com.smartlynx.wupassignment.model.CardInfo
import com.smartlynx.wupassignment.viewmodel.MainActivityViewModel

class MainFragment : Fragment() {

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseViewPagerAdapter()
        initialiseViewModel()
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private fun initialiseViewPagerAdapter() {
        val viewPager = requireView().findViewById<ViewPager2>(R.id.viewPager)
        viewPagerAdapter = ViewPagerAdapter()
        viewPager.adapter = viewPagerAdapter
    }

    private fun initialiseViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getCardsObserver().observe(viewLifecycleOwner, Observer<ArrayList<CardInfo>> {
            if (it != null) {
                viewPagerAdapter.setCardsData(it)
            }
        })
        viewModel.makeApiCall()
    }
}