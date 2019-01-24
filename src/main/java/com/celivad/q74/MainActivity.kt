package com.celivad.q74

import android.graphics.Color
import android.os.Bundle
import com.celivad.app.StateCompatActivity
import com.celivad.q74.main.MainActivityInit
import com.celivad.q74.player.His
import com.celivad.q74.player.HistoryS
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : StateCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_NoActionBar)
        setContentView(R.layout.activity_main)
        window.decorView.setBackgroundColor(resources.getColor(R.color.background_main))
        statusBarColor = Color.TRANSPARENT
        UI2Status()
        His.read(this)
        MainActivityInit(this).init()
        HistoryS.read(this)
    }
}
