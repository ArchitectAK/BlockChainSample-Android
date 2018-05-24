package com.cogitator.blockchainsample.presenter.menu

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 01/05/2018 (MM/DD/YYYY)
 */
class MenuPresenter(private var view: MenuContract.MenuView, private var model: MenuContract.MenuModel?) : MenuContract.MenuPresenter {


    init {
        this.view.setPresenter(this)
    }

    override fun openCreateWallet() {
        this.view.showCreateWallet()
    }

    override fun openExchangeRate() {

        this.view.showExchangeRate()
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {

    }

}