package com.cogitator.blockchainsample.presenter.creation

import com.cogitator.blockchainsample.presenter.base.BaseModel
import com.cogitator.blockchainsample.presenter.base.BasePresenter
import com.cogitator.blockchainsample.presenter.base.BaseView
import info.blockchain.api.createwallet.CreateWalletResponse
import rx.Observable


/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 29/04/2018 (MM/DD/YYYY)
 */



interface RegisterWalletContract {
    interface RegisterWalletView : BaseView<RegisterWalletPresenter> {
        fun showProgress()
        fun dismissProgress()
        fun showToast(success: Boolean)

        fun showErrorStateForEmail(error: RegisterWalletError.Email)
        fun showErrorStateForPassword(error: RegisterWalletError.Password)
        fun showErrorStateForPasswordConfirm(error: RegisterWalletError.PasswordConfirm)
    }

    interface RegisterWalletPresenter : BasePresenter {
        fun createWallet(email: String, password: String, confirmPassword: String)
    }

    interface RegisterWalletModel : BaseModel {
        fun createWallet(email: String, password: String): Observable<CreateWalletResponse>
    }
}