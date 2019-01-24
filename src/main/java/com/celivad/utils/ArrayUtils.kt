package com.celivad.utils

import com.celivad.q74.data.MovieBaseData

object ArrayUtils {
    fun <T:Any> getIndex(array: Array<T>, element:T):Int{
        for ((index,ele) in array.withIndex()){
            if (element == ele)return index
        }
        return -1
    }

    fun <T:MovieBaseData> isLoop(list0:ArrayList<T>,list1:ArrayList<T>):Boolean{
        for (item0 in list0){
            for (item1 in list1){
                if (item0.id == item1.id){
                    return true
                }
            }
        }
        return false
    }

}