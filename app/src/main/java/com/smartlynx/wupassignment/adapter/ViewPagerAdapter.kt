package com.smartlynx.wupassignment.adapter

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smartlynx.wupassignment.view.DetailsActivity
import com.smartlynx.wupassignment.R
import com.smartlynx.wupassignment.model.CardInfo
import java.text.DecimalFormat

class ViewPagerAdapter: RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    private var cards = ArrayList<CardInfo>()

    fun setCardsData(data: ArrayList<CardInfo>) {
        this.cards = data
        notifyDataSetChanged()
    }

    inner class ViewPagerViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val cardImage: ImageView = view.findViewById(R.id.cardImage)
        private val available: TextView = view.findViewById(R.id.tvActualAvailable)
        private val currentBalance: TextView = view.findViewById(R.id.tvActualCurrentBalance)
        private val minPayment: TextView = view.findViewById(R.id.tvActualMinPayment)
        private val dueDate: TextView = view.findViewById(R.id.tvActualDueDate)
        private val progressbar: ProgressBar = view.findViewById(R.id.progressBar)
        private val detailsButton: Button = view.findViewById(R.id.details)
        private val alertIv: ImageView = view.findViewById(R.id.alertIv)


        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(card: CardInfo) {
            // Load image into the image views
            val currentImage = card.cardImage
            val assets = itemView.context.assets
            val inputStreamCardImage = assets.open("card_images/$currentImage.png")
            val drawableCardImage = Drawable.createFromStream(inputStreamCardImage, null)
            Glide.with(cardImage).load(drawableCardImage).into(cardImage)

            val inputStreamAlert = assets.open("icons/ic_alert.png")
            val drawableAlert = Drawable.createFromStream(inputStreamAlert, null)
            Glide.with(alertIv).load(drawableAlert).into(alertIv)

            // Set progressbar status
            val fullBalance = card.availableBalance + card.currentBalance
            progressbar.progress = 0
            progressbar.max = fullBalance
            val progressAnimator = ObjectAnimator.ofInt(progressbar, "progress", progressbar.progress, card.availableBalance)
            progressAnimator.duration = 3000
            progressAnimator.start()

            // Show alert if the available balance is zero
            if (card.availableBalance == 0) {
                alertIv.visibility = View.VISIBLE
            } else {
                alertIv.visibility = View.GONE
            }

            // Set details click listener
            detailsButton.setOnClickListener {
                val intent = Intent(itemView.context, DetailsActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable("card", card)
                intent.putExtras(bundle)
                itemView.context.startActivity(intent)
            }
            val dec = DecimalFormat("#,###.##")
            available.text = dec.format(card.availableBalance).plus(" USD")
            currentBalance.text = dec.format(card.currentBalance).plus(" USD")
            minPayment.text = dec.format(card.minPayment).plus(" USD")
            dueDate.text = card.dueDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewPagerViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind(cards[position])
    }

    override fun getItemCount(): Int {
        return cards.size
    }

}