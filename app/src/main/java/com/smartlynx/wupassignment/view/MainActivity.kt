package com.smartlynx.wupassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.smartlynx.wupassignment.R
import com.smartlynx.wupassignment.adapter.ViewPagerAdapter
import com.smartlynx.wupassignment.databinding.ActivityDetailsBinding
import com.smartlynx.wupassignment.databinding.ActivityMainBinding
import com.smartlynx.wupassignment.model.CardInfo
import com.smartlynx.wupassignment.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initialiseViewPagerAdapter()
        initialiseViewModel()
    }

    private fun initialiseViewPagerAdapter() {
        viewPagerAdapter = ViewPagerAdapter()
        binding.viewPager.adapter = viewPagerAdapter
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