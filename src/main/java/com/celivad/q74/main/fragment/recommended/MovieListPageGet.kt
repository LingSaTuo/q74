package com.celivad.q74.main.fragment.recommended

import com.celivad.q74.data.MovieBaseData
import com.celivad.q74.search.fragment.results.ResultBaseData
import com.celivad.q74.search.fragment.results.ResultData
import org.jsoup.Jsoup

open class MovieListPageGet (private var i: String) {
    val list = ArrayList<ResultBaseData>()

    init {
        geti()
    }

    private fun geti() {
        val source = Jsoup.connect(i)
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:64.0) Gecko/20100101 Firefox/64.0")
            .get()
        val list = source.select("div.col-md-1-5.col-sm-4.col-xs-6")
        for (item in list) {
            val movieItem = ResultData()
            movieItem.title = item.select("a").attr("title")
            movieItem.id = item.select("a").attr("href").replace("http://", "https://")
            if (!movieItem.id.startsWith("https")) movieItem.id = "${MovieBaseData.href}${movieItem.id}"
            movieItem.icon = item.select("img").attr("src")
            movieItem.uptime = item.select("div.otherinfo").text()
            this.list.add(movieItem)
        }
    }
}