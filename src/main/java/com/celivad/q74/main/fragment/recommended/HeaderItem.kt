package com.celivad.q74.main.fragment.recommended

import com.celivad.q74.search.fragment.results.ResultBaseData
import com.celivad.q74.search.fragment.results.ResultData

class HeaderItem : ResultData() {

    val result = arrayListOf<ResultData>()

    fun addData(result:ResultData){
        this.result.add(result)
    }

    override fun getType(): Int {
        return Header
    }

    companion object {
        fun cast(resultData: ResultBaseData): HeaderItem {
            val header = HeaderItem()
            header.icon = resultData.icon
            header.id = resultData.id
            header.title = resultData.title
            header.type = resultData.type
            header.uptime = resultData.uptime
            return header
        }
    }
}