package com.cogitator.blockchainsample.domain

import com.cogitator.blockchainsample.presenter.creation.RegisterWalletContract
import com.cogitator.blockchainsample.presenter.exchange.ExchangeRateContract
import com.cogitator.blockchainsample.presenter.utils.API_KEY
import com.cogitator.blockchainsample.presenter.utils.BASE_URL
import info.blockchain.api.createwallet.CreateWallet
import info.blockchain.api.createwallet.CreateWalletResponse
import info.blockchain.api.exchangerates.Currency
import info.blockchain.api.exchangerates.ExchangeRates
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 29/04/2018 (MM/DD/YYYY)
 */


class BlockChainRepo : RegisterWalletContract.RegisterWalletModel,
        ExchangeRateContract.ExchangeRateModel {
    override val exchangeRates: Observable<Map<String, Currency>>
        get() {
            return Observable.fromCallable({ ExchangeRates.getTicker(API_KEY) })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
        }

    override fun createWallet(email: String, password: String): Observable<CreateWalletResponse> {
        return Observable.fromCallable({
            CreateWallet.create(
                    BASE_URL,
                    password,
                    API_KEY, null, null,
                    email)
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
    }
}