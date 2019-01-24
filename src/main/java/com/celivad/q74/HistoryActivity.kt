package com.celivad.q74

import android.graphics.Color
import android.os.Bundle
import com.celivad.app.StateCompatActivity
import com.celivad.q74.history.HistoryInit
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : StateCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        statusBarColor = Color.TRANSPARENT
        UI2Status()
        appbar.setPadding(0,statusBarHeight,0,0)
        window.decorView.setBackgroundColor(resources.getColor(R.color.background_main))
        HistoryInit(this).init()
    }
}