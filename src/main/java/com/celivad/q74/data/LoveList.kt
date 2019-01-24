package com.celivad.q74.data

import android.content.Context
import com.celivad.io.CFile
import com.google.gson.Gson

object LoveList {

    var love = Love()

    class Love {
        val list = ArrayList<MovieData>()
    }

    fun contains(movieData: MovieData): Boolean {
        for (item in love.list) {
            if (item.id == movieData.id) return true
        }
        return false
    }

    fun remove(movieData: MovieData) {
        for (item in love.list) {
            if (item.id == movieData.id) {
                love.list.remove(item)
                break
            }
        }
    }

    fun save(context: Context) {
        LoveSystem(context).save(love)
    }

    fun read(context: Context) {
        LoveSystem(context).read()
    }

    fun add(movieData: MovieData) {
        remove(movieData)
        love.list.add(movieData)
    }
}