package com.cogitator.blockchainsample.presenter.creation

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.view.View
import android.widget.Toast
import com.cogitator.blockchainsample.R
import com.cogitator.blockchainsample.domain.BlockChainRepo
import com.cogitator.blockchainsample.presenter.MainActivity
import com.cogitator.blockchainsample.presenter.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_register_wallet.*


/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 29/04/2018 (MM/DD/YYYY)
 */


class RegisterWalletFragment : BaseFragment(), RegisterWalletContract.RegisterWalletView, View.OnClickListener {

    private var presenter: RegisterWalletContract.RegisterWalletPresenter? = null

    override fun screenName(): Int {
        return R.string.create_wallet
    }

    override fun layoutRes(): Int {
        return R.layout.fragment_register_wallet
    }

    override fun initUI() {

        btnCreateWallet_FRW.setOnClickListener(this)
        testDemo()
    }

    override fun initPresenter() {
        RegisterWalletPresenter(this, BlockChainRepo())
    }

    private var progressDialog: ProgressDialog? = null

    override fun showProgress() {
        dismissProgress()
        progressDialog = ProgressDialog(activity)
        progressDialog?.setMessage("Loading... Please wait.")
        progressDialog?.setCancelable(false)
        progressDialog?.show()
    }

    override fun dismissProgress() {

        progressDialog?.dismiss()
        progressDialog = null

    }

    override fun showToast(success: Boolean) {
        if (success) {
            Toast.makeText(activity, R.string.wallet_is_created, Toast.LENGTH_SHORT).show()
            (activity as MainActivity).onSupportNavigateUp()
        } else Toast.makeText(activity, R.string.wallet_creating_error, Toast.LENGTH_SHORT).show()
    }

    override fun showErrorStateForEmail(error: RegisterWalletError.Email) {
        when (error) {
            RegisterWalletError.Email.EMPTY -> {
                tilEmail_FRW.error = getString(R.string.error_field_is_empty)
                tilEmail_FRW.isErrorEnabled = true
            }
            RegisterWalletError.Email.NOT_VALID -> {
                tilEmail_FRW.error = getString(R.string.error_field_not_valid)
                tilEmail_FRW.isErrorEnabled = true
            }
            RegisterWalletError.Email.NONE -> {
                tilEmail_FRW.error = null
                tilEmail_FRW.isErrorEnabled = false
            }
        }
    }

    override fun showErrorStateForPassword(error: RegisterWalletError.Password) {
        when (error) {
            RegisterWalletError.Email.EMPTY -> {
                tilPassword_FRW.error = getString(R.string.error_field_is_empty)
                tilPassword_FRW.isErrorEnabled = true
            }
            RegisterWalletError.Email.NOT_VALID -> {
                tilPassword_FRW.error = getString(R.string.error_field_not_valid)
                tilPassword_FRW.isErrorEnabled = true
            }
            RegisterWalletError.Email.NONE -> {
                tilPassword_FRW.error = null
                tilPassword_FRW.isErrorEnabled = false
            }
        }

    }

    override fun showErrorStateForPasswordConfirm(error: RegisterWalletError.PasswordConfirm) {
        when (error) {
            RegisterWalletError.Email.EMPTY -> {
                tilPasswordConfirm_FRW.error = getString(R.string.error_field_is_empty)
                tilPasswordConfirm_FRW.isErrorEnabled = true
            }
            RegisterWalletError.PasswordConfirm.NOT_EQUAL -> {
                tilPasswordConfirm_FRW.error = getString(R.string.error_field_not_equal_passwords)
                tilPasswordConfirm_FRW.isErrorEnabled = true
            }
            RegisterWalletError.Email.NONE -> {
                tilPasswordConfirm_FRW.error = null
                tilPasswordConfirm_FRW.isErrorEnabled = false
            }
        }
    }

    override fun setPresenter(presenter: RegisterWalletContract.RegisterWalletPresenter) {
        this.presenter = presenter
        this.presenter?.subscribe()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnCreateWallet_FRW -> presenter?.createWallet(
                    etEmail_FRW.text.toString(),
                    etPassword_FRW.text.toString(),
                    etPasswordConfirm_FRW.text.toString())
        }
    }

    @SuppressLint("SetTextI18n")
    private fun testDemo() {
        etEmail_FRW.setText("ankit@mail.com")
        etPassword_FRW.setText("1234567890")
        etPasswordConfirm_FRW.setText("1234567890")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.presenter?.unsubscribe()
    }
}