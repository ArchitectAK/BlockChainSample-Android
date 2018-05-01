package com.cogitator.blockchainsample.presenter.menu

import com.cogitator.blockchainsample.presenter.base.BaseModel
import com.cogitator.blockchainsample.presenter.base.BasePresenter
import com.cogitator.blockchainsample.presenter.base.BaseView


/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 01/05/2018 (MM/DD/YYYY)
 */
interface MenuContract {
    interface MenuView : BaseView<MenuPresenter> {
        fun showCreateWallet()
        fun showExchangeRate()
    }

    interface MenuPresenter : BasePresenter {
        fun openCreateWallet()
        fun openExchangeRate()
    }

    interface MenuModel : BaseModel
}