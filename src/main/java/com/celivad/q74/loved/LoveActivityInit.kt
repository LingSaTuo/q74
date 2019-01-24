package com.celivad.q74.loved

import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.LinearLayout
import com.celivad.q74.LoveActivity
import com.celivad.q74.data.LoveList
import kotlinx.android.synthetic.main.activity_love.*

class LoveActivityInit(private var loveActivity: LoveActivity) {
    private val adapter = LoveRVAdapter()
    private val rv = loveActivity.love_rv
    fun init(){
        adapter.setData(LoveList.love.list)
        rv.layoutManager = StaggeredGridLayoutManager(1,LinearLayout.VERTICAL)
        rv.adapter = adapter
    }
}