package com.cogitator.blockchainsample.peresenter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.cogitator.blockchainsample.R


/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 29/04/2018 (MM/DD/YYYY)
 */


class MainActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {

        if (addToBackStack)
            supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right, R.anim.slide_in_left, R.anim.slide_out_left)
                    .replace(R.id.flFragmentsContainer, fragment)
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
        else
            supportFragmentManager.beginTransaction()
                    .replace(R.id.flFragmentsContainer, fragment)
                    .commitAllowingStateLoss()
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.removeOnBackStackChangedListener(this)
    }

    override fun onBackStackChanged() {
        shouldDisplayHomeUp()
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }

    private fun shouldDisplayHomeUp() {
        val backExist = supportFragmentManager.backStackEntryCount > 0
        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(backExist)
    }

}
