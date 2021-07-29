package com.smartlynx.wupassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import com.smartlynx.wupassignment.R
import com.smartlynx.wupassignment.model.CardInfo

class DetailsActivity : AppCompatActivity() {

    lateinit var cardInfo: CardInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        getSerializableFromBundle()
        fillDetails(cardInfo)
    }

    private fun getSerializableFromBundle() {
        val intent = this.intent
        val bundle = intent.extras
        cardInfo = bundle?.getSerializable("card") as CardInfo
    }

    private fun fillDetails(cardInfo: CardInfo) {
        val actualCurrentBalanceTv = findViewById<TextView>(R.id.actualCurrentBalanceTv)
        actualCurrentBalanceTv.text = cardInfo.currentBalance.toString()

        val actualAvailableTv = findViewById<TextView>(R.id.actualAvailableTv)
        actualAvailableTv.text = cardInfo.availableBalance.toString()

        val actualReservationsTv = findViewById<TextView>(R.id.actualReservationsTv)
        actualReservationsTv.text = cardInfo.reservations.toString()

        val actualBalanceCarriedOverTv = findViewById<TextView>(R.id.actualBalanceCarriedOverTv)
        actualBalanceCarriedOverTv.text = cardInfo.balanceCarriedOverFromLastStatement.toString()

        val actualTotalSpendingTv = findViewById<TextView>(R.id.actualTotalSpendingTv)
        actualTotalSpendingTv.text = cardInfo.spendingsSinceLastStatement.toString()

        val actualLatestPaymentTv = findViewById<TextView>(R.id.actualLatestPaymentTv)
        actualLatestPaymentTv.text = cardInfo.yourLastRepayment

        val actualCardAccountLimitTv = findViewById<TextView>(R.id.actualCardAccountLimitTv)
        actualCardAccountLimitTv.text = cardInfo.accountDetails.accountLimit.toString()

        val actualCardAccountNumberTv = findViewById<TextView>(R.id.actualCardAccountNumberTv)
        actualCardAccountNumberTv.text = cardInfo.accountDetails.accountNumber

        val actualCardNumberTv = findViewById<TextView>(R.id.actualCardNumberTv)
        actualCardNumberTv.text = cardInfo.cardNumber

        val actualCardHolderNameTv = findViewById<TextView>(R.id.actualCardHolderNameTv)
        actualCardHolderNameTv.text = cardInfo.cardHolderName

        val actualCardNumberSupplementaryTv = findViewById<TextView>(R.id.actualCardNumberSupplementaryTv)
        actualCardNumberSupplementaryTv.text = cardInfo.cardNumber

        val actualCardHolderNameSupplementaryTv = findViewById<TextView>(R.id.actualCardHolderNameSupplementaryTv)
        actualCardHolderNameSupplementaryTv.text = cardInfo.cardHolderName

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.max = cardInfo.availableBalance + cardInfo.currentBalance
        progressBar.progress = cardInfo.availableBalance

    }
}