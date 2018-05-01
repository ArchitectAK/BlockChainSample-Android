package com.cogitator.blockchainsample.presenter.exchange

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cogitator.blockchainsample.R
import com.cogitator.blockchainsample.presenter.base.IRecyclerItemClickListener
import info.blockchain.api.exchangerates.Currency
import kotlinx.android.synthetic.main.list_item_exchange_rate.view.*
import java.text.DecimalFormat


/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 01/05/2018 (MM/DD/YYYY)
 */
class ExchangeRateAdapter(private val itemClickListener: IRecyclerItemClickListener<String>) : RecyclerView.Adapter<ExchangeRateAdapter.ExchangeRateVH>() {

    private val listData: MutableList<Pair<String, Currency>> = emptyList<Pair<String, Currency>>() as MutableList<Pair<String, Currency>>
    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeRateVH {
        return ExchangeRateVH(LayoutInflater.from(parent.context).inflate(R.layout.list_item_exchange_rate, parent, false), itemClickListener)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ExchangeRateVH, position: Int) {
        holder.bind(listData[position], selectedPosition == position)
    }


    fun updateData(listData: List<Pair<String, Currency>>) {
        this.listData.addAll(listData)
        notifyItemRangeInserted(0, this.listData.size)
    }

    fun selectItem(pos: Int) {
        val prevPosititon = this.selectedPosition
        this.selectedPosition = pos
        notifyItemChanged(prevPosititon)
        notifyItemChanged(this.selectedPosition)
    }

    class ExchangeRateVH(itemView: View, private val itemClickListener: IRecyclerItemClickListener<String>) : RecyclerView.ViewHolder(itemView) {
        private val numberFormat = DecimalFormat.getInstance()
        private lateinit var keyLabel: String

        init {

            numberFormat.minimumFractionDigits = 2
            numberFormat.maximumFractionDigits = 15

            itemView.setOnClickListener({ _ -> this.itemClickListener.selectItem(keyLabel, adapterPosition) })
        }

        fun bind(currencyPair: Pair<String, Currency>, isSelected: Boolean) {
            itemView.isSelected = isSelected
            keyLabel = currencyPair.first

            itemView.tvExchangeLabel_LIER.text = String.format("%s (%s)", keyLabel, currencyPair.second.symbol)
            itemView.tvSellValue_LIER.text = numberFormat.format(currencyPair.second.sell.toDouble())
            itemView.tvBuyValue_LIER.text = numberFormat.format(currencyPair.second.buy.toDouble())
        }
    }
}