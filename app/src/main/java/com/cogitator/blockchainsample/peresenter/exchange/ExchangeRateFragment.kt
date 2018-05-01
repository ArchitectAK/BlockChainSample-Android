package com.cogitator.blockchainsample.peresenter.exchange

import android.app.ProgressDialog
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.cogitator.blockchainsample.R
import com.cogitator.blockchainsample.domain.BlockChainRepo
import com.cogitator.blockchainsample.peresenter.base.BaseFragment
import com.cogitator.blockchainsample.peresenter.base.IRecyclerItemClickListener
import com.cogitator.blockchainsample.peresenter.exchange.ExchangeRateContract.ExchangeRatePresenter
import info.blockchain.api.exchangerates.Currency
import kotlinx.android.synthetic.main.fragment_exchange_rate.*
import java.text.DecimalFormat
import java.text.ParseException


/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 01/05/2018 (MM/DD/YYYY)
 */
class ExchangeRateFragment : BaseFragment(), ExchangeRateContract.ExchangeRateView,
        View.OnClickListener, IRecyclerItemClickListener<String> {

    private val numberFormat = DecimalFormat.getInstance()
    private var presenter: ExchangeRateContract.ExchangeRatePresenter? = null
    private var progressDialog: ProgressDialog? = null

    override fun showProgress() {
        dismissProgress()
        progressDialog = ProgressDialog(activity)
        progressDialog?.setMessage("Getting exchange rates... Please wait.")
        progressDialog?.setCancelable(false)
        progressDialog?.show()
    }


    override fun dismissProgress() {
        progressDialog?.dismiss()
        progressDialog = null
    }

    override fun showErrorToast() {
        Toast.makeText(activity, R.string.exchange_rate_error, Toast.LENGTH_SHORT).show()
        tvListEmpty_FER.visibility = if (exchangeRateAdapter.getItemCount() === 0) View.VISIBLE else View.GONE
    }

    override fun setExchangeRates(data: List<Pair<String, Currency>>) {
        exchangeRateAdapter.updateData(data)
        tvListEmpty_FER.visibility = if (exchangeRateAdapter.getItemCount() === 0) View.VISIBLE else View.GONE

    }

    override fun setFromExchange(exchangeCode: String, value: Double) {
        if (exchangeCode == ExchangeRatePresenter.BITCOIN) {
            tvFromExchangeIconInText_FER.visibility = View.INVISIBLE
            ivFromExchangeIcon_FER.visibility = View.VISIBLE
        } else {
            tvFromExchangeIconInText_FER.text = exchangeCode
            tvFromExchangeIconInText_FER.visibility = View.VISIBLE
            ivFromExchangeIcon_FER.visibility = View.INVISIBLE
        }
        etFromExchangeValue_FER.setText(numberFormat.format(value))

    }

    override fun setToExchange(exchangeCode: String, value: Double) {
        if (exchangeCode.equals(ExchangeRatePresenter.BITCOIN)) {
            tvToExchangeIconInText_FER.visibility = View.INVISIBLE
            ivToExchangeIcon_FER.visibility = View.VISIBLE
        } else {
            tvToExchangeIconInText_FER.text = exchangeCode
            tvToExchangeIconInText_FER.visibility = View.VISIBLE
            ivToExchangeIcon_FER.visibility = View.INVISIBLE
        }
        etToExchangeValue_FER.setText(numberFormat.format(value))
    }

    override fun setPresenter(presenter: ExchangeRateContract.ExchangeRatePresenter) {
        this.presenter = presenter
        this.presenter?.subscribe()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.ivChangeExchangeSides_FER -> presenter?.changeExchangeSide()
        }
    }

    override fun selectItem(data: String, position: Int) {
        exchangeRateAdapter.selectItem(position)
        presenter?.setTargetCurrencyInKey(data)
    }

    override fun screenName(): Int {
        return R.string.exchange_rate
    }

    override fun layoutRes(): Int {
        return R.layout.fragment_exchange_rate
    }

    override fun initUI() {
        numberFormat.minimumFractionDigits = 2
        numberFormat.maximumFractionDigits = 15

        ivFromExchangeIcon_FER
        ivToExchangeIcon_FER

        tvFromExchangeIconInText_FER
        tvToExchangeIconInText_FER



        etToExchangeValue_FER
        rvExchangeRates_FER.layoutManager = LinearLayoutManager(activity)
        rvExchangeRates_FER.adapter = ExchangeRateAdapter(this)

        etFromExchangeValue_FER.addTextChangedListener(fromExchangeTextWatcher)
        ivChangeExchangeSides_FER.setOnClickListener(this)
    }

    override fun initPresenter() {
        ExchangeRatePresenter(this, BlockChainRepo())
    }

    override fun onDestroy() {
        super.onDestroy()
        etFromExchangeValue_FER.removeTextChangedListener(fromExchangeTextWatcher)
        this.presenter?.unsubscribe()
    }

    private val fromExchangeTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

        }

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            var result: Double
            try {
                result = numberFormat.parse(charSequence.toString()).toDouble()
            } catch (e: ParseException) {
                result = 0.0
                e.printStackTrace()
            }

            presenter.calculateExchange(result)
        }

        override fun afterTextChanged(editable: Editable) {

        }
    }
}