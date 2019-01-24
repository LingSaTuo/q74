package com.celivad.application

import android.app.Application
import cn.bmob.v3.Bmob

class CApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Bmob.initialize(this,"UNKNWON")
    }
}