package com.smartlynx.wupassignment.adapter

import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smartlynx.wupassignment.R
import com.smartlynx.wupassignment.databinding.CardItemBinding
import com.smartlynx.wupassignment.model.CardInfo
import com.smartlynx.wupassignment.view.DetailsFragment
import com.smartlynx.wupassignment.view.MainActivity
import com.smartlynx.wupassignment.view.MainFragment
import java.text.DecimalFormat


class ViewPagerAdapter: RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    private var cards = ArrayList<CardInfo>()

    fun setCardsData(data: ArrayList<CardInfo>) {
        this.cards = data
        notifyDataSetChanged()
    }

    inner class ViewPagerViewHolder(val binding: CardItemBinding): RecyclerView.ViewHolder(binding.root) {

        lateinit var card: CardInfo

        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(card: CardInfo) {

            this.card = card

            // Load image into the image views
            val currentImage = card.cardImage
            val assets = itemView.context.assets
            val inputStreamCardImage = assets.open("card_images/$currentImage.png")
            val drawableCardImage = Drawable.createFromStream(inputStreamCardImage, null)
            Glide.with(binding.cardImage).load(drawableCardImage).into(binding.cardImage)

            val inputStreamAlert = assets.open("icons/ic_alert.png")
            val drawableAlert = Drawable.createFromStream(inputStreamAlert, null)
            Glide.with(binding.alertIv).load(drawableAlert).into(binding.alertIv)

            // Set progressbar status
            val fullBalance = card.availableBalance + card.currentBalance
            binding.progressBar.progress = 0
            binding.progressBar.max = fullBalance
            val progressAnimator = ObjectAnimator.ofInt(binding.progressBar, "progress", binding.progressBar.progress, card.availableBalance)
            progressAnimator.duration = 3000
            progressAnimator.start()

            // Show alert if the available balance is zero
            if (card.availableBalance == 0) {
                binding.alertIv.visibility = View.VISIBLE
            } else {
                binding.alertIv.visibility = View.GONE
            }

            // Set details click listener
            binding.details.setOnClickListener {
                val detailsFragment = DetailsFragment()
                val bundle = Bundle()
                (itemView.context as MainActivity).supportFragmentManager.beginTransaction().apply {
                    replace(R.id.frameLayout, detailsFragment).commit()
                }
                bundle.putSerializable("card", card)
                detailsFragment.arguments = bundle
            }
            val dec = DecimalFormat("#,###.##")
            binding.tvActualAvailable.text = dec.format(card.availableBalance).plus(" USD")
            binding.tvActualCurrentBalance.text = dec.format(card.currentBalance).plus(" USD")
            binding.tvActualMinPayment.text = dec.format(card.minPayment).plus(" USD")
            binding.tvActualDueDate.text = card.dueDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind(cards[position])
        with(holder) {
            binding
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }

}