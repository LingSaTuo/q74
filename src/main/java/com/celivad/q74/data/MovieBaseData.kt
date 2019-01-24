package com.celivad.q74.data

import java.io.Serializable

open class MovieBaseData : Serializable{
    companion object {
        val href = "https://www.d6080.com"
    }
    var icon = ""
    var title = ""
    open var uptime = ""
    var id = ""

    var type = ""
}