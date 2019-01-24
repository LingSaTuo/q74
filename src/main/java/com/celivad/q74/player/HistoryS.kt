package com.celivad.q74.player

import android.content.Context
import com.celivad.q74.data.MovieData

object HistoryS {

    var history = History()

    class History {
        val history = ArrayList<MovieData>()
    }

    fun contains(movieData: MovieData): Boolean {
        for (item in history.history) {
            if (item.id == movieData.id) return true
        }
        return false
    }

    fun remove(movieData: MovieData) {
        for (item in history.history) {
            if (item.id == movieData.id) {
                history.history.remove(item)
                break
            }
        }
    }

    fun save(context: Context) {
        HistorySystem(context).save(history)
    }

    fun read(context: Context) {
        HistorySystem(context).read()
    }

    fun add(movieData: MovieData) {
        remove(movieData)
        history.history.add(movieData)
    }
}