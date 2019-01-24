package com.celivad.q74.introduction

import com.celivad.bmob.report
import com.celivad.q74.data.MovieData
import java.io.Serializable

class IntroductionData : MovieData() {


    private val skipTypes = arrayOf("YY福利", "福利上瘾", "YY神曲")

    private val unLegalTypes = arrayOf("伦理片", "伦理")

    var action = ""
    var player = arrayListOf<String>()
    var country = ""
    var state = ""
    var time = ""
    var playPage = ""
    var introduce = ""
    var bmobLegal = BmobLegal()

    var videoSource = ""

    var isLegal: Boolean = true
        get() {
            return !unLegalTypes.contains(type)
        }

    companion object {
        fun cast(resultData: MovieData): IntroductionData {
            val introductionData = IntroductionData()
            introductionData.uptime = resultData.uptime
            introductionData.icon = resultData.icon
            introductionData.id = resultData.id
            introductionData.title = resultData.title
            return introductionData
        }
    }

    class BmobLegal:Serializable {
        var legal: report? = null
    }
}