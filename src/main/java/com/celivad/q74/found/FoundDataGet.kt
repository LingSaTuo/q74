package com.celivad.q74.found

import com.celivad.net.http.CHttp
import com.celivad.net.http.CHttpOptions
import com.celivad.net.http.entity.CStringParamsEntity
import com.celivad.net.http.stream.CHttpGetHrefSourceStream
import com.celivad.q74.main.fragment.quote.QuoteItemD
import com.celivad.utils.BackgroundThread
import org.jsoup.Jsoup

class FoundDataGet(href: String,private var bdata: QuoteItemD) : BackgroundThread<String, ArrayList<CommentData>>(href) {
    override fun doInBackground(i: String): ArrayList<CommentData> {
        val list = ArrayList<CommentData>()
        readData(i, list)
        return list
    }

    private fun readData(href: String, list: ArrayList<CommentData>) {
        val source = Jsoup.parse(getHtml(href))
        val group =
            source.select("div.view.view-xqarticletermspage.view-id-xqarticletermspage.view-display-id-block_1.xalllist.view-dom-id-1")
                .select("div.view-content")[0].children()

        for (i in group) {
            val data = CommentData()
            data.title = bdata.title
            data.icon = bdata.src
            data.desc = i.select("a.xlistju").html()
            data.href = "https://www.juzimi.com" + i.select("a.xlistju").attr("href")
            data.userid = i.select("a.xqusernpop").attr("juuid")
            data.user = i.select("a.xqusernpop").text()
            //post(data)
            list.add(data)
        }
    }



    /**
     * Jsoup无法获取到源码，使用CTool 的CHttp获取
     * */
    private fun getHtml(href: String): String {
        var htmls = ""
        val option = CHttpOptions()
        option.doInput = true
        CHttp.openGet(href)
            .setStreamCallback(
                object : CHttpGetHrefSourceStream() {
                    override fun receiveSource(html: String) {
                        htmls = html
                    }
                }
            )
            .setCHttpOptions(option)
            .build().excute(null)
        return htmls
    }



    /**
     * 用于获取头像
     * */
    private fun post(data: CommentD) {
        CHttp.openPost("https://www.juzimi.com/user/${data.userid}/ajax")
            .addEntity(CStringParamsEntity("keyvar", "u${data.userid}"))
            .setStreamCallback(object : CHttpGetHrefSourceStream() {
                override fun receiveSource(html: String) {
                    val usericon = "https:" + (Jsoup.parse(html).select("div.picture").select("img").attr("src"))
                    data.usericon = usericon
                }
            }).build().excute(null)
    }

}