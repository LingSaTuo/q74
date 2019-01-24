package com.celivad.q74.found

import com.celivad.net.http.CHttp
import com.celivad.net.http.entity.CStringParamsEntity
import com.celivad.net.http.stream.CHttpGetHrefSourceStream
import com.celivad.utils.BackgroundThread
import org.jsoup.Jsoup

class UserIconGet(data: CommentD) : BackgroundThread<CommentD, Unit>(data) {
    companion object {
        private val list = HashMap<String,String>()
    }

    override fun doInBackground(i: CommentD) {
        if (list[i.userid]!=null){
            i.usericon = list[i.userid]!!
            return
        }
        list[i.userid] = ""
        post(i)
    }

    private fun post(data: CommentD) {
        CHttp.openPost("https://www.juzimi.com/user/${data.userid}/ajax")
            .addEntity(CStringParamsEntity("keyvar", "u${data.userid}"))
            .setStreamCallback(object : CHttpGetHrefSourceStream() {
                override fun receiveSource(html: String) {
                    val usericon = "https:" + (Jsoup.parse(html).select("div.picture").select("img").attr("src"))
                    data.usericon = usericon
                    list[data.userid] = usericon
                }
            }).build().excute(null)
    }
}