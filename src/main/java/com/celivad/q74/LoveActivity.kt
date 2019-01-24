package com.celivad.q74

import android.graphics.Color
import android.os.Bundle
import com.celivad.app.StateCompatActivity
import com.celivad.q74.data.LoveList
import com.celivad.q74.loved.LoveActivityInit
import kotlinx.android.synthetic.main.activity_love.*

class LoveActivity : StateCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_love)
        window.decorView.setBackgroundColor(resources.getColor(R.color.background_main))
        statusBarColor = Color.TRANSPARENT
        UI2Status()
        appbar.setPadding(0,statusBarHeight,0,0)
        LoveActivityInit(this).init()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        LoveList.save(this)
    }
}