package com.celivad.utils

import java.lang.ref.WeakReference


/**
 * @author Celivad
 * 简易的异步处理框架，不打算使用AsynTask和线程池
 * */
abstract class BackgroundThread<IN, OUT>(private var i: IN) : Thread() {

    private var weakReference : WeakReference<Thread>?=null

    private var main: (OUT) -> Unit = {}
    abstract fun doInBackground(i: IN): OUT
    override fun run() {
        super.run()
        try {
            val out = doInBackground(i)
            RunOnUiThread {
                main.invoke(out)
            }
        }catch (e:Throwable){
            e.printStackTrace()
        }
    }

    open fun excute(main: (OUT) -> Unit) {
        this.main = main
        start()
    }

    @Deprecated("PLZ use excute")
    override fun start() {
        super.start()
        weakReference = WeakReference(this)
    }
}