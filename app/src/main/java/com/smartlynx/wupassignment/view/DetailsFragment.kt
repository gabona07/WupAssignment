package com.smartlynx.wupassignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smartlynx.wupassignment.R
import com.smartlynx.wupassignment.databinding.FragmentDetailsBinding
import com.smartlynx.wupassignment.model.CardInfo
import java.text.DecimalFormat

class DetailsFragment: Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var cardInfo: CardInfo


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setProgressBarStatus(cardInfo)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSerializableFromBundle()
        fillDetails(cardInfo)
        setProgressBarStatus(cardInfo)
    }

    private fun getSerializableFromBundle() {
        cardInfo = arguments?.getSerializable("card") as CardInfo
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