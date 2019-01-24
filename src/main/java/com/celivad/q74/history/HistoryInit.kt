package com.celivad.q74.history

import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.LinearLayout
import com.celivad.q74.HistoryActivity
import com.celivad.q74.player.HistoryS
import kotlinx.android.synthetic.main.activity_history.*

class HistoryInit(private val historyActivity: HistoryActivity) {
    private val adapter = HistoryRvAdapter()
    fun init(){
        adapter.setData(HistoryS.history.history)
        historyActivity.history_rv.layoutManager = StaggeredGridLayoutManager(1,LinearLayout.VERTICAL)
        historyActivity.history_rv.adapter = adapter
    }
}