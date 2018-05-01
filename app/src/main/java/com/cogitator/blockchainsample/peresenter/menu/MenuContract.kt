package com.cogitator.blockchainsample.peresenter.menu

import com.cogitator.blockchainsample.peresenter.base.BaseModel
import com.cogitator.blockchainsample.peresenter.base.BasePresenter
import com.cogitator.blockchainsample.peresenter.base.BaseView


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