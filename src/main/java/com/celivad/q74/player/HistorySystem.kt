package com.celivad.q74.player

import android.content.Context
import com.celivad.q74.data.BaseSystem

class HistorySystem (private var context: Context) : BaseSystem<HistoryS.History>(context,"historys", HistoryS.History::class.java) {

    override fun read(any: Any?) {
        HistoryS.history = any as HistoryS.History??:return
    }
}