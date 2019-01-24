package com.celivad.q74.main

import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.celivad.q74.MainActivity
import com.celivad.q74.R
import com.celivad.q74.data.LoveList
import com.celivad.q74.main.fragment.adapter.MainViewPagerAdapter
import com.celivad.q74.main.fragment.community.CollectionList
import com.celivad.q74.main.fragment.community.Community
import com.celivad.q74.main.fragment.my.MY
import com.celivad.q74.main.fragment.quote.Quote
import com.celivad.q74.main.fragment.recommended.Recommended
import com.celivad.q74.search.fragment.history.History
import com.celivad.utils.ArrayUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityInit(private var mainActivity: MainActivity) {
   private val array = arrayOf(R.id.recommended, R.id.found, R.id.community, R.id.my)
    fun init() {
        History.read(mainActivity)
        LoveList.read(mainActivity)
        CollectionList.read(mainActivity)
        bander()
        mainActivity.main_viewpager.addOnPageChangeListener(object : PageListener() {
            override fun onPageSelected(p0: Int) {
                setBNVCheckedState(ArrayUtils.getIndex(array,mainActivity.bnv.selectedItemId), p0)
            }
        })
    }


    private fun setBNVCheckedState(old: Int, new: Int) {
        mainActivity.bnv.menu.getItem(old).isChecked = false
        mainActivity.bnv.menu.getItem(new).isChecked = true
    }

    private fun bander() {
        mainActivity.bnv.setOnNavigationItemSelectedListener {
            val page = ArrayUtils.getIndex(array, it.itemId)
            mainActivity.main_viewpager.currentItem = if (page == -1) 0 else page
            return@setOnNavigationItemSelectedListener true
        }
        val vpAda = MainViewPagerAdapter(mainActivity.supportFragmentManager)
        vpAda.addPage(Recommended())
        vpAda.addPage(Quote())
        vpAda.addPage(Community())
        vpAda.addPage(MY())
        mainActivity.main_viewpager.offscreenPageLimit = 5
        mainActivity.main_viewpager.adapter = vpAda
    }

    private abstract inner class PageListener : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(p0: Int) {

        }

        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
        }

    }
}