package com.celivad.q74.search.fragment


/**
 * @author Celivad
 * 简易跨类同进程通讯模块
 * */
object Bus {

    private val map = HashMap<String, ArrayList<(Any) -> Unit>>()

    fun clear(key: String) {
        map[key] = ArrayList()
    }
    fun add(key: String,value:(Any)->Unit){
        if (map[key] == null) map[key] = ArrayList()
        map[key]!!.add(value)
    }

    fun invoke(key: String,any: Any){
        for (p0 in (map[key]?: ArrayList())){
            p0.invoke(any)
        }
    }
}