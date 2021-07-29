package com.smartlynx.wupassignment.adapter

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
import com.smartlynx.wupassignment.DetailsActivity
import com.smartlynx.wupassignment.R
import com.smartlynx.wupassignment.model.CardInfo

class ViewPagerAdapter: RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    private var cards = ArrayList<CardInfo>()

    fun setCardsData(data: ArrayList<CardInfo>) {
        this.cards = data
        notifyDataSetChanged()
    }

    inner class ViewPagerViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.cardImage)
        private val available: TextView = view.findViewById(R.id.tvActualAvailable)
        private val currentBalance: TextView = view.findViewById(R.id.tvActualCurrentBalance)
        private val minPayment: TextView = view.findViewById(R.id.tvActualMinPayment)
        private val dueDate: TextView = view.findViewById(R.id.tvActualDueDate)
        private val progressbar: ProgressBar = view.findViewById(R.id.progressBar)
        private val detailsButton: Button = view.findViewById(R.id.details)


        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(card: CardInfo) {
            // Load image
            val currentImage = card.cardImage
            val assets = itemView.context.assets
            val inputStream = assets.open("card_images/$currentImage.png")
            val drawable = Drawable.createFromStream(inputStream, null)
            Glide.with(imageView).load(drawable).into(imageView)

            // Set progressbar status
            val full = card.availableBalance + card.currentBalance
            if (card.availableBalance != 0) {
                val availableBalancePercent = full / card.availableBalance
                progressbar.setProgress(availableBalancePercent, true)
            } else {
                progressbar.setProgress(100, true)
            }

            // Set details click listener
            detailsButton.setOnClickListener {
                val intent = Intent(itemView.context, DetailsActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable("card", card)
                intent.putExtras(bundle)
                itemView.context.startActivity(intent)
            }

            available.text = card.availableBalance.toString()
            currentBalance.text = card.currentBalance.toString()
            minPayment.text = card.minPayment.toString()
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