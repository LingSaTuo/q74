package com.celivad.q74.found

open abstract class CommentD {
    var href = ""
    var desc = ""
    var user = ""
    var userid = ""
    var icon=""
    var title = ""
    var usericon = ""
    abstract fun getType(): Int

    companion object {
        val AD = 0
        val COMMENT = 1
    }
}