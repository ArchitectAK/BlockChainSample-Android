package com.cogitator.blockchainsample.domain

import com.cogitator.blockchainsample.peresenter.creation.RegisterWalletContract
import info.blockchain.api.createwallet.CreateWallet
import info.blockchain.api.createwallet.CreateWalletResponse
import info.blockchain.api.exchangerates.ExchangeRates
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers



/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 29/04/2018 (MM/DD/YYYY)
 */


class BlockChainRepo : RegisterWalletContract.RegisterWalletModel,
        ExchangeRateContract.ExchangeRateModel {

    fun createWallet(email: String, password: String): Observable<CreateWalletResponse> {
        return Observable.fromCallable({
            CreateWallet.create(
                    Constants.BASE_URL,
                    password,
                    Constants.API_KEY, null, null,
                    email)
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
    }

    fun getExchangeRates(): Observable<Map<String, Currency>> {
        return Observable.fromCallable({ ExchangeRates.getTicker(Constants.API_KEY) })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
    }
}