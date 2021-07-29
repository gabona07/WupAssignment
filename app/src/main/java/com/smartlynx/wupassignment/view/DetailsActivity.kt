package com.smartlynx.wupassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import com.smartlynx.wupassignment.R
import com.smartlynx.wupassignment.databinding.ActivityDetailsBinding
import com.smartlynx.wupassignment.model.CardInfo
import java.text.DecimalFormat

class DetailsActivity : AppCompatActivity() {

    private lateinit var cardInfo: CardInfo
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        getSerializableFromBundle()
        fillDetails(cardInfo)
        setProgressBarStatus(cardInfo)
    }

    private fun getSerializableFromBundle() {
        val intent = this.intent
        val bundle = intent.extras
        cardInfo = bundle?.getSerializable("card") as CardInfo
    }

    private fun fillDetails(cardInfo: CardInfo) {
        val dec = DecimalFormat("#,###.##")
        binding.actualCurrentBalanceTv.text = dec.format(cardInfo.currentBalance).plus(" USD")
        binding.actualAvailableTv.text = dec.format(cardInfo.availableBalance).plus(" USD")
        binding.actualReservationsTv.text = dec.format(cardInfo.reservations).plus(" USD")
        binding.actualBalanceCarriedOverTv.text = dec.format(cardInfo.balanceCarriedOverFromLastStatement).plus(" USD")
        binding.actualTotalSpendingTv.text = dec.format(cardInfo.spendingsSinceLastStatement).plus(" USD")
        binding.actualLatestPaymentTv.text = cardInfo.yourLastRepayment
        binding.actualCardAccountLimitTv.text = dec.format(cardInfo.accountDetails.accountLimit).plus(" USD")
        binding.actualCardAccountNumberTv.text = cardInfo.accountDetails.accountNumber
        binding.actualCardNumberTv.text = cardInfo.cardNumber
        binding.actualCardHolderNameTv.text = cardInfo.cardHolderName
        binding.actualCardNumberSupplementaryTv.text = cardInfo.cardNumber
        binding.actualCardHolderNameSupplementaryTv.text = cardInfo.cardHolderName
    }

    private fun setProgressBarStatus(cardInfo: CardInfo) {
        binding.progressBar.max = cardInfo.availableBalance + cardInfo.currentBalance
        binding.progressBar.progress = cardInfo.availableBalance
    }
}