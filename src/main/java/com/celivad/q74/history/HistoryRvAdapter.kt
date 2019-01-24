package com.celivad.q74.history

import android.view.View
import com.celivad.q74.loved.LoveRVAdapter
import kotlinx.android.synthetic.main.item_love.view.*

class HistoryRvAdapter : LoveRVAdapter() {
    override fun onBindViewHolder(p0: LoveItem, p1: Int) {
        super.onBindViewHolder(p0, p1)
        p0.view.loved.visibility = View.GONE
    }
}