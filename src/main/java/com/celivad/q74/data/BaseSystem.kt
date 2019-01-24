package com.celivad.q74.data

import android.content.Context
import com.celivad.io.CFile
import com.google.gson.Gson

/**
 * 用于保存记录，最好用数据库，考虑到种类量多以及我对数据库不熟悉，采取文件保存
 * */

abstract class BaseSystem<T>(private var context: Context, private var key: String, private var clazz: Class<T>) {
    protected val cFile = CFile(context.filesDir.absolutePath + "/" + key + "_")

    abstract fun read(any: Any?)

    open fun read() {
        if (cFile.exists()) {
            read(Gson().fromJson(String(cFile.readAll()), clazz))
        }
    }

    fun save(his: Any) {
        cFile.coveredWrite(Gson().toJson(his).toByteArray())
    }

}