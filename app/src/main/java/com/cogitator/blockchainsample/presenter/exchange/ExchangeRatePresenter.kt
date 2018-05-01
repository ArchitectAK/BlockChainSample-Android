package com.cogitator.blockchainsample.presenter.exchange

import info.blockchain.api.exchangerates.Currency
import java.math.BigDecimal


/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 01/05/2018 (MM/DD/YYYY)
 */
class ExchangeRatePresenter(private var view: ExchangeRateContract.ExchangeRateView?, private var model: ExchangeRateContract.ExchangeRateModel?) : ExchangeRateContract.ExchangeRatePresenter {
    companion object {
        val BITCOIN = "BTC"
    }

    private lateinit var exchangeRates: Map<String, Currency>
    private lateinit var fromExchange: Pair<String, Currency>
    private lateinit var toExchange: Pair<String, Currency>

    private val valueFrom = 1.0
    private var valueTo = 1.0

    init {
        this.view?.setPresenter(this)
    }

    override fun setTargetCurrencyInKey(keyLabel: String) {
        if (fromExchange.first == BITCOIN) {
            toExchange = Pair(keyLabel, exchangeRates[keyLabel]!!)
            calculateExchange(valueFrom)
        } else {
            fromExchange = Pair(keyLabel, exchangeRates[keyLabel]!!)
            this.view?.setFromExchange(fromExchange.first, fromExchange.second.buy.toDouble())
        }
    }

    override fun calculateExchange(valueFrom: Double) {
        valueTo = if (fromExchange.first == BITCOIN)
            toExchange.second.buy?.multiply(BigDecimal.valueOf(valueFrom))?.toDouble()!!
        else BigDecimal.valueOf(valueFrom).divide(fromExchange.second.buy, 15, BigDecimal.ROUND_HALF_UP).toDouble()
        this.view?.setToExchange(toExchange.first, valueTo)
    }

    override fun changeExchangeSide() {
        fromExchange = toExchange
        toExchange = Pair(fromExchange.first, fromExchange.second)
        this.view?.setFromExchange(fromExchange.first, valueTo)
    }

    override fun subscribe() {
        fromExchange = Pair(BITCOIN, Currency(1.0, 1.0, 1.0, 1.0, BITCOIN))
        toExchange = Pair(BITCOIN, Currency(1.0, 1.0, 1.0, 1.0, BITCOIN))
        this.view?.setFromExchange(fromExchange.first, fromExchange.second.buy?.toDouble()!!)

        loadExchanges()
    }

    override fun unsubscribe() {
        this.view = null
        this.model = null
    }

    private fun loadExchanges() {
        view?.showProgress()
        model?.exchangeRates?.subscribe({ stringCurrencyMap ->
            exchangeRates = stringCurrencyMap
            this.view?.dismissProgress()
            this.view?.setExchangeRates(prepareDataForList())

        }, { throwable ->
            throwable.printStackTrace()

            this.view?.dismissProgress()
            this.view?.showErrorToast()

        })
    }

    private fun prepareDataForList(): List<Pair<String, Currency>> {
        val data: MutableList<Pair<String, Currency>> = emptyList<Pair<String, Currency>>() as MutableList<Pair<String, Currency>>
        for (label in exchangeRates.keys) {
            data.add(Pair(label, exchangeRates[label]) as Pair<String, Currency>)
        }
        return data
    }
}