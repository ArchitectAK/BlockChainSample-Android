package com.cogitator.blockchainsample.presenter.base

import android.app.Activity
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.annotation.Nullable
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cogitator.blockchainsample.presenter.MainActivity

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 29/04/2018 (MM/DD/YYYY)
 */

abstract class BaseFragment : Fragment() {
    private lateinit var activity: MainActivity
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        try {
            this.activity = activity as MainActivity
            this.activity.setTitle(screenName())
        } catch (e: ClassCastException) {
            throw RuntimeException("This fragment should have Activity instance")
        }

    }

    override fun onResume() {
        super.onResume()
        this.activity.setTitle(screenName())
    }

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initPresenter()
    }

    //This method recommend call in onViewCreated() | initUI()
    @Nullable
    protected fun <T : View> findView(@IdRes viewId: Int): T? {
        val fragmentView = view
        return if (fragmentView != null)
            fragmentView.findViewById(viewId) as T
        else
            null
    }

    @StringRes
    protected abstract fun screenName(): Int

    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected abstract fun initUI()
    protected abstract fun initPresenter()

}