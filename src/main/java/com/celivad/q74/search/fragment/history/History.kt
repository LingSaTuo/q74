package com.celivad.q74.search.fragment.history

import android.content.Context
import com.celivad.q74.main.HistorySystem


object History {

    var his = HIS()

    class HIS{
        val history = ArrayList<String>()
    }
    fun addHistory(resultBaseData: String) {
        refresh(resultBaseData)
        his.history.add(resultBaseData)
    }

    private fun refresh(resultBaseData: String) {
        for (item in his.history) {
            if (item == resultBaseData) {
                his.history.remove(item)
                break
            }
        }
    }

    fun save(context: Context){
        HistorySystem(context).save(his)
    }

    fun read(context: Context){
        HistorySystem(context).read()
    }

}