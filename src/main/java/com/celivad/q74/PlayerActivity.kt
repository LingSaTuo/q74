package com.celivad.q74

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import cn.jzvd.Jzvd
import cn.jzvd.JzvdStd
import com.celivad.app.StateCompatActivity
import com.celivad.q74.player.His
import com.celivad.q74.player.HistoryS
import com.celivad.q74.player.PlayerActivityInit
import kotlinx.android.synthetic.main.activity_player.*
import kotlinx.android.synthetic.main.activity_player.view.*

class PlayerActivity : StateCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        statusBarColor = Color.TRANSPARENT
        UI2Status()
        appbar.setPadding(0,statusBarHeight,0,0)
        window.decorView.setBackgroundColor(resources.getColor(R.color.background_main))
        appbar.loading.visibility = View.GONE
        player.batteryTimeLayout.visibility = View.GONE
        player.backButton.visibility = View.GONE
        player.titleTextView.visibility = View.GONE
        PlayerActivityInit(this)
    }


    override fun onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        JzvdStd.goOnPlayOnPause()
        His.save(this)
        HistoryS.save(this)
        super.onBackPressed()
    }
}