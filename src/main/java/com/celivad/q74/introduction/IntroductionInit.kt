package com.celivad.q74.introduction

import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.celivad.bmob.report
import com.celivad.gilde.HttpIconLoader
import com.celivad.q74.IntroductionActivity
import com.celivad.q74.PlayerActivity
import com.celivad.q74.R
import com.celivad.q74.data.MovieData
import com.celivad.utils.BackgroundThread
import kotlinx.android.synthetic.main.activity_movie.*

class IntroductionInit(private var introductionActivity: IntroductionActivity) : (View, IntroductionData) -> Unit {

    override fun invoke(p1: View, data: IntroductionData) {
        if (LegalCheck(data, introductionActivity).isLegal()) {
            val intent = Intent(introductionActivity, PlayerActivity::class.java)
            intent.putExtra("data", data)
            introductionActivity.startActivity(intent)
        }
    }

    fun init() {
        getIn()
    }

    private fun getIn() {

        introductionActivity.fab.setOnClickListener {
            Toast.makeText(introductionActivity, "别急！我正在检查该视频是否违规！", Toast.LENGTH_LONG).show()
        }

        val movie = introductionActivity.intent.getSerializableExtra("baseData") as MovieData?
        movie ?: return
        object : BackgroundThread<String, IntroductionData>(movie.id) {
            override fun doInBackground(i: String): IntroductionData {
                return IntroductionGet(movie).introductionData
            }
        }.excute {
            setData2Ui(it)
            val adapter = PlayerRVAdapter()
            adapter.setPlayers(it.player)
            introductionActivity.players.layoutManager = StaggeredGridLayoutManager(1, LinearLayout.HORIZONTAL)
            introductionActivity.players.adapter = adapter
        }
    }

    private fun setData2Ui(introductionData: IntroductionData) {
        HttpIconLoader(introductionActivity.icon).load(introductionData.icon)
        introductionActivity.findViewById<TextView>(R.id.title).text = introductionData.title
        introductionActivity.action.text = introductionData.action
        introductionActivity.time.text = introductionData.time
        introductionActivity.intoductions.text = introductionData.introduce

        isLegal(introductionData)
        introductionActivity.love.setOnClickListener {
            Toast.makeText(
                introductionActivity, if (introductionActivity.changeLove(introductionData)) {
                    "加入喜欢"
                } else {
                    "取消喜欢"
                }, Toast.LENGTH_SHORT
            ).show()
        }

        introductionActivity.collection.setOnClickListener {
            Toast.makeText(
                introductionActivity, if (introductionActivity.changeCollection(introductionData)) {
                    "加入标签"
                } else {
                    "取消标签"
                }, Toast.LENGTH_SHORT
            ).show()
        }

        introductionActivity.buildLCA(introductionData)
    }



    /**
     * 如果是直观类账号，直接允许观看，跳过检测
     * 否则检测资源违规性
     * */
    private fun isLegal(introductionData: IntroductionData) {
        if (LegalCheck.isL()) {
            fabLeagl(introductionData)
            return
        }
        BmobQuery<report>()
            .addWhereEqualTo("id", introductionData.id)
            .setLimit(1)
            .findObjects(object : FindListener<report>() {
                override fun done(p0: MutableList<report>?, p1: BmobException?) {
                    introductionData.bmobLegal.legal = p0?.get(0)
                    fabLeagl(introductionData)
                    introductionActivity.fab.setOnClickListener {
                        this@IntroductionInit.invoke(introductionActivity.fab, introductionData)
                    }
                }
            })
    }


    /**
     * 如果是直观类账号，直接允许观看，跳过检测
     * */

    private fun fabLeagl(introductionData: IntroductionData) {
        if (LegalCheck.isL())
            introductionActivity.fab.setOnClickListener {
                this@IntroductionInit.invoke(introductionActivity.fab, introductionData)
            }
        if (!introductionData.isLegal || introductionData.bmobLegal.legal != null) {
            introductionActivity.fab.setImageResource(R.mipmap.ban)
            introductionActivity.fab.backgroundTintList =
                    introductionActivity.resources.getColorStateList(android.R.color.holo_red_dark)
            introductionActivity.fab.setColorFilter(Color.WHITE)
        } else {
            introductionActivity.fab.setImageResource(R.mipmap.play)
            introductionActivity.fab.backgroundTintList =
                    introductionActivity.resources.getColorStateList(R.color.colorAccent)
            introductionActivity.fab.setColorFilter(introductionActivity.resources.getColor(R.color.colorPrimary))
        }
    }

}