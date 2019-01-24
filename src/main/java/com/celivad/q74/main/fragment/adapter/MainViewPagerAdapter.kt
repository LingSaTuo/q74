package com.celivad.q74.main.fragment.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

open class MainViewPagerAdapter(private var fragmentManager:FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private val page = ArrayList<Fragment>()

    override fun getItem(p0: Int): Fragment {
        return page[p0]
    }

    override fun getCount(): Int {
        return page.size
    }
    fun addPage(fragment: Fragment){
        if (page.contains(fragment))return
        page.add(fragment)
    }
}