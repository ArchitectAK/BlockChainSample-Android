package com.cogitator.blockchainsample.presenter.menu

import android.view.View
import com.cogitator.blockchainsample.R
import com.cogitator.blockchainsample.presenter.MainActivity
import com.cogitator.blockchainsample.presenter.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_menu.*
import com.cogitator.blockchainsample.presenter.creation.RegisterWalletFragment
import com.cogitator.blockchainsample.presenter.exchange.ExchangeRateFragment


/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 01/05/2018 (MM/DD/YYYY)
 */
class MenuFragment : BaseFragment(), MenuContract.MenuView, View.OnClickListener {

    private val presenter: MenuContract.MenuPresenter? = null

    override fun screenName(): Int {
        return R.string.app_name
    }

    override fun layoutRes(): Int {
        return R.layout.fragment_menu
    }

    override fun initUI() {
        llCreateWallet_FM.setOnClickListener(this)
        llExchangeRate_FM.setOnClickListener(this)
    }

    override fun initPresenter() {
        MenuPresenter(this, null)
    }

    override fun showCreateWallet() {
        (activity as MainActivity).replaceFragment(RegisterWalletFragment(), true)
    }

    override fun showExchangeRate() {
        (activity as MainActivity).replaceFragment(ExchangeRateFragment(), true)
    }

    override fun setPresenter(presenter: MenuContract.MenuPresenter) {
        this.presenter?.subscribe()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.llCreateWallet_FM -> presenter?.openCreateWallet()
            R.id.llExchangeRate_FM -> presenter?.openExchangeRate()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.presenter?.unsubscribe()
    }
}