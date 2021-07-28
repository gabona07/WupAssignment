package com.smartlynx.wupassignment.model

data class CardList(val cardList: ArrayList<CardInfo>)
data class CardInfo(
    val cardId: String,
    val issuer: String,
    val cardNumber: String,
    val expirationDate: String,
    val cardHolderName: String,
    val friendlyName: String,
    val currency: String,
    val cvv: String,
    val availableBalance: Int,
    val currentBalance: Int,
    val minPayment: Int,
    val dueDate: String,
    val reservations: Int,
    val balanceCarriedOverFromLastStatement: Int,
    val spendingsSinceLastStatement: Int,
    val yourLastRepayment: String,
    val status: String,
    val cardImage: String,
    val accountDetails: AccountDetails
    )

data class AccountDetails(val accountLimit: Int, val accountNumber: String)


