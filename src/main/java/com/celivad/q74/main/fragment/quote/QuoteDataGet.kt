package com.celivad.q74.main.fragment.quote

import com.celivad.net.http.CHttp
import com.celivad.net.http.CHttpOptions
import com.celivad.net.http.stream.CHttpGetHrefSourceStream
import com.celivad.utils.BackgroundThread
import org.jsoup.Jsoup

class QuoteDataGet(page: Int) :
    BackgroundThread<String, ArrayList<QuoteItemD>>(
        "https://www.juzimi.com/allarticle/jingdiantaici" + if (page > 0) {
            "?page=" + page
        } else ""
    ) {

    override fun doInBackground(i: String): ArrayList<QuoteItemD> {
        return get(i)
    }

    private fun get(href: String): ArrayList<QuoteItemD> {
        val quotes = ArrayList<QuoteItemD>()

        val html = getHtml(href)
        val source = Jsoup.parse(html)
        val group =
            source.select("div.view.view-xqtermspagearticletype.view-id-xqtermspagearticletype.view-display-id-block_1.zuopinliebiao.view-dom-id-1")
        val list = group.select("div.view-content")[0].children()
        for (item in list) {
            val quote = QuoteItem()
            quote.src = "https:" + item.select("div.views-field-tid").select("img").attr("src")
            quote.title = item.select("div.views-field-phpcode").select("a.xqallarticletilelink").text()
            quote.href = "https://www.juzimi.com" + item.select("div.views-field-tid").select("a").attr("href")
            quote.desc = item.select("div.views-field-phpcode").select("div.xqagepawirdesc").html()
            quotes.add(quote)
        }
        return quotes
    }

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
}