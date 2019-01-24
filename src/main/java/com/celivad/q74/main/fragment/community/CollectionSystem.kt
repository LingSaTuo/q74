package com.celivad.q74.main.fragment.community

import android.content.Context
import com.celivad.q74.data.BaseSystem

class CollectionSystem(private var context: Context):BaseSystem<CollectionList.Collection>(context,"collection",CollectionList.Collection::class.java) {
    override fun read(any: Any?) {
        CollectionList.list = any as CollectionList.Collection??:return
    }
}