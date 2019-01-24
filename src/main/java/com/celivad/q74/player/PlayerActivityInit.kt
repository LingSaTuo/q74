package com.celivad.q74.player

import android.annotation.SuppressLint
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.LinearLayout
import cn.jzvd.JzvdStd
import com.celivad.q74.PlayerActivity
import com.celivad.q74.data.MovieBaseData
import com.celivad.q74.introduction.IntroductionData
import com.celivad.q74.search.fragment.results.ResultBaseData
import com.celivad.q74.search.fragment.results.ResultData
import com.celivad.utils.BackgroundThread
import kotlinx.android.synthetic.main.activity_player.*
import kotlinx.android.synthetic.main.activity_player.view.*

class PlayerActivityInit(private var playerActivity: PlayerActivity) {
    private val introductionData: IntroductionData? =
        playerActivity.intent.getSerializableExtra("data") as IntroductionData?
    private val adapter = RelateVideoAdapter()

    private var p = 0

    private val rv = playerActivity.related

    @SuppressLint("SetTextI18n")
    private val relateVideo: (View, ResultBaseData, Int) -> Unit = run@{ _, result, index ->
        introductionData ?: return@run
        introductionData.playPage = MovieBaseData.href + result.id
        His.add(introductionData.id, index)
        p = index
        playerActivity.appbar.titlei.text = "${introductionData.title}  ${result.title}"
        getData()
    }

    init {
        adapter.setOnClickListener(relateVideo)

        rv.adapter = adapter
        rv.layoutManager = StaggeredGridLayoutManager(1, LinearLayout.HORIZONTAL)
        setOnSelect(adapter)
        getData()
        HistoryS.add(introductionData!!)
        playerActivity.back.setOnClickListener {
            playerActivity.onBackPressed()
        }
        playerActivity.player.setOnCompleteListener {
            introductionData ?: return@setOnCompleteListener
            if (His.read(introductionData.id) + 1 >= adapter.data.size) return@setOnCompleteListener
            relateVideo.invoke(it, adapter.data[His.read(introductionData.id) + 1], His.read(introductionData.id) + 1)
        }
    }

    private fun getData() {
        introductionData ?: return
        playerActivity.appbar.loading.visibility = View.VISIBLE
        object : BackgroundThread<IntroductionData, ArrayList<ResultData>>(introductionData) {
            override fun doInBackground(i: IntroductionData): ArrayList<ResultData> {
                return PlayerData(i).related
            }
        }.excute {
            val index = His.read(introductionData.id)
            println(index)
            if (index != 0 && p == 0) {
                relateVideo.invoke(rv, it[index], index)
                return@excute
            }
            startPlay(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun startPlay(it: ArrayList<ResultData>) {
        introductionData ?: return
        playerActivity.player.setUp(
            introductionData.videoSource,
            introductionData.title,
            JzvdStd.SCREEN_WINDOW_NORMAL
        )
        playerActivity.appbar.loading.visibility = View.GONE
        adapter.setData(it)
        rv.scrollToPosition(His.read(introductionData.id))
        playerActivity.player.autoStart()
    }

    private fun setOnSelect(adapter: RelateVideoAdapter) {
        playerActivity.select.setOnClickListener {
            SelectionDia(introductionData!!, playerActivity, adapter, relateVideo).init()
        }
    }
}