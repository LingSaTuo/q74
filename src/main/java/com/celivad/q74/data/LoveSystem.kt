package com.celivad.q74.data

import android.content.Context

class LoveSystem(private var context: Context) : BaseSystem<LoveList.Love>(context,"love",LoveList.Love::class.java) {

    override fun read(any: Any?) {
        LoveList.love = any as LoveList.Love??:return
    }
}