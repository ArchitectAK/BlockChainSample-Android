package com.cogitator.blockchainsample.peresenter.base

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 29/4/18 (MM/DD/YYYY)
 */
interface BaseView<in P : BasePresenter> {
    fun setPresenter(presenter: P)
}