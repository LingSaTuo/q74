package com.celivad.q74.main.fragment.community

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.celivad.q74.MainActivity
import com.celivad.q74.R
import com.celivad.q74.data.MovieData
import com.celivad.q74.search.fragment.Bus
import kotlinx.android.synthetic.main.fragment_community.view.*

class Community : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_community,null,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mactivity = activity as MainActivity??:return
        view.appbar.setPadding(0,mactivity.statusBarHeight,0,0)
        val adapter = CRvAdapter()
        Bus.clear("collection")
        Bus.add("collection"){
            val data = CollectionList.list.list
            adapter.setData(data)
            adapter.notifyDataSetChanged()
        }
        Bus.invoke("collection",CollectionList.list.list)
        view.community_rv.layoutManager = StaggeredGridLayoutManager(1,LinearLayout.VERTICAL)
        view.community_rv.adapter = adapter
    }
}