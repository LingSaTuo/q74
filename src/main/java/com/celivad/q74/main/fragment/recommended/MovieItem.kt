package com.celivad.q74.main.fragment.recommended

import com.celivad.q74.R
import com.celivad.q74.search.fragment.results.ResultBaseData
import com.celivad.q74.search.fragment.results.ResultData

class MovieItem : ResultData() {
    override fun getType(): Int {
        return Result
    }

    companion object {
        fun cast(resultData: ResultBaseData): MovieItem {
            val item = MovieItem()
            item.icon = resultData.icon
            item.id = resultData.id
            item.title = resultData.title
            item.type = resultData.type
            item.uptime = resultData.uptime
            return item
        }
    }
}