package com.celivad.q74.player

import com.celivad.q74.introduction.IntroductionData
import com.celivad.q74.search.fragment.results.ResultData
import org.jsoup.Jsoup
import org.jsoup.select.Elements

class PlayerData(private var introductionData: IntroductionData) {
    var videoM3u8 = ""
    var related = arrayListOf<ResultData>()

    init {
        videoM3u8 = getM3u8()
    }

    private fun getM3u8(): String {
        val source = Jsoup.connect(introductionData.playPage)
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:64.0) Gecko/20100101 Firefox/64.0")
            .get()
        introductionData.videoSource = source.select("iframe").attr("src").split("=")[1]
        getRelatedVideo(source.select("ul.dslist-group").select("li"))
        return introductionData.videoSource
    }


    private fun getRelatedVideo(el: Elements){
        for (item in el){
            val relateV = ResultData()
            relateV.title = item.select("a").text()
            relateV.icon = introductionData.icon
            relateV.uptime = introductionData.uptime
            relateV.id = item.select("a").attr("href")
            related.add(relateV)
        }
    }
}