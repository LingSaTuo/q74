package com.celivad.q74.main

import android.content.Context
import com.celivad.q74.data.BaseSystem
import com.celivad.q74.search.fragment.history.History

class HistorySystem(private var context: Context) :BaseSystem<History.HIS>(context,"history",History.HIS::class.java){
    override fun read(any: Any?) {
        History.his = any as History.HIS??:return
    }
}