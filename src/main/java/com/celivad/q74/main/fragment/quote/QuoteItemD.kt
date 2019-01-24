package com.celivad.q74.main.fragment.quote

import java.io.Serializable

abstract class QuoteItemD : Serializable{
    var src = ""
    var title = ""
    var href = ""
    var desc = ""
    abstract fun getType(): Int

    companion object {
        val AD = 0
        val HEADER = 1
        val ITEM = 2
    }
}