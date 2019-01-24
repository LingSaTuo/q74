package com.celivad.q74.search.fragment.history

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.celivad.q74.R
import com.celivad.q74.SearchActivity
import com.celivad.q74.search.fragment.Bus
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_searchhistory.view.*

class SearchHistory : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Bus.clear("search")
        return inflater.inflate(R.layout.fragment_searchhistory,null,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mactivity = activity as SearchActivity??:return

        val adapter = RVAdapter()
        adapter.setData(History.his.history)

        view.history.layoutManager = StaggeredGridLayoutManager(History.his.history.size/5+1,LinearLayout.HORIZONTAL)

        adapter.setOnClickListener {
            mactivity.search(it)
        }

        view.history.adapter = adapter


        Bus.add("search"){
            History.addHistory(it as String)
            History.save(view.context)
            adapter.setData(History.his.history)
            adapter.notifyDataSetChanged()
        }
    }
}