package me.khongcham.khongcham.Adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import me.khongcham.khongcham.Fragments.*

class AppFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> {
                StockFragment.newInstance()
            }
            1 -> {
                PayBillFragment.newInstance()
            }
            2 -> {
                HomeFragment.newInstance()
            }
            3 -> {
                SummaryFragment.newInstance()
            }
            4 -> {
                FAQFragment.newInstance()
            }
            else -> null
        }
    }

    override fun getCount(): Int {
        return 5
    }

}