package com.smartlynx.wupassignment.network

import com.smartlynx.wupassignment.model.CardList
import retrofit2.http.GET


interface ApiService {

    @GET("cards.json")
    suspend fun getCardData(): CardList
}