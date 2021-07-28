package com.smartlynx.wupassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartlynx.wupassignment.model.CardList
import com.smartlynx.wupassignment.network.ApiService
import com.smartlynx.wupassignment.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {

    var cardsLiveData: MutableLiveData<CardList> = MutableLiveData()

    fun getCardsObserver(): MutableLiveData<CardList> {
        return cardsLiveData
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
            val response = retrofitInstance.getCardData()
            cardsLiveData.postValue(response)
        }
    }
}