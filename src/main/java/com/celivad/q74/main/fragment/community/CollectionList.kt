package com.celivad.q74.main.fragment.community

import android.content.Context
import com.celivad.q74.found.CommentD
import com.celivad.q74.found.CommentData

object CollectionList {
    var list = Collection()
    class Collection{
        val list = ArrayList<CommentData>()
    }


    fun contains(comment: CommentD): Boolean {
        for (item in list.list) {
            if (item.href == comment.href) return true
        }
        return false
    }

    fun remove(comment: CommentD) {
        for (item in list.list) {
            if (item.href == comment.href) {
                list.list.remove(item)
                break
            }
        }
    }

    fun save(context: Context) {
        CollectionSystem(context).save(list)
    }

    fun read(context: Context) {
        CollectionSystem(context).read()
    }

    fun add(movieData: CommentData) {
        remove(movieData)
        list.list.add(movieData)
    }
}