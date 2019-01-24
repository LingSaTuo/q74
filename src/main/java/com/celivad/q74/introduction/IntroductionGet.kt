package com.celivad.q74.introduction

import com.celivad.q74.data.MovieBaseData
import com.celivad.q74.data.MovieData
import org.jsoup.Jsoup
import org.jsoup.select.Elements

class IntroductionGet(private val movieData: MovieData) {

    val introductionData: IntroductionData = IntroductionData.cast(movieData)

    init {
        geti()
    }


    private fun geti() {
        val source = Jsoup.connect(movieData.id)
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:64.0) Gecko/20100101 Firefox/64.0")
            .get()
        val trs = source.select("tbody")[0].select("tr")
        introductionData.action = trs[0].select("td")[1].text()
        buildPlay(trs)
        introductionData.type = trs[2].select("td")[1].text()
        introductionData.country = trs[3].select("td")[1].text()
        introductionData.state = trs[4].select("td")[1].text()
        introductionData.time = trs[5].select("td")[1].text()
        introductionData.introduce = "      "+source.select("p.summary").text()
        introductionData.playPage = MovieBaseData.href + source.select("a.btn.btn-success.btn-block").attr("href")
    }
    private fun buildPlay(ele: Elements){
        val plist = ele[1].select("td")[1].select("a")
        for (play in plist){
            val name = play.text()
            if (name=="")continue
            introductionData.player.add(name)
        }
    }
}