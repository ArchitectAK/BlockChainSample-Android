package com.cogitator.blockchainsample.peresenter.creation


import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern


/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 30/04/2018 (MM/DD/YYYY)
 */
class RegisterWalletPresenter(var view: RegisterWalletContract.RegisterWalletView?, var model: RegisterWalletContract.RegisterWalletModel?) : RegisterWalletContract.RegisterWalletPresenter {

    private val PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9_@]{10,}+$")

    init {
        view?.setPresenter(this)
    }

    override fun createWallet(email: String, password: String, confirmPassword: String) {
        var allValid = true

        if (TextUtils.isEmpty(email)) {
            view?.showErrorStateForEmail(RegisterWalletError.Email.EMPTY)
            allValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            this.view?.showErrorStateForEmail(RegisterWalletError.Email.NOT_VALID)
            allValid = false
        } else
            this.view?.showErrorStateForEmail(RegisterWalletError.Email.NONE)

        if (TextUtils.isEmpty(password)) {
            this.view?.showErrorStateForPassword(RegisterWalletError.Password.EMPTY)
            allValid = false
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            this.view?.showErrorStateForPassword(RegisterWalletError.Password.NOT_VALID)
            allValid = false
        } else
            this.view?.showErrorStateForPassword(RegisterWalletError.Password.NONE)

        if (TextUtils.isEmpty(confirmPassword)) {
            this.view?.showErrorStateForPasswordConfirm(RegisterWalletError.PasswordConfirm.EMPTY)
            allValid = false
        } else if (!password.equals(confirmPassword)) {
            this.view?.showErrorStateForPasswordConfirm(RegisterWalletError.PasswordConfirm.NOT_EQUAL)
            allValid = false
        } else
            this.view?.showErrorStateForPasswordConfirm(RegisterWalletError.PasswordConfirm.NONE)

        if (allValid) createWallet(email, password)
    }

    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe() {
        view = null
        model = null

    }

    private fun createWallet(email: String, password: String) {
        this.view?.showProgress()
        this.model?.createWallet(email, password)?.subscribe({ createWalletResponse ->
            view?.dismissProgress()
            view?.showToast(createWalletResponse != null)

        }) { throwable ->
            throwable.printStackTrace()

            view?.dismissProgress()
            view?.showToast(false)
        }
    }
}