package com.celivad.q74.player

import android.content.Context
import com.celivad.q74.data.BaseSystem

object His {
    var his = His()


    fun add(id:String,index:Int){
        his.his[id] = index
    }

    fun read(id:String):Int{
        return his.his[id]?:0
    }


    fun save(context: Context){
        HisSystem(context).save(his)
    }
    fun read(context: Context){
        HisSystem(context).read()
    }

    class His{
        var his = HashMap<String,Int>()
    }

    class HisSystem(context: Context) : BaseSystem<His>(context,"player",His::class.java){
        override fun read(any: Any?) {
            his = any as His??:return
        }

    }

}