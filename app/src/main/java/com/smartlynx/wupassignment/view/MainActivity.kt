package com.smartlynx.wupassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.smartlynx.wupassignment.R
import com.smartlynx.wupassignment.adapter.ViewPagerAdapter
import com.smartlynx.wupassignment.model.CardInfo
import com.smartlynx.wupassignment.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialiseViewPagerAdapter()
        initialiseViewModel()
    }

    private fun initialiseViewPagerAdapter() {
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPagerAdapter = ViewPagerAdapter()
        viewPager.adapter = viewPagerAdapter
    }

    private fun initialiseViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getCardsObserver().observe(this, Observer<ArrayList<CardInfo>> {
            if (it != null) {
                viewPagerAdapter.setCardsData(it)
            } else {
                Toast.makeText(this, "Could not load card data.", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }
}