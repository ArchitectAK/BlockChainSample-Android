package com.cogitator.blockchainsample.presenter.exchange

import com.cogitator.blockchainsample.presenter.base.BaseModel
import com.cogitator.blockchainsample.presenter.base.BasePresenter
import com.cogitator.blockchainsample.presenter.base.BaseView
import info.blockchain.api.exchangerates.Currency
import rx.Observable


/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 01/05/2018 (MM/DD/YYYY)
 */
interface ExchangeRateContract {
    interface ExchangeRateView : BaseView<ExchangeRatePresenter> {
        fun showProgress()
        fun dismissProgress()
        fun showErrorToast()
        fun setExchangeRates(data: List<Pair<String, Currency>>)

        fun setFromExchange(exchangeCode: String, value: Double)
        fun setToExchange(exchangeCode: String, value: Double)
    }

    interface ExchangeRatePresenter : BasePresenter {
        fun setTargetCurrencyInKey(keyLabel: String)
        fun calculateExchange(valueFrom: Double)
        fun changeExchangeSide()
    }

    interface ExchangeRateModel : BaseModel {
        val exchangeRates: Observable<Map<String, Currency>>
    }
}