package com.smartlynx.wupassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smartlynx.wupassignment.model.CardInfo
import com.smartlynx.wupassignment.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialiseViewModel()
    }

    private fun initialiseViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getCardsObserver().observe(this, Observer<ArrayList<CardInfo>> {
            if (it != null) {
                // recyclerViewAdapter.setItemsData(it.items)
                Log.d("hello", "initialiseViewModel: $it")
            } else {
                Toast.makeText(this, "Could not load card data.", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }
}